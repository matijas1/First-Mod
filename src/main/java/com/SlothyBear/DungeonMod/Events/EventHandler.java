package com.SlothyBear.DungeonMod.Events;

import java.util.List;

import com.SlothyBear.DungeonMod.Blocks.DungeonChest;
import com.SlothyBear.DungeonMod.Blocks.DungeonPortal;
import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.Items.DungeonChestKey;
import com.SlothyBear.DungeonMod.Items.DungeonStaff;
import com.SlothyBear.DungeonMod.TileEntities.TileEntityDungeonChest;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler 
{
	@SubscribeEvent
	public void onRightClick(RightClickBlock e)
	{
		ItemStack stack = e.getItemStack();
		
		//Dungeon Staff
		if(stack != null && e.getWorld().isRemote == false)
		{
			if(stack.getItem() instanceof DungeonStaff && (e.getWorld().getBlockState(e.getPos()).getBlock() instanceof DungeonPortal) && e.getEntityPlayer().isSneaking() == true) 
			{
				if(!stack.hasTagCompound())
				{
					NBTTagCompound newtag = new NBTTagCompound();
					stack.setTagCompound(newtag);
				}
				if(!stack.getTagCompound().hasKey("location_list"))
				{
					NBTTagList newlist = new NBTTagList();
					stack.getTagCompound().setTag("location_list", newlist);
				}
				NBTTagList staffNBT = stack.getTagCompound().getTagList("location_list", 10);
				NBTTagCompound location = new NBTTagCompound();
				location.setIntArray("portal_location", new int[]{e.getPos().getX(), e.getPos().getY(), e.getPos().getZ()});
				boolean exists = false;
				for(int i = 0; i < staffNBT.tagCount(); i++)
				{
					if(staffNBT.getCompoundTagAt(i).equals(location))
					{
						exists = true;
						break;
					}
				}
				if(exists == false)
				{
					staffNBT.appendTag(location);
					stack.getTagCompound().setTag("location_list", staffNBT);
				}
			}
			else if(stack.getItem() instanceof DungeonStaff && !(e.getWorld().getBlockState(e.getPos()).getBlock() instanceof DungeonPortal) && e.getEntityPlayer().isSneaking() == true) 
			{
				if(!stack.hasTagCompound())
				{
					System.out.println("Please add coordinates first!");
					return;
				}
				if(!stack.getTagCompound().hasKey("location_list"))
				{
					System.out.println("Please add coordinates first!");
					return;
				}
				DungeonStaff staff = (DungeonStaff) stack.getItem();
				NBTTagList staffNBT = stack.getTagCompound().getTagList("location_list", 10);
				NBTTagCompound location = new NBTTagCompound();
				if(staff.portal[0] != null)
				{
					location.setIntArray("portal_location", new int[]{staff.portal[0], staff.portal[1], staff.portal[2]});
					for(int i = 0; i < staffNBT.tagCount(); i++)
					{
						if(staffNBT.getCompoundTagAt(i).equals(location))
						{
							int coords[] = staffNBT.getCompoundTagAt(0).getIntArray("portal_location");
							if(i + 1 < staffNBT.tagCount())
								coords = staffNBT.getCompoundTagAt(i + 1).getIntArray("portal_location");
							staff.portal[0] = coords[0];
							staff.portal[1] = coords[1];
							staff.portal[2] = coords[2];
							break;
						}
					}
				}
				else
				{
					int coords[] = staffNBT.getCompoundTagAt(0).getIntArray("portal_location");
					staff.portal[0] = coords[0];
					staff.portal[1] = coords[1];
					staff.portal[2] = coords[2];
				}
			}
		}
		
		if(!e.getWorld().isRemote && e.getWorld().getTileEntity(e.getPos()) instanceof TileEntityDungeonChest)
		{
			TileEntityDungeonChest chest = (TileEntityDungeonChest) e.getWorld().getTileEntity(e.getPos());
			
			if(e.isCancelable() && chest.isLocked() && e.getItemStack() == null)
			{
				e.getEntityPlayer().addChatMessage(new TextComponentString("This chest is locked!"));
				e.setCanceled(true);
			}
			else if(e.isCancelable() && chest.isLocked() && !(e.getItemStack().getItem() instanceof DungeonChestKey))
			{
				e.getEntityPlayer().addChatMessage(new TextComponentString("This chest is locked!"));
				e.setCanceled(true);
			}
			else if(chest.isLocked() && e.getItemStack().getItem() instanceof DungeonChestKey)
			{
				NBTTagCompound compound = new NBTTagCompound();
				chest.writeToNBT(compound);
				compound.setString("Type", "unlocked");
				chest.clear();
				e.getWorld().setBlockState(e.getPos(), ModBlocks.unlockedDungeonChest.getDefaultState());
				TileEntityDungeonChest newchest = (TileEntityDungeonChest)e.getWorld().getTileEntity(e.getPos());
				newchest.readFromNBT(compound);
				e.getItemStack().stackSize--;
			}
		}
	}
	
	@SubscribeEvent
	public void breakBlock(BreakEvent e)
	{
		Block block = e.getState().getBlock();
		ItemStack itemstack = e.getPlayer().inventory.getCurrentItem();
		BlockPos pos = e.getPos();
		World world = e.getWorld();
		
		if(block instanceof DungeonChest)
		{
			DungeonChest chest = (DungeonChest)block;
			ItemStack blockstack = new ItemStack(block);
			NBTTagCompound compound = new NBTTagCompound();
			compound = blockstack.writeToNBT(compound);
			if(chest.isLocked())
				compound.setString("Type", "locked");
			else
				compound.setString("Type", "unlocked");
			blockstack.readFromNBT(compound);
			
			world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), blockstack));
		}
	}
	
	@SubscribeEvent
	public void breakSpeed(BreakSpeed e)
	{
		ItemStack itemstack = e.getEntityPlayer().inventory.getCurrentItem();
		Block block = e.getState().getBlock();
		
		if(block instanceof DungeonChest)
		{
			DungeonChest chest = (DungeonChest) block;
			if(chest.isLocked())
				e.setNewSpeed(0F);
			else if(itemstack == null)
				e.setNewSpeed(e.getOriginalSpeed() * 1.25F);
			else if(!(itemstack.getItem() instanceof ItemPickaxe))
			{
				e.setNewSpeed(e.getOriginalSpeed() * 1.25F);
			}
		}
	}
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent e)
	{
		if(e.getItemStack().getItem() instanceof DungeonStaff)
		{
			DungeonStaff staff = (DungeonStaff) e.getItemStack().getItem();
			List<String> tooltip = e.getToolTip();
			for(String s : tooltip)
			{
				if(s.contains("Portal coordinates: "))
					tooltip.remove(s);
			}
			tooltip.add("Portal coordinates: " + staff.portal[0] + ", " + staff.portal[1] + ", " + staff.portal[2]);
		}
	}
}

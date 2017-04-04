package com.SlothyBear.DungeonMod.Blocks;


import com.SlothyBear.DungeonMod.Dimension.DungeonTeleporter;
import com.SlothyBear.DungeonMod.Items.DungeonStaff;
import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class DungeonPortal extends Block {
	public final String name = References.dungeonPortal;

	public DungeonPortal(Material mat) {
		super(mat);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setResistance(18000000.0F);
		this.setUnlocalizedName(name);

		ModBlocks.registerBlock(this, new ItemBlock(this), name, "");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!playerIn.worldObj.isRemote && !playerIn.isDead && heldItem != null) {
			if (heldItem.getItem() instanceof DungeonStaff) {
				DungeonStaff staff = (DungeonStaff) heldItem.getItem();
				if (playerIn.dimension == References.dungeonid) {
					WorldServer worldserver = playerIn.getServer().worldServerForDimension(0);
					DungeonTeleporter teleporter = new DungeonTeleporter(
							worldserver);
					playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, 0,
							teleporter);
					return true;
				} else {
					DungeonTeleporter teleporter = new DungeonTeleporter(
							playerIn.getServer().worldServerForDimension(References.dungeonid));
					playerIn.getServer().getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn,
							References.dungeonid, teleporter);
					if(staff.portal[0] != null)
						playerIn.setPositionAndUpdate(staff.portal[0], staff.portal[1], staff.portal[2]);
					return true;
				}
			} else
				return false;
		} else
			return false;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	
}

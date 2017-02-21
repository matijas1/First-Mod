package com.SlothyBear.DungeonMod.Blocks;

import com.SlothyBear.DungeonMod.Dimnsion.DungeonDimensionProvider;
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
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldServer;

public class DungeonPortal extends Block
{
	public final String name = References.dungeonPortal;
	
	public DungeonPortal(Material mat) 
	{
		super(mat);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setResistance(18000000.0F);
		this.setUnlocalizedName(name);
		
		ModBlocks.registerBlock(this,new ItemBlock(this), name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
		{
			if(playerIn.getHeldItemMainhand() == null)
			{
				if(playerIn.dimension == References.dungeonid)
				{
					playerIn.changeDimension(0);
					BlockPos spawnPos = playerIn.getEntityWorld().getSpawnPoint();
					playerIn.setPositionAndUpdate(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
					return true;
				}
				else
				{
					playerIn.changeDimension(References.dungeonid);
					int spawnHeight = 1;
					for(int j = 1; j < 128; j++)
					{
						BlockPos spawn = new BlockPos(0, j, 0);
						IBlockState spawnState = playerIn.getEntityWorld().getBlockState(spawn);
						if(spawnState.getMaterial() == Material.AIR)
						{
							spawnHeight = j + 2;
							break;
						}
					}
					playerIn.setPositionAndUpdate(0, spawnHeight, 0);
					return true;
				}
			}
			else
				return false;
		}
		else
			return false;
	}
}

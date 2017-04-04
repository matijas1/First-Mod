package com.SlothyBear.DungeonMod.Blocks;

import com.SlothyBear.DungeonMod.Main.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	public static Block dungeonBrick;
	public static Block dungeonPortal;
	public static Block dungeonChest;
	public static Block unlockedDungeonChest;
	
	public static void loadBlocks()
	{
		dungeonBrick = new DungeonBrick(Material.ROCK);
		dungeonPortal = new DungeonPortal(Material.ROCK);
		dungeonChest = new DungeonChest(DungeonChest.Type.BASIC_LOCKED);
		unlockedDungeonChest = new DungeonChest(DungeonChest.Type.BASIC_UNLOCKED);
	}
	
	public static Block registerBlock(Block block, ItemBlock item, String name, String type)
	{
		block.setRegistryName(name + type);
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
		Main.getBlockList().add(block);
		return block;
	}
	
	public static Block registerBlockWithTileEntity(Block block, ItemBlock item, TileEntity te, String name, String type)
	{
		block.setRegistryName(name + type);
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
		GameRegistry.registerTileEntity(te.getClass(), name + type);
		//Main.getBlockList().add(block);
		Main.getItemList().add(item);
		return block;
	}
}

package com.SlothyBear.DungeonMod.Blocks;

import com.SlothyBear.DungeonMod.Main.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks 
{
	public static Block dungeonBrick;
	public static Block dungeonPortal;
	public static Block dungeonChest;
	
	public static void loadBlocks()
	{
		dungeonBrick = new DungeonBrick(Material.ROCK);
		dungeonPortal = new DungeonPortal(Material.ROCK);
		dungeonChest = new DungeonChest(DungeonChest.Type.BASIC_LOCKED);
	}
	
	public static Block registerBlock(Block block, ItemBlock item, String name)
	{
		block.setRegistryName(name);
		GameRegistry.register(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
		Main.getBlockList().add(block);
		return block;
	}
}

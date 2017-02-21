package com.SlothyBear.DungeonMod.Dimnsion;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraftforge.common.DimensionManager;

public class Dimensions 
{
	private static DimensionType dungeon;
	
	public static void loadDimensions()
	{
		dungeon = DimensionType.register("Dungeon Dimension", "_dungeondimension", References.dungeonid, DungeonDimensionProvider.class, false);
		DimensionManager.registerDimension(133, dungeon);
	}
	
	public static DimensionType getTypeByID(int id)
	{
		if(id == References.dungeonid)
			return dungeon;
		else
			return null;
	}
}

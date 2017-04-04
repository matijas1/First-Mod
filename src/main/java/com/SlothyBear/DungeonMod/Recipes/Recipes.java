package com.SlothyBear.DungeonMod.Recipes;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.Items.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes 
{
	public static void loadRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.dungeonBrick), "###", "###", "###", '#', Items.BRICK);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.dungeonBrick), ModItems.dungeonStaff, Blocks.STONEBRICK);
	}
}

package com.SlothyBear.DungeonMod.Proxies;

import java.util.List;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.Dimnsion.Dimensions;
import com.SlothyBear.DungeonMod.Items.ModItems;
import com.SlothyBear.DungeonMod.Recipes.Recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent e) 
	{
		ModBlocks.loadBlocks();
		ModItems.loadItems();
    }

    public void init(FMLInitializationEvent e) 
    {
    	Recipes.loadRecipes();
    	Dimensions.loadDimensions();
    }

    public void postInit(FMLPostInitializationEvent e) 
    {

    }
    
    public void renderThings(List<Block> blocks, List<Item> items)
    {
    	
    }
}

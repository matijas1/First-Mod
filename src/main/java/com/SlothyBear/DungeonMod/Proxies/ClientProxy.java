package com.SlothyBear.DungeonMod.Proxies;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e) 
	{
		super.preInit(e);
    }

	@Override
    public void init(FMLInitializationEvent e) 
    {
		super.init(e);
    }

	@Override
    public void postInit(FMLPostInitializationEvent e) 
    {
		super.postInit(e);
    }
    
	@Override
    public void renderThings(List<Block> blocks, List<Item> items)
    {
		for(Block block : blocks)
		{
			System.err.println(block.getRegistryName() + "   " + block.getUnlocalizedName());
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName().toString()));
		}
		for(Item item : items)
		{
			System.err.println(item.getRegistryName() + "   " + item.getUnlocalizedName());
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));
		}
    }
}
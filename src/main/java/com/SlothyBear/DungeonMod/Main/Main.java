package com.SlothyBear.DungeonMod.Main;

import java.util.LinkedList;
import java.util.List;

import com.SlothyBear.DungeonMod.Proxies.CommonProxy;
import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.modid,version = References.version,name = References.name)
public class Main 
{
	private static List<Block> blocks = new LinkedList<Block>();
	private static List<Item> items = new LinkedList<Item>();
	
	@SidedProxy(clientSide="com.SlothyBear.DungeonMod.Proxies.ClientProxy", serverSide="com.SlothyBear.DungeonMod.Proxies.ServerProxy")
    public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
		proxy.renderThings(blocks, items);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}

	public static List<Block> getBlockList() {
		return blocks;
	}

	public static List<Item> getItemList() {
		return items;
	}
	
}

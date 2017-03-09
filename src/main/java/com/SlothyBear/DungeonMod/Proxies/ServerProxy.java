package com.SlothyBear.DungeonMod.Proxies;

import com.SlothyBear.DungeonMod.Events.EventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy{

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
}

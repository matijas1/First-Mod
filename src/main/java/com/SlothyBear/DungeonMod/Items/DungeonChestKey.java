package com.SlothyBear.DungeonMod.Items;

import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DungeonChestKey extends Item
{
	public final String name = References.dungeonKey;
	
	public DungeonChestKey()
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.MISC);
		
		ModItems.registerItem(this, name);
	}
}

package com.SlothyBear.DungeonMod.Items;

import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DungeonStaff extends Item
{
	public final String name = References.dungeonStaff;
	public Integer portal[] = new Integer[3];
	
	public DungeonStaff()
	{
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.MISC);
		
		ModItems.registerItem(this, name);
	}
	
}

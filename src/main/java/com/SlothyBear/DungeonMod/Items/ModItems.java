package com.SlothyBear.DungeonMod.Items;

import com.SlothyBear.DungeonMod.Main.Main;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
	public static Item dungeonStaff;
	
	public static void loadItems()
	{
		dungeonStaff = new DungeonStaff();
	}
	
	public static Item registerItem(Item item, String name)
	{
		item.setRegistryName(name);
		GameRegistry.register(item);
		Main.getItemList().add(item);
		return item;
	}
}

package com.SlothyBear.DungeonMod.LootTables;

import com.SlothyBear.DungeonMod.References.References;
import com.SlothyBear.DungeonMod.TileEntities.TileEntityDungeonChest;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTables 
{
	public static ResourceLocation dungeonchestloot = new ResourceLocation(References.modid, "dungeon_chest");
	
	public static void register()
	{
		LootTableList.register(dungeonchestloot);
	}
	
	public static void generate(TileEntity te)
	{
		if(te instanceof TileEntityDungeonChest && te != null)
		{
			TileEntityDungeonChest chest = (TileEntityDungeonChest) te;
			chest.setLootTable(dungeonchestloot, 0L);
			//System.out.println(chest.getLootTable().toString());
			chest.fillWithLoot(null);
		}
	}
}

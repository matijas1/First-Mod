package com.SlothyBear.DungeonMod.Dimnsion;

import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;

public class DungeonDimensionProvider extends WorldProvider
{
	@Override
	public DimensionType getDimensionType() 
	{
		return Dimensions.getTypeByID(References.dungeonid);
	}

	
}

package com.SlothyBear.DungeonMod.Dimnsion;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class DungeonTeleporter extends Teleporter
{
	public DungeonTeleporter(WorldServer worldIn) 
	{
		super(worldIn);
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) 
	{
		
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) 
	{
		return true;
	}
	
	@Override
	public boolean makePortal(Entity entityIn) 
	{
		return true;
	}
	
	@Override
	public void removeStalePortalLocations(long worldTime) 
	{
		
	}
}

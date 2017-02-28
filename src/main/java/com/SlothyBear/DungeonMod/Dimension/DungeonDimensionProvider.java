package com.SlothyBear.DungeonMod.Dimension;

import com.SlothyBear.DungeonMod.Blocks.DungeonPortal;
import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;

public class DungeonDimensionProvider extends WorldProvider
{
	@Override
	public DimensionType getDimensionType() 
	{
		return Dimensions.getTypeByID(References.dungeonid);
	}

	@Override
	public void createBiomeProvider()
    {
        this.biomeProvider = new BiomeProviderSingle(Biomes.SKY);
        this.isHellWorld = false;
        this.hasNoSky = true;
    }
	
	@Override
	public boolean isSurfaceWorld() 
	{
		return false;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int x, int z) 
	{
		return false;
	}
	
	@Override
	public boolean canRespawnHere()
    {
        return false;
    }
	
	@Override
	public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful)
    {
        super.setAllowedSpawnTypes(allowHostile, false);
    }
	
	@Override
	public IChunkGenerator createChunkGenerator() 
	{
		return new DungeonChunkProvider(worldObj, worldObj.getSeed());
	}
}

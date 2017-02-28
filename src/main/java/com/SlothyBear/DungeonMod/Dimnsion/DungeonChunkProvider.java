package com.SlothyBear.DungeonMod.Dimnsion;

import java.util.List;
import java.util.Random;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;

public class DungeonChunkProvider implements IChunkGenerator
{
	private World world;
	private Random rand;
	
	public DungeonChunkProvider(World world, long seed)
	{
		this.world = world;
		this.rand = new Random(seed);
		this.world.setSeaLevel(0);
	}

	@Override
	public Chunk provideChunk(int x, int z) 
	{
		Chunk chunk = new Chunk(world, x, z);
		chunk.resetRelightChecks();
        return chunk;
	}

	@Override
	public void populate(int x, int z) 
	{
		int blockX = x * 16;
		int blockZ = z * 16;
		for(int i = 0; i < 16; i++)
		{
			for(int j = 127; j >= 0; j--)
			{
				for(int k = 0; k < 16; k++)
				{
					BlockPos pos = new BlockPos(blockX + i, j, blockZ + k);
					Chunk chunk = world.getChunkFromChunkCoords(x, z);
					if(j == 1)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
		}
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		// TODO Auto-generated method stub
		
	}
}

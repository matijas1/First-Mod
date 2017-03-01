package com.SlothyBear.DungeonMod.Dimension;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class DungeonRoomProvider {

	Chunk chunk;
	
	public DungeonRoomProvider(Chunk chunk)
	{
		this.chunk = chunk;
	}
	
	public Chunk generateChunk(int rNum, int x, int z)
	{
		defaultRooms(x, z);
		return this.chunk;
	}
	
	public void defaultRooms(int x, int z)
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
					if((j - 1) % 7 == 0)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else if((i == 0 || i == 15 || k == 0 || k == 15) && j < 8)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
		}
	}
	
	
}

package com.SlothyBear.DungeonMod.Dimension;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class DungeonRoomProvider {

	Chunk chunk;

	public DungeonRoomProvider(Chunk chunk) {
		this.chunk = chunk;
	}

	public Chunk generateChunk(int rNum, int x, int z) {
		if (rNum <= 500)
			portalRoom(x, z);
		else if (rNum > 500)
			twoSpawnerRoom(x, z);
		return this.chunk;
	}

	public void defaultRooms(int x, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;
		for (int i = 0; i < 16; i++) {
			for (int j = 127; j >= 0; j--) {
				for (int k = 0; k < 16; k++) {
					BlockPos pos = new BlockPos(blockX + i, j, blockZ + k);
					if(j == 0)
						chunk.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
					else if (j == 1 || j == 8)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else if ((i == 0 || k == 0) && j < 8)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
		}
	}

	public void portalRoom(int x, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;

		defaultRooms(x, z);
		BlockPos pos = new BlockPos(blockX + 8, 1, blockZ + 8);
		chunk.setBlockState(pos, ModBlocks.dungeonPortal.getDefaultState());
	}

	public void twoSpawnerRoom(int x, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;

		defaultRooms(x, z);
		for (int j = 1; j < 7; j++) {
			BlockPos pos1 = new BlockPos(blockX + 4, j, blockZ + 8);
			BlockPos pos2 = new BlockPos(blockX + 12, j, blockZ + 8);
			if (j != 3) {
				chunk.setBlockState(pos1, ModBlocks.dungeonBrick.getDefaultState());
				chunk.setBlockState(pos2, ModBlocks.dungeonBrick.getDefaultState());
			} else {
				chunk.setBlockState(pos1, Blocks.MOB_SPAWNER.getDefaultState());
				chunk.setBlockState(pos2, Blocks.MOB_SPAWNER.getDefaultState());
			}
		}

	}

}

package com.SlothyBear.DungeonMod.Dimension;

import java.util.Random;

import com.SlothyBear.DungeonMod.Blocks.ModBlocks;
import com.SlothyBear.DungeonMod.LootTables.LootTables;
import com.SlothyBear.DungeonMod.TileEntities.TileEntityDungeonChest;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class DungeonRoomProvider {

	private Chunk chunk;
	private static final int RNGESUS = 1000;

	public DungeonRoomProvider(Chunk chunk) {
		this.chunk = chunk;
	}

	public Chunk generateChunk(Random rand, int x, int z) {
		
		// layer 2
		int rNum = rand.nextInt(RNGESUS);
		if (rNum <= 100)
			portalRoom(x, 2, z);
		else if (rNum <= 200)
			twoSpawnerRoom(x, 2, z);
		else if(rNum <= 300)
			chestRoom(x, 2, z);
		else if(rNum > 300)
			stairsRoom(x, 2, z);
		
		// layer 0
		rNum = rand.nextInt(RNGESUS);
		if (rNum <= 100)
			portalRoom(x, 0, z);
		else if (rNum <= 200)
			twoSpawnerRoom(x, 0, z);
		else if(rNum <= 300)
			chestRoom(x, 0, z);
		else if(rNum > 300)
			stairsRoom(x, 0, z);
		
		return this.chunk;
	}

	public void defaultRooms(int x, int y, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;
		for (int i = 0; i < 16; i++) {
			for (int k = 0; k < 16; k++) {
				for (int j = (y + 1) * 7; j >= y * 7 + 1; j--) {
					BlockPos pos = new BlockPos(blockX + i, j, blockZ + k);
					if ((j - 1) % 7 == 0)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else if(((i == 0 && k >= 7 && k <= 9) || (k == 0 && i >= 7 && i <= 9)) && (j - 1) % 7 <= 4)
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
					else if ((i == 0 || k == 0))
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else if ((j - 1) % 7 == 6 && (((i + 2) % 4 == 0 && (k == 1 || k == 15)) || ((k + 2) % 4 == 0 && (i == 1 || i == 15))))
					{
						IBlockState lantern = Blocks.GLOWSTONE.getDefaultState();
						chunk.getWorld().setBlockState(pos, lantern, 2);
					}
					else
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
				if(y == 5)
				{
					BlockPos pos = new BlockPos(blockX + i, 0, blockZ + k);
					chunk.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
					pos = new BlockPos(blockX + i, 36, blockZ + k);
					chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					pos = new BlockPos(blockX + i, 37, blockZ + k);
					chunk.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
				}
			}
		}
	}

	public void portalRoom(int x, int y, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;

		defaultRooms(x, y, z);
		BlockPos pos = new BlockPos(blockX + 8, y * 7 + 2, blockZ + 8);
		chunk.setBlockState(pos, ModBlocks.dungeonPortal.getDefaultState());
	}

	public void twoSpawnerRoom(int x, int y, int z) {
		int blockX = x * 16;
		int blockZ = z * 16;

		defaultRooms(x, y, z);
		for (int j = y * 7 + 2; j <= (y + 1) * 7; j++) {
			BlockPos pos1 = new BlockPos(blockX + 4, j, blockZ + 8);
			BlockPos pos2 = new BlockPos(blockX + 12, j, blockZ + 8);
			if (j % 7 != 4) {
				chunk.setBlockState(pos1, ModBlocks.dungeonBrick.getDefaultState());
				chunk.setBlockState(pos2, ModBlocks.dungeonBrick.getDefaultState());
			} else {
				chunk.setBlockState(pos1, Blocks.MOB_SPAWNER.getDefaultState());
				chunk.setBlockState(pos2, Blocks.MOB_SPAWNER.getDefaultState());

			}
		}
	}
	public void chestRoom(int x, int y, int z)
	{
		int blockX = x * 16;
		int blockZ = z * 16;

		defaultRooms(x, y, z);
		BlockPos pos = new BlockPos(blockX + 8, y * 7 + 2, blockZ + 8);
		chunk.setBlockState(pos, ModBlocks.dungeonChest.getDefaultState());
		TileEntityDungeonChest chest = (TileEntityDungeonChest)chunk.getWorld().getTileEntity(pos);
		LootTables.generate(chest);
	}

	public void stairsRoom(int x, int y, int z)
	{
		int blockX = x * 16;
		int blockZ = z * 16;
		int stairup = 5;

		defaultRooms(x, y, z);
		for (int i = 0; i < 16; i++) {
			for (int j = (y + 2) * 7 ; j >= (y + 1) * 7 + 1; j--) {
				for (int k = 0; k < 16; k++) {
					BlockPos pos = new BlockPos(blockX + i, j, blockZ + k);
					if((i < 7 || i >= 10 || k < 5 || k >= 9) && j % 7 == 1)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else if (i == 0 || k == 0)
						chunk.setBlockState(pos, ModBlocks.dungeonBrick.getDefaultState());
					else
						chunk.setBlockState(pos, Blocks.AIR.getDefaultState());
				}
			}
		}
		for (int i = 7; i < 10; i++) {
			for (int j = (y + 1) * 7 + 1; j >= y * 7 + 2; j--) {
					BlockPos pos = new BlockPos(blockX + i, j, blockZ + stairup);
					chunk.setBlockState(pos, Blocks.STONE_BRICK_STAIRS.getDefaultState());
					stairup++;
				}
			stairup = 5;
		}
	}
}

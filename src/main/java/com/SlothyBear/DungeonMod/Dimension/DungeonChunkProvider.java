package com.SlothyBear.DungeonMod.Dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;

public class DungeonChunkProvider implements IChunkGenerator {
	private static final int RNGESUS = 1000;

	private World world;
	private Random rand;
	private long seed;

	public DungeonChunkProvider(World world, long seed) {
		this.world = world;
		this.rand = new Random(seed);
		this.seed = seed;
		this.world.setSeaLevel(0);
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Chunk chunk = new Chunk(world, x, z);
		chunk.resetRelightChecks();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
		Chunk chunk = world.getChunkFromChunkCoords(x, z);
		DungeonRoomProvider provider = new DungeonRoomProvider(chunk);
		provider.generateChunk(rand.nextInt(RNGESUS), x, z);
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

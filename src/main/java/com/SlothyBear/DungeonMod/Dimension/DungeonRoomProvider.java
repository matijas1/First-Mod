package com.SlothyBear.DungeonMod.Dimension;

import net.minecraft.world.chunk.Chunk;

public class DungeonRoomProvider {

	Chunk chunk;
	
	public DungeonRoomProvider(Chunk chunk){
		this.chunk = chunk;
	}
	
	public Chunk generateChunk(int rNum){
		return this.chunk;
	}
}

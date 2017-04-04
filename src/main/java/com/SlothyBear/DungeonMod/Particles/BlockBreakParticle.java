package com.SlothyBear.DungeonMod.Particles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleBlockDust;
import net.minecraft.world.World;

public class BlockBreakParticle extends ParticleBlockDust
{
	public BlockBreakParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn, IBlockState state, IBlockState asBlock) 
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, state);
		this.init();
		if(asBlock != null)
		{
				this.setParticleTexture(Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(asBlock));
		}
	}
}

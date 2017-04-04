package com.SlothyBear.DungeonMod.Blocks;

import com.SlothyBear.DungeonMod.References.References;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class DungeonBrick extends Block {
	public final String name = References.dungeonBrick;

	public DungeonBrick(Material mat) {
		super(mat);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setBlockUnbreakable();
		this.setSoundType(SoundType.STONE);
		this.setResistance(18000000.0F);
		this.setUnlocalizedName(name);

		ModBlocks.registerBlock(this, new ItemBlock(this), name, "");
	}
}

package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.gen.MapGenCaves;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Custom cave generator for cave gen through new blocks
 */
public class MapGenCustomCaves extends MapGenCaves {

    @Override
    @ParametersAreNonnullByDefault
    protected boolean canReplaceBlock(IBlockState blockState, IBlockState blockStateUp) {
        return blockState.getBlock() == ModBlocks.SUGAR_BLOCK || super.canReplaceBlock(blockState, blockStateUp);
    }
}

package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Custom cave generator for ravine gen through new blocks
 */
public class MapGenCustomRavine extends MapGenRavine {

    @Override
    @ParametersAreNonnullByDefault
    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block == ModBlocks.LIQUID_CHOCOLATE_BLOCK;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
        net.minecraft.world.biome.Biome biome = world.getBiome(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;

        if (state.getBlock() == ModBlocks.SUGAR_BLOCK || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
            if (y - 1 < 10) {
                data.setBlockState(x, y, z, ModBlocks.LIQUID_CANDY_BLOCK.getDefaultState());
            } else {
                data.setBlockState(x, y, z, AIR);

                if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock()) {
                    data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
                }
            }
        }
    }
}

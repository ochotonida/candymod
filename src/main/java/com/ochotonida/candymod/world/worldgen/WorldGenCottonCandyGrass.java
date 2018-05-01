package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class WorldGenCottonCandyGrass extends WorldGenerator {

    private static final IBlockState STATE_GRASS = ModBlocks.COTTON_CANDY_GRASS.getDefaultState();
    private static final IBlockState STATE_PLANT = ModBlocks.COTTON_CANDY_PLANT.getDefaultState();

    public WorldGenCottonCandyGrass() {
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World world, Random rand, BlockPos pos) {
        for (IBlockState iblockstate = world.getBlockState(pos); (iblockstate.getBlock().isAir(iblockstate, world, pos) || iblockstate.getBlock().isLeaves(iblockstate, world, pos)) && pos.getY() > 0; iblockstate = world.getBlockState(pos)) {
            pos = pos.down();
        }
        IBlockState state;

        for (int i = 0; i < 10; ++i) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (rand.nextInt(5) == 0) {
                state = STATE_PLANT;
            } else {
                state = STATE_GRASS;
            }
            if (world.isAirBlock(blockpos) && ModBlocks.COTTON_CANDY_GRASS.canBlockStay(world, blockpos, STATE_GRASS)) {
                world.setBlockState(blockpos, state, 2);
            }
        }
        return true;
    }
}

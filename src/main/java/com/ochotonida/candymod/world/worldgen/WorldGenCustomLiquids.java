package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCustomLiquids {

    private final Block block;

    public WorldGenCustomLiquids(Block blockIn) {
        this.block = blockIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn.getBlockState(position.up()).getBlock() != ModBlocks.SUGAR_BLOCK) {
            return false;
        } else if (worldIn.getBlockState(position.down()).getBlock() != ModBlocks.SUGAR_BLOCK) {
            return false;
        } else {
            IBlockState iblockstate = worldIn.getBlockState(position);

            if (!iblockstate.getBlock().isAir(iblockstate, worldIn, position) && iblockstate.getBlock() != ModBlocks.SUGAR_BLOCK) {
                return false;
            } else {
                int i = 0;

                if (worldIn.getBlockState(position.west()).getBlock() == ModBlocks.SUGAR_BLOCK) {
                    ++i;
                }

                if (worldIn.getBlockState(position.east()).getBlock() == ModBlocks.SUGAR_BLOCK) {
                    ++i;
                }

                if (worldIn.getBlockState(position.north()).getBlock() == ModBlocks.SUGAR_BLOCK) {
                    ++i;
                }

                if (worldIn.getBlockState(position.south()).getBlock() == ModBlocks.SUGAR_BLOCK) {
                    ++i;
                }

                int j = 0;

                if (worldIn.isAirBlock(position.west())) {
                    ++j;
                }

                if (worldIn.isAirBlock(position.east())) {
                    ++j;
                }

                if (worldIn.isAirBlock(position.north())) {
                    ++j;
                }

                if (worldIn.isAirBlock(position.south())) {
                    ++j;
                }

                if (i == 3 && j == 1) {
                    IBlockState iblockstate1 = this.block.getDefaultState();
                    worldIn.setBlockState(position, iblockstate1, 2);
                    worldIn.immediateBlockTick(position, iblockstate1, rand);
                }

                return true;
            }
        }
    }
}

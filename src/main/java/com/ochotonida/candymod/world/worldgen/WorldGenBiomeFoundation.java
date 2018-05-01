package com.ochotonida.candymod.world.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenBiomeFoundation extends WorldGenerator {

    public final Biome biome;
    public int maxLength;
    public int minLength;
    public int chance;
    public List<IBlockState> stateList;

    public WorldGenBiomeFoundation(Biome biome, int minLength, int maxLength, int chance, IBlockState... states) {
        super();
        this.biome = biome;
        if (maxLength <= minLength) {
            throw new IllegalArgumentException();
        }
        this.chance = chance;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.stateList = Arrays.asList(states);
        if (stateList.size() < 1) {
            throw new IllegalArgumentException();
        }
    }

    public IBlockState getRandomState(Random rand) {
        if (stateList.size() == 1) {
            return stateList.get(0);
        }
        return stateList.get(rand.nextInt(stateList.size()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                BlockPos blockpos = pos.add(i + 8, 255, j + 8);
                if (worldIn.getBiome(blockpos) == biome) {
                    int blocksToPlace = 0;
                    IBlockState state = getRandomState(rand);
                    for (int k = 255; k >= 0; k--) {
                        if (worldIn.getBlockState(blockpos).getBlock() == biome.topBlock.getBlock() && rand.nextInt(chance) == 0) {
                            blocksToPlace = rand.nextInt(maxLength + 1 - minLength) + minLength;
                            state = getRandomState(rand);
                        } else if (blocksToPlace > 0 && worldIn.getBlockState(blockpos).getBlock() != biome.fillerBlock.getBlock()) {
                            this.setBlockAndNotifyAdequately(worldIn, blockpos, state);
                            blocksToPlace--;
                        }

                        blockpos = blockpos.down();
                    }
                }
            }
        }
        return true;
    }
}

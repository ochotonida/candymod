package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.cottoncandy.BlockCottonCandyLeaves;
import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CANDY_CANE_TYPE;

public class WorldGenCottonCandyTree extends WorldGenAbstractTree {

    private static final IBlockState LOG = ModBlocks.CANDY_CANE.getDefaultState()
            .withProperty(CANDY_CANE_TYPE, EnumCandyCane.WHITE);
    private static final IBlockState LEAF = ModBlocks.COTTON_CANDY_LEAVES.getDefaultState()
            .withProperty(BlockCottonCandyLeaves.DECAYABLE, Boolean.TRUE);

    public WorldGenCottonCandyTree(boolean notify) {
        super(notify);
    }

    private void setLeafBlock(World worldIn, BlockPos pos) {
        BlockPos blockpos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
        IBlockState state = worldIn.getBlockState(blockpos);

        if (state.getBlock().isAir(state, worldIn, blockpos)) {
            this.setBlockAndNotifyAdequately(worldIn, blockpos, LEAF);
        }
    }

    private void setAir(World worldIn, BlockPos pos) {
        if (worldIn.getBlockState(pos) == LEAF) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    private void placeLayer1(World worldIn, BlockPos pos) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos blockpos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                this.setLeafBlock(worldIn, blockpos);
            }
        }
    }

    private void placeLayer2(World worldIn, BlockPos pos) {
        this.placeLayerSquare(worldIn, pos);
        this.setAir(worldIn, pos.add(2, 0, 2));
        setAir(worldIn, pos.add(2, 0, -2));
        setAir(worldIn, pos.add(-2, 0, 2));
        setAir(worldIn, pos.add(-2, 0, -2));
    }

    private void placeLayer3(World worldIn, BlockPos pos) {
        placeLayerSquare(worldIn, pos);
        setLeafBlock(worldIn, pos.east(3));
        setLeafBlock(worldIn, pos.west(3));
        setLeafBlock(worldIn, pos.north(3));
        setLeafBlock(worldIn, pos.south(3));
    }

    private void placeLayer4(World worldIn, BlockPos pos) {
        placeLayerSquare(worldIn, pos);
        for (int i = -1; i <= 1; i++) {
            setLeafBlock(worldIn, pos.add(i, 0, 3));
            setLeafBlock(worldIn, pos.add(i, 0, -3));
            setLeafBlock(worldIn, pos.add(3, 0, i));
            setLeafBlock(worldIn, pos.add(-3, 0, i));
        }
    }

    private void placeLayerSquare(World worldIn, BlockPos pos) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos blockpos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                this.setLeafBlock(worldIn, blockpos);
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = rand.nextInt(3) + 8;

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int currentY = position.getY(); currentY <= position.getY() + 1 + height; ++currentY) {
                int k = 1;

                if (currentY == position.getY()) {
                    k = 0;
                }

                if (currentY >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
                        if (currentY >= 0 && currentY < worldIn.getHeight()) {
                            if (!this.isReplaceable(worldIn, blockpos$mutableblockpos.setPos(l, currentY, i1))) {
                                flag = false;
                                // TODO custom algorithm
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                BlockPos down = position.down();
                IBlockState state = worldIn.getBlockState(down);
                boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, net.minecraft.util.EnumFacing.UP, ModBlocks.COTTON_CANDY_SAPLING);

                if (isSoil && position.getY() < worldIn.getHeight() - height - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, down, position);

                    int currentY = -6 + height;

                    placeLayer1(worldIn, position.up(currentY++));
                    placeLayer2(worldIn, position.up(currentY++));
                    placeLayer3(worldIn, position.up(currentY++));
                    placeLayer4(worldIn, position.up(currentY++));
                    placeLayer4(worldIn, position.up(currentY++));
                    placeLayer3(worldIn, position.up(currentY++));
                    placeLayer2(worldIn, position.up(currentY++));
                    placeLayer1(worldIn, position.up(currentY));

                    for (int j2 = 0; j2 < height; ++j2) {
                        BlockPos upN = position.up(j2);
                        IBlockState state2 = worldIn.getBlockState(upN);

                        if (state2.getBlock().isAir(state2, worldIn, upN) || state2.getBlock().isLeaves(state2, worldIn, upN)) {
                            this.setBlockAndNotifyAdequately(worldIn, position.up(j2), LOG);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}

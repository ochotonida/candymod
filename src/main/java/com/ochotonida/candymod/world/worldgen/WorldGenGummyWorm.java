package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.enums.EnumAxis;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class WorldGenGummyWorm extends WorldGenerator {

    public WorldGenGummyWorm() {
        super();
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        BlockPos surfacePos = findSurfaceBlock(worldIn, position);

        if (worldIn.getBlockState(surfacePos).getBlock() == ModBlocks.GUMMY_WORM_BLOCK)
            return false;

        IBlockState state = ModBlocks.GUMMY_WORM_BLOCK.getDefaultState().withProperty(ModBlockProperties.GUMMY_TYPE, EnumGummy.random(rand));
        int r = rand.nextInt(3);
        switch (r) {
            case 0:
                return generateWormFlat(worldIn, surfacePos, rand.nextInt(10) + 7, state, rand);
            case 1:
                return generateWormStraight(worldIn, surfacePos, rand.nextInt(12) + 6, rand.nextInt(4) + 3, state);
            case 2:
                return generateWormArc(worldIn, surfacePos, state, rand);
        }
        return false;
    }

    private BlockPos findSurfaceBlock(World worldIn, BlockPos position) {
        BlockPos pos = new BlockPos(position.getX(), 255, position.getZ());
        while (worldIn.isAirBlock(pos)) {
            pos = pos.down();
        }
        return pos;
    }

    private boolean generateWormStraight(World worldIn, BlockPos position, int down, int up, IBlockState state) {
        for (int i = -down; i < up; i++) {
            worldIn.setBlockState(position.up(i), state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
        }
        return true;
    }

    private boolean generateWormArc(World worldIn, BlockPos position, IBlockState state, Random rand) {
        int height = rand.nextInt(2) + 2;
        int startDepth = rand.nextInt(4) + 4;
        EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y);
        BlockPos pos = position.down(startDepth);
        for (int i = 0; i <= height + startDepth; i++) {
            pos = pos.up();
            worldIn.setBlockState(pos, state, 2 | 16);
        }

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.fromFacingAxis(direction.getAxis()));
        for (int i = 0; i <= 2 + rand.nextInt(2); i++) {
            if (worldIn.isBlockLoaded(pos.offset(direction))) {
                pos = pos.offset(direction);
                worldIn.setBlockState(pos, state, 2 | 16);
            } else {
                break;
            }
        }

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y);
        while (worldIn.isAirBlock(pos.down())) {
            pos = pos.down();
            worldIn.setBlockState(pos, state, 2 | 16);
        }
        for (int i = 0; i <= 4 + rand.nextInt(4); i++) {
            pos = pos.down();
            worldIn.setBlockState(pos, state, 2 | 16);
        }
        return true;
    }

    private boolean generateWormFlat(World worldIn, BlockPos position, int length, IBlockState state, Random rand) {
        BlockPos pos = position.up();
        EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);
        int lastTurnDir = 0;
        boolean hasTurned = false;
        for (int i = 0; i <= length; i++) {
            worldIn.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.fromFacingAxis(direction.getAxis())), 2 | 16);

            // randomly change direction
            if (hasTurned) {
                hasTurned = false;
            } else if (rand.nextInt(3) == 0) {
                direction = direction.rotateY();
                if (lastTurnDir == 1 || (lastTurnDir == 0 && rand.nextBoolean())) {
                    direction = direction.rotateY().rotateY();
                    lastTurnDir = -1;
                } else {
                    lastTurnDir = 1;
                }
                hasTurned = true;
            }

            if (!worldIn.isBlockLoaded(pos.offset(direction))) {
                break;
            }


            // fall
            while (worldIn.isAirBlock(pos.down())) {
                pos = pos.down();
                worldIn.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
                i++;
            }

            // climb
            while (!worldIn.isAirBlock(pos.offset(direction))) {
                pos = pos.up();
                if (!worldIn.isAirBlock(pos)) {
                    return true;
                }
                worldIn.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
                i++;
            }

            pos = pos.offset(direction);
        }
        return true;
    }
}

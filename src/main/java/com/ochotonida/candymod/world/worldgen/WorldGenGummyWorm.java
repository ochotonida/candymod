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
    public boolean generate(World world, Random rand, BlockPos position) {
        BlockPos surfacePos = findSurfaceBlock(world, position);

        if (world.getBlockState(surfacePos).getBlock() == ModBlocks.GUMMY_WORM_BLOCK)
            return false;

        IBlockState state = ModBlocks.GUMMY_WORM_BLOCK.getDefaultState().withProperty(ModBlockProperties.GUMMY_TYPE, EnumGummy.random(rand));
        int r = rand.nextInt(3);
        switch (r) {
            case 0:
                return generateWormFlat(world, surfacePos, rand.nextInt(10) + 7, state, rand);
            case 1:
                return generateWormStraight(world, surfacePos, rand.nextInt(12) + 6, rand.nextInt(4) + 3, state);
            case 2:
                return generateWormArc(world, surfacePos, state, rand);
        }
        return false;
    }

    private BlockPos findSurfaceBlock(World world, BlockPos position) {
        BlockPos pos = new BlockPos(position.getX(), 255, position.getZ());
        while (isAirOrLiquid(world, pos)) {
            pos = pos.down();
        }
        return pos;
    }

    private boolean generateWormStraight(World world, BlockPos position, int down, int up, IBlockState state) {
        for (int i = -down; i < up; i++) {
            world.setBlockState(position.up(i), state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
        }
        return true;
    }

    private boolean generateWormArc(World world, BlockPos position, IBlockState state, Random rand) {
        int height = rand.nextInt(2) + 2;
        int startDepth = rand.nextInt(4) + 4;
        EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y);
        BlockPos pos = position.down(startDepth);
        for (int i = 0; i <= height + startDepth; i++) {
            pos = pos.up();
            world.setBlockState(pos, state, 2 | 16);
        }

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.fromFacingAxis(direction.getAxis()));
        for (int i = 0; i <= 2 + rand.nextInt(2); i++) {
            if (world.isBlockLoaded(pos.offset(direction))) {
                pos = pos.offset(direction);
                world.setBlockState(pos, state, 2 | 16);
            } else {
                break;
            }
        }

        state = state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y);
        while (isAirOrLiquid(world, pos.down())) {
            pos = pos.down();
            world.setBlockState(pos, state, 2 | 16);
        }
        for (int i = 0; i <= 4 + rand.nextInt(4); i++) {
            pos = pos.down();
            world.setBlockState(pos, state, 2 | 16);
        }
        return true;
    }

    private boolean generateWormFlat(World world, BlockPos position, int length, IBlockState state, Random rand) {
        BlockPos pos = position.up();
        EnumFacing direction = EnumFacing.Plane.HORIZONTAL.random(rand);
        int lastTurnDir = 0;
        boolean hasTurned = false;
        for (int i = 0; i <= length; i++) {
            world.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.fromFacingAxis(direction.getAxis())), 2 | 16);

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

            if (!world.isBlockLoaded(pos.offset(direction))) {
                break;
            }


            // fall
            while (isAirOrLiquid(world, pos.down())) {
                pos = pos.down();
                world.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
                i++;
            }

            // climb
            while (!isAirOrLiquid(world, pos.offset(direction))) {
                pos = pos.up();
                if (!isAirOrLiquid(world, pos)) {
                    return true;
                }
                world.setBlockState(pos, state.withProperty(ModBlockProperties.AXIS, EnumAxis.Y), 2 | 16);
                i++;
            }

            pos = pos.offset(direction);
        }
        return true;
    }

    private boolean isAirOrLiquid(World world, BlockPos pos) {
        return world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().isLiquid();
    }
}

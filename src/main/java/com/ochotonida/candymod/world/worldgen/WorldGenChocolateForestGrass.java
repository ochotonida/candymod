package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.chocolate.BlockChocolateBar;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class WorldGenChocolateForestGrass extends WorldGenerator {

    private static final IBlockState STATE_B = ModBlocks.CHOCOLATE_BAR.getDefaultState();
    private IBlockState state_m = ModBlocks.CHOCOLATE_MUSHROOM.getDefaultState();

    public WorldGenChocolateForestGrass() {
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        for (IBlockState iblockstate = worldIn.getBlockState(pos); (iblockstate.getBlock().isAir(iblockstate, worldIn, pos) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, pos)) && pos.getY() > 0; iblockstate = worldIn.getBlockState(pos)) {
            pos = pos.down();
        }

        int r = rand.nextInt(4);

        if (r < 3) {
            state_m = state_m.withProperty(CHOCOLATE_TYPE, EnumChocolate.byMetadata(r));

            for (int i = 0; i < 70; i++) {
                BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

                if (worldIn.isAirBlock(blockpos) && ModBlocks.CHOCOLATE_MUSHROOM.canBlockStay(worldIn, blockpos, state_m)) {
                    worldIn.setBlockState(blockpos, state_m, 2);
                }
            }
            return true;
        }
        for (int i = 0; i < 30; ++i) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && ModBlocks.CHOCOLATE_BAR.canBlockStay(worldIn, blockpos)) {
                EnumChocolate type = EnumChocolate.byMetadata(rand.nextInt(EnumChocolate.META_LOOKUP.length));
                EnumFacing facing = EnumFacing.Plane.HORIZONTAL.random(rand);
                worldIn.setBlockState(blockpos, STATE_B.withProperty(CHOCOLATE_TYPE, type).withProperty(BlockChocolateBar.FACING, facing), 2);
            }
        }
        return true;
    }
}

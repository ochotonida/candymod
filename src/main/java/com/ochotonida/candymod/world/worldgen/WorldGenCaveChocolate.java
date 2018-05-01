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

public class WorldGenCaveChocolate extends WorldGenerator {

    public static final IBlockState STATE = ModBlocks.CHOCOLATE_BAR.getDefaultState();

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        while (!(ModBlocks.CHOCOLATE_BAR.canBlockStay(worldIn, pos) && worldIn.isAirBlock(pos)) && pos.getY() < worldIn.getSeaLevel() - 2) {
            pos = pos.up();
        }

        for (int i = 0; i < 20; ++i) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(6) - rand.nextInt(6), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && ModBlocks.CHOCOLATE_BAR.canBlockStay(worldIn, blockpos)) {
                EnumChocolate type = EnumChocolate.byMetadata(rand.nextInt(EnumChocolate.META_LOOKUP.length));
                EnumFacing facing = EnumFacing.Plane.HORIZONTAL.random(rand);
                worldIn.setBlockState(blockpos, STATE.withProperty(CHOCOLATE_TYPE, type).withProperty(BlockChocolateBar.FACING, facing), 2);
            }
        }

        return true;
    }
}

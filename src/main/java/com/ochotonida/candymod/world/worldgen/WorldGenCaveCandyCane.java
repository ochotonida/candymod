package com.ochotonida.candymod.world.worldgen;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;

import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CANDY_CANE_TYPE;

public class WorldGenCaveCandyCane extends WorldGenerator {

    public static final IBlockState STATE = ModBlocks.CANDY_CANE.getDefaultState();

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(World worldIn, Random rand, BlockPos pos) {
        while (!(ModBlocks.CANDY_CANE.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos)) && pos.getY() < worldIn.getSeaLevel() - 2) {
            pos = pos.up();
        }

        EnumCandyCane type;

        switch (rand.nextInt(3)) {
            case 0:
                type = EnumCandyCane.WHITE_GREEN;
                break;
            default:
                type = EnumCandyCane.WHITE_RED;
                break;
        }

        for (int i = 0; i < 16 + rand.nextInt(8); i++) {
            if (worldIn.isAirBlock(pos) && ModBlocks.CANDY_CANE.canPlaceBlockAt(worldIn, pos)) {
                worldIn.setBlockState(pos, STATE.withProperty(CANDY_CANE_TYPE, type));
                pos = pos.up();
            } else {
                if (i == 0) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
}

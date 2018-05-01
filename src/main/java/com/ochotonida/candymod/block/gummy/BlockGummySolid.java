package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.GUMMY_TYPE;

public class BlockGummySolid extends BlockGummyBase {

    public BlockGummySolid() {
        super("hardened_gummy_block");
        this.setHardness(0.5F);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModBlocks.GUMMY_BLOCK.getItemDropped(ModBlocks.GUMMY_BLOCK.getDefaultState().withProperty(GUMMY_TYPE, state.getValue(GUMMY_TYPE)), rand, fortune);
    }
}

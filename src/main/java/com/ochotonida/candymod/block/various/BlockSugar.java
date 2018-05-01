package com.ochotonida.candymod.block.various;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockSugar extends Block {

    public BlockSugar() {
        super(Material.ROCK);
        this.setRegistryName("sugar_block");
        this.setUnlocalizedName("sugar_block");
        this.setHardness(1.5F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.SUGAR_ROCK;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 4;
    }
}

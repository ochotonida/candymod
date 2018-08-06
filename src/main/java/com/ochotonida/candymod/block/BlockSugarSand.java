package com.ochotonida.candymod.block;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockSugarSand extends BlockFalling {

    public BlockSugarSand() {
        super();
        this.setRegistryName("sugar_sand_block");
        this.setUnlocalizedName("sugar_sand_block");
        this.setHardness(0.5F);
        this.setSoundType(SoundType.SAND);
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
        return Items.SUGAR;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getDustColor(IBlockState state) {
        return ~0xa6b6bf;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.STONE;
    }
}

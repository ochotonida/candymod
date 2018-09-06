package com.ochotonida.candymod.block;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModConfig;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockWaferStick extends BlockStackable {

    public BlockWaferStick() {
        super(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY, ModConfig.stackableTreeTrunks, ModConfig.recursiveTreeTrunks, true);
        this.setRegistryName("wafer_stick_block");
        this.setUnlocalizedName("wafer_stick_block");
        this.setHardness(0.9F);
        this.setHarvestLevel("axe", 0);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
            return BlockFaceShape.UNDEFINED;
        }
        return BlockFaceShape.SOLID;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return side != EnumFacing.UP && side != EnumFacing.DOWN;
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}

package com.ochotonida.candymod.block.cottoncandy;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.block.ModSoundTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCottonCandyGrass extends BlockBush implements IShearable {

    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);

    public BlockCottonCandyGrass() {
        super(Material.VINE, MapColor.PINK);
        this.setSoundType(ModSoundTypes.COTTON_CANDY);
        this.setRegistryName("cotton_candy_grass_block");
        this.setUnlocalizedName("cotton_candy_grass_block");
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.COTTON_CANDY;
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean canSustainBush(IBlockState state) {
        return state.getBlock() == ModBlocks.CANDY_GRASS
                || state.getBlock() == ModBlocks.CANDY_SOIL
                || state.getBlock() == Blocks.DIRT
                || state.getBlock() == Blocks.GRASS;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(30) == 0 ? random.nextInt(3) + 1 : 0;
    }

    @Nonnull
    @Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack stack, IBlockAccess world, BlockPos pos) {
        return stack.getItem() instanceof ItemShears;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack stack, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Item.getItemFromBlock(ModBlocks.COTTON_CANDY_GRASS)));
        return drops;
    }
}

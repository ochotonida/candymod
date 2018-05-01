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
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCottonCandyPlant extends BlockBush implements IPlantable, IShearable {

    private static final AxisAlignedBB AABB = new AxisAlignedBB((2.5 / 16), (0.0 / 16), (2.5 / 16), (13.5 / 16), (15.5 / 16), (13.5 / 16));

    public BlockCottonCandyPlant() {
        super(Material.VINE, MapColor.PINK);
        this.setSoundType(ModSoundTypes.COTTON_CANDY);
        this.setHardness(0.2F);
        this.setUnlocalizedName("cotton_candy_plant_block");
        this.setRegistryName("cotton_candy_plant_block");
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.COTTON_CANDY;
    }

    @Override
    public boolean canSustainBush(IBlockState state) {
        return state.getBlock() == ModBlocks.CANDY_GRASS
                || state.getBlock() == ModBlocks.CANDY_SOIL
                || state.getBlock() == Blocks.DIRT
                || state.getBlock() == Blocks.GRASS;
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return item.getItem() instanceof ItemShears;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Item.getItemFromBlock(ModBlocks.COTTON_CANDY_PLANT)));
        return drops;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }
}

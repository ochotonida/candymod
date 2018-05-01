package com.ochotonida.candymod.block.cottoncandy;

import com.google.common.collect.Lists;
import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.block.ModSoundTypes;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

public class BlockCottonCandyLeaves extends BlockLeaves {

    public BlockCottonCandyLeaves() {
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setUnlocalizedName("cotton_candy_leaves_block");
        this.setRegistryName("cotton_candy_leaves_block");
        this.setSoundType(ModSoundTypes.COTTON_CANDY);
        this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, false));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.PINK;
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(DECAYABLE, false);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.COTTON_CANDY_SAPLING);
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(40) == 0 ? 1 : 0;
    }

    @Override
    public void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if (worldIn.rand.nextInt(chance / 10) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.COTTON_CANDY));
        }
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack stack, IBlockAccess world, BlockPos pos, int fortune) {
        final IBlockState state = world.getBlockState(pos);
        return Lists.newArrayList(this.getSilkTouchDrop(state));
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, 0);
    }

    @Nonnull
    @Override
    public EnumType getWoodType(int meta) {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        // first bit
        final int decayable = meta % 2;
        // second bit
        final int check_decay = (meta >> 1) % 2;

        return this.getDefaultState().withProperty(DECAYABLE, decayable == 0).withProperty(CHECK_DECAY, check_decay == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;

        if (!state.getValue(DECAYABLE)) {
            meta |= 1; // first bit
        }
        if (state.getValue(CHECK_DECAY)) {
            meta |= 2; // second bit
        }

        return meta;
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }
}

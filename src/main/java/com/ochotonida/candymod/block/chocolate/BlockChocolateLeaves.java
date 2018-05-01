package com.ochotonida.candymod.block.chocolate;

import com.google.common.collect.Lists;
import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockChocolateLeaves extends BlockLeaves {

    public BlockChocolateLeaves() {
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setUnlocalizedName("chocolate_leaves_block");
        this.setRegistryName("chocolate_leaves_block");
        this.setSoundType(SoundType.PLANT);
        this.leavesFancy = true;
        this.setDefaultState(this.blockState.getBaseState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, false).withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        switch (state.getValue(CHOCOLATE_TYPE)) {
            case MILK:
                return MapColor.BROWN;
            case WHITE:
                return MapColor.SAND;
            case DARK:
                return MapColor.BROWN_STAINED_HARDENED_CLAY;
        }
        return this.blockMapColor;
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(DECAYABLE, false).withProperty(CHOCOLATE_TYPE, EnumChocolate.byMetadata(meta));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumChocolate.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.CHOCOLATE_SAPLING);
    }

    @Override
    public void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
        if (worldIn.rand.nextInt(chance / 10) == 0) {
            spawnAsEntity(worldIn, pos, new ItemStack(ModItems.CHOCOLATE_BAR, 1, state.getValue(CHOCOLATE_TYPE).getMetadata()));
        }
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        final IBlockState state = world.getBlockState(pos);
        return Lists.newArrayList(this.getSilkTouchDrop(state));
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(CHOCOLATE_TYPE).getMetadata());
    }

    @Nonnull
    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, DECAYABLE, CHECK_DECAY, CHOCOLATE_TYPE);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        // first bit
        int decayable = meta % 2;
        // second bit
        int check_decay = (meta >> 1) % 2;
        EnumChocolate type = EnumChocolate.byMetadata(meta >> 2);

        return this.getDefaultState().withProperty(DECAYABLE, decayable == 0).withProperty(CHECK_DECAY, check_decay == 1).withProperty(CHOCOLATE_TYPE, type);
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
        EnumChocolate type = state.getValue(CHOCOLATE_TYPE);
        meta |= (type.getMetadata() << 2);

        return meta;
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }
}
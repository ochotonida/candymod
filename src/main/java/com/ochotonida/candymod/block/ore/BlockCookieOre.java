package com.ochotonida.candymod.block.ore;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.IS_SUGAR_VARIANT;

public class BlockCookieOre extends Block {
    public BlockCookieOre() {
        super(Material.ROCK);
        this.setRegistryName("cookie_ore_block");
        this.setUnlocalizedName("cookie_ore_block");
        this.setHardness(1.5F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);

        this.setDefaultState(this.blockState.getBaseState().withProperty(IS_SUGAR_VARIANT, false));
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, IS_SUGAR_VARIANT);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(IS_SUGAR_VARIANT, meta != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(IS_SUGAR_VARIANT) ? 1 : 0;
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.COOKIE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSilkHarvest() {
        return true;
    }

    @Override
    public int quantityDropped(Random rand) {
        return 1;
    }

    @Override
    @ParametersAreNonnullByDefault
    public int quantityDroppedWithBonus(int fortune, Random rand) {
        if (fortune > 0) {
            return (rand.nextInt(fortune + 2) + 1) * this.quantityDropped(rand);
        }
        return this.quantityDropped(rand);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World) world).rand : new Random();
        return MathHelper.getInt(rand, 0, 3);
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(state));
    }
}

package com.ochotonida.candymod.block.various;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BlockCookieOre extends Block {
    public BlockCookieOre() {
        super(Material.ROCK);
        this.setRegistryName("cookie_ore_block");
        this.setUnlocalizedName("cookie_ore_block");
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
        return Items.COOKIE;
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
}

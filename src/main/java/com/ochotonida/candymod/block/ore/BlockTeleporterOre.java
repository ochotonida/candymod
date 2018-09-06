package com.ochotonida.candymod.block.ore;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BlockTeleporterOre extends Block {

    public BlockTeleporterOre() {
        super(Material.ROCK);
        this.setRegistryName("teleporter_ore_block");
        this.setUnlocalizedName("teleporter_ore_block");
        this.setHardness(1.5F);
        this.setHarvestLevel("pickaxe", 1);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.TELEPORTER;
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
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0) {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }
            return this.quantityDropped(random) * (i + 1);
        }
        return this.quantityDropped(random);
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        return MathHelper.getInt(rand, 3, 7);
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }
}

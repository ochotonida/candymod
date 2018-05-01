package com.ochotonida.candymod.block.cottoncandy;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModSoundTypes;
import com.ochotonida.candymod.world.worldgen.WorldGenCottonCandyTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BlockCottonCandySapling extends BlockSapling {

    public BlockCottonCandySapling() {
        this.setRegistryName("cotton_candy_sapling_block");
        this.setUnlocalizedName("cotton_candy_sapling_block");
        this.setSoundType(ModSoundTypes.COTTON_CANDY);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, @Nonnull NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, this.getMetaFromState(this.getDefaultState())));
    }

    @Nonnull
    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE, TYPE);
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STAGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isReplaceable(IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        Block ground = worldIn.getBlockState(pos.down()).getBlock();
        if (ground == ModBlocks.CANDY_SOIL || ground == ModBlocks.CANDY_GRASS) {
            return true;
        }
        return super.canPlaceBlockAt(worldIn, pos);
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) {
            return;
        }

        WorldGenerator worldgenerator = new WorldGenCottonCandyTree(true);
        worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

        if (!worldgenerator.generate(worldIn, rand, pos)) {
            worldIn.setBlockState(pos, state, 4);
        }
    }
}

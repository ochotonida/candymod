package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.block.BlockColoring;
import com.ochotonida.candymod.interfaces.IBlockColored;
import com.ochotonida.candymod.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockColaGummy extends Block implements IBlockColored {

    public BlockColaGummy() {
        super(Material.CLAY, MapColor.BROWN_STAINED_HARDENED_CLAY);
        this.setRegistryName("cola_gummy_block");
        this.setUnlocalizedName("cola_gummy_block");
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setDefaultSlipperiness(0.6F);
        this.setHardness(0.4F);
        this.setHarvestLevel("shovel", 0);
        this.setSoundType(SoundType.SLIME);
        ClientProxy.addColoredBlock(this);
    }

    @Nonnull
    public Item createItemBlock() {
        //noinspection ConstantConditions
        return new ItemBlock(this).setRegistryName(this.getRegistryName());
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return ModConfig.isGummyTransluscent ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        if (!ModConfig.isGummyTransluscent)
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        return blockState.getBlock() != iblockstate.getBlock() && blockState.getBlock() != ModBlocks.GUMMY_BLOCK;
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (entityIn.isSneaking()) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
        } else {
            entityIn.fall(fallDistance, 0.0F);
        }
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn) {
        if (entityIn.isSneaking() || Math.abs(entityIn.motionY * 0.8) < 0.3D) {
            super.onLanded(worldIn, entityIn);
        } else if (entityIn.motionY < 0.0D) {
            entityIn.motionY = -entityIn.motionY * 0.8;

            if (!(entityIn instanceof EntityLivingBase)) {
                entityIn.motionY *= 0.8D;
            }
        }
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
            entityIn.motionX *= 0.8;
            entityIn.motionZ *= 0.8;
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Nonnull
    @Override
    public IBlockColor getBlockColor() {
        return BlockColoring.COLA_GUMMY;
    }

    @Nonnull
    @Override
    public IItemColor getItemColor() {
        return BlockColoring.ITEM_COLA_GUMMY;
    }
}

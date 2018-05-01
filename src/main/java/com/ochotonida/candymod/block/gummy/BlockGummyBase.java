package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.block.BlockColoring;
import com.ochotonida.candymod.block.ModSoundTypes;
import com.ochotonida.candymod.enums.EnumGummy;
import com.ochotonida.candymod.interfaces.IBlockColored;
import com.ochotonida.candymod.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import static com.ochotonida.candymod.block.ModBlockProperties.GUMMY_TYPE;

public class BlockGummyBase extends Block implements IBlockColored {

    protected final String name;

    public BlockGummyBase(String name) {
        super(Material.CLAY);
        this.name = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setHardness(0.4F);
        this.setDefaultSlipperiness(0.6F);
        this.setSoundType(ModSoundTypes.GUMMY);
        this.setHarvestLevel("shovel", 0);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(GUMMY_TYPE, EnumGummy.RED));
        ClientProxy.addColoredBlock(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public IBlockColor getBlockColor() {
        return BlockColoring.GUMMY;
    }

    @Override
    public IItemColor getItemColor() {
        return BlockColoring.ITEM_GUMMY;
    }

    @Nonnull
    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, GUMMY_TYPE);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(GUMMY_TYPE, EnumGummy.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(GUMMY_TYPE).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumGummy.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        switch (state.getValue(GUMMY_TYPE)) {
            case ORANGE:
                return MapColor.YELLOW_STAINED_HARDENED_CLAY;
            case YELLOW:
                return MapColor.YELLOW;
            case GREEN:
                return MapColor.LIME;
            case WHITE:
                return MapColor.SAND;
            case RED:
            default:
                return MapColor.TNT;
        }
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
}

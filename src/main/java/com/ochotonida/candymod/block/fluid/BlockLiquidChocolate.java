package com.ochotonida.candymod.block.fluid;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.ochotonida.candymod.block.fluid.ModFluids.LIQUID_CHOCOLATE;

public class BlockLiquidChocolate extends BlockFluidClassic {

    public BlockLiquidChocolate() {
        super(LIQUID_CHOCOLATE, Material.WATER);
        this.setRegistryName("liquid_chocolate_block");
        this.setUnlocalizedName("liquid_chocolate_block");
    }

    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighbourPos) {
        this.checkForMixing(world, pos, state);
        super.neighborChanged(state, world, pos, neighborBlock, neighbourPos);
    }

    protected void checkForMixing(World world, BlockPos pos, IBlockState state) {
        if (state.getValue(LEVEL) != 0) {
            return;
        }
        boolean flag = false;
        for (EnumFacing enumfacing : EnumFacing.values()) {
            if (enumfacing != EnumFacing.DOWN && world.getBlockState(pos.offset(enumfacing)).getBlock() == Blocks.WATER || world.getBlockState(pos.offset(enumfacing)).getBlock() == Blocks.FLOWING_WATER) {
                flag = true;
                break;
            }
        }
        if (flag) {
            world.setBlockState(pos, ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.MILK));
            this.triggerMixEffects(world, pos);
        }
    }

    protected void triggerMixEffects(World worldIn, BlockPos pos) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();
        worldIn.playSound(null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}

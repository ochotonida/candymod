package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;


public class BlockGummy extends BlockGummyBase {

    public BlockGummy() {
        super("gummy_block");
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return ModConfig.isGummyTransluscent && Minecraft.getMinecraft().gameSettings.fancyGraphics ? BlockRenderLayer.TRANSLUCENT : BlockRenderLayer.SOLID;
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
        if (!ModConfig.isGummyTransluscent || blockState.getBlock() != blockAccess.getBlockState(pos.offset(side)).getBlock()) {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
        return false;
    }
}

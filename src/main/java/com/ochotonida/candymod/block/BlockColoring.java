package com.ochotonida.candymod.block;

import com.ochotonida.candymod.interfaces.IBlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlockColoring {

    public static final IBlockColor COLA_GUMMY = ((state, worldIn, pos, tintIndex) -> 0xaa7217);
    public static final IItemColor ITEM_COLA_GUMMY = ((stack, tintIndex) -> 0xb76d19);

    public static final IBlockColor GUMMY = (state, worldIn, pos, tintIndex) -> state.getValue(ModBlockProperties.GUMMY_TYPE).getColor();
    @SuppressWarnings("deprecation")
    public static final IItemColor ITEM_GUMMY = (stack, tintIndex) -> {
        IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
        IBlockColor blockColor = ((IBlockColored) state.getBlock()).getBlockColor();
        return blockColor == null ? 0xFFFFFF : blockColor.colorMultiplier(state, null, null, tintIndex);
    };
}

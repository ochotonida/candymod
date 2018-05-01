package com.ochotonida.candymod.interfaces;

import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("SameReturnValue")
public interface IBlockColored {
    @SideOnly(Side.CLIENT)
    IBlockColor getBlockColor();

    @SideOnly(Side.CLIENT)
    IItemColor getItemColor();
}

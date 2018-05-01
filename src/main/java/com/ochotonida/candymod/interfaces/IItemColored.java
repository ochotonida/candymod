package com.ochotonida.candymod.interfaces;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemColored {
    @SideOnly(Side.CLIENT)
    IItemColor getItemColor();
}

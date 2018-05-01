package com.ochotonida.candymod.item;

import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemColoring {
    public static final IItemColor ITEM_GUMMY = (stack, tintIndex) -> EnumGummy.byMetadata(stack.getMetadata()).getColor();
}
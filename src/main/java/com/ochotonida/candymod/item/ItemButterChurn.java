package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.item.Item;

public class ItemButterChurn extends Item {

    public ItemButterChurn() {
        super();
        this.setUnlocalizedName("butter_churn");
        this.setRegistryName("butter_churn");
        this.setMaxStackSize(1);
        this.setCreativeTab(CandyMod.TAB_ITEMS);
    }

    public void registerItemModel() {
        CandyMod.proxy.registerItemRenderer(this, 0, "butter_churn");
    }
}

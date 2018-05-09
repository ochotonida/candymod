package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemCandyCane extends ModFood {

    public ItemCandyCane() {
        super("candy_cane", "foodCandyCane", 5, 0.6F);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CandyMod.TAB_ITEMS);
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName(stack.getMetadata());
    }

    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName() + ":" + EnumCandyCane.byMetadata(meta).getName();
    }

    @Override
    public void registerItemModel() {
        for (EnumCandyCane value : EnumCandyCane.values()) {
            CandyMod.proxy.registerItemRenderer(this, value.getMetadata(), "candy_cane/" + "candy_cane_" + value.getName());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumCandyCane enumcandycane : EnumCandyCane.values()) {
                items.add(new ItemStack(this, 1, enumcandycane.getMetadata()));
            }
        }
    }
}

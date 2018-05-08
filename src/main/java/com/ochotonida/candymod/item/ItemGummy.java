package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGummy extends ModFood {

    public ItemGummy(String name, String oreName, int healAmount, float saturation) {
        super(name, oreName, healAmount, saturation);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumGummy value : EnumGummy.values()) {
                items.add(new ItemStack(this, 1, value.getMetadata()));
            }
        }
    }

    @Override
    public void registerItemModel() {
        for (EnumGummy value : EnumGummy.values()) {
            CandyMod.proxy.registerItemRenderer(this, value.getMetadata(), this.name + "_" + value.getName(), "gummy/");
        }
    }
}

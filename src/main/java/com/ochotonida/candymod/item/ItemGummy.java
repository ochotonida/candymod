package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGummy extends ModFoodItem {

    public ItemGummy(String name, int healAmount, float saturation, String... oreNames) {
        super(name, healAmount, saturation, oreNames);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    protected void registerOreNames() {
        for (EnumGummy enumGummy : EnumGummy.values()) {
            ItemStack stack = new ItemStack(this, 1, enumGummy.getMetadata());
            for (String oreName : oreNames) {
                OreDictionary.registerOre(oreName, stack);
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumGummy enumGummy : EnumGummy.values()) {
                items.add(new ItemStack(this, 1, enumGummy.getMetadata()));
            }
        }
    }

    @Override
    public void registerItemModel() {
        for (EnumGummy enumGummy : EnumGummy.values()) {
            CandyMod.proxy.registerItemRenderer(this, enumGummy.getMetadata(), "gummy/" + this.name + "_" + enumGummy.getName());
        }
    }
}

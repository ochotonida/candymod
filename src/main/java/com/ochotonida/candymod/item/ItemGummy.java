package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumGummy;
import com.ochotonida.candymod.interfaces.IItemColored;
import com.ochotonida.candymod.proxy.ClientProxy;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemGummy extends ModFood implements IItemColored {

    public ItemGummy(String name, String oreName, int healAmount, float saturation) {
        super(name, oreName, healAmount, saturation);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        ClientProxy.addColoredItem(this);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumGummy enumgummy : EnumGummy.values()) {
                items.add(new ItemStack(this, 1, enumgummy.getMetadata()));
            }
        }
    }

    @Override
    public void registerItemModel() {
        for (EnumGummy value : EnumGummy.values()) {
            CandyMod.proxy.registerItemRenderer(this, value.getMetadata(), this.name);
        }
    }

    @Override
    public IItemColor getItemColor() {
        return ItemColoring.ITEM_GUMMY;
    }
}

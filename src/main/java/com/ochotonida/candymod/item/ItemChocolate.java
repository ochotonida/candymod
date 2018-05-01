package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemChocolate extends ModFood {

    /**
     * Initializes chocolate based food item
     */
    public ItemChocolate(String name, String oreName) {
        this(name, oreName, 6, 0.6F);
    }

    public ItemChocolate(String name, String oreName, int healAmount, float saturation) {
        super(name, oreName, healAmount, saturation);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName(stack.getMetadata());
    }

    /**
     * Get the unlocalized name of the item based on its metadata
     */
    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName() + ":" + EnumChocolate.byMetadata(meta).getName();
    }

    /**
     * Registers the item models for each chocolate type
     */
    @Override
    public void registerItemModel() {
        for (EnumChocolate value : EnumChocolate.values()) {
            CandyMod.proxy.registerItemRenderer(this, value.getMetadata(), this.name + "_" + value.getName());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumChocolate enumchocolate : EnumChocolate.values()) {
                items.add(new ItemStack(this, 1, enumchocolate.getMetadata()));
            }
        }
    }

}

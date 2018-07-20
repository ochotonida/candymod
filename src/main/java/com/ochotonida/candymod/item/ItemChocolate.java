package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemChocolate extends ModFoodItem {

    public ItemChocolate(String name, String... oreNames) {
        this(name, 6, 0.6F, oreNames);
    }

    public ItemChocolate(String name, int healAmount, float saturation, String... oreNames) {
        super(name, healAmount, saturation, oreNames);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    protected void registerOreNames() {
        for (EnumChocolate enumChocolate : EnumChocolate.values()) {
            ItemStack stack = new ItemStack(this, 1, enumChocolate.getMetadata());
            for (String oreName : oreNames) {
                OreDictionary.registerOre(oreName, stack);
            }
        }
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName(stack.getMetadata());
    }

    /**
     * Return the unlocalized name of the item based on its metadata
     */
    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName() + ":" + EnumChocolate.byMetadata(meta).getName();
    }

    /**
     * Register the item models for each chocolate type
     */
    @Override
    public void registerItemModel() {
        for (EnumChocolate enumChocolate : EnumChocolate.values()) {
            CandyMod.proxy.registerItemRenderer(this, enumChocolate.getMetadata(), "chocolate/" + this.name + "_" + enumChocolate.getName());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumChocolate enumChocolate : EnumChocolate.values()) {
                items.add(new ItemStack(this, 1, enumChocolate.getMetadata()));
            }
        }
    }
}

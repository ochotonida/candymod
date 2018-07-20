package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemCandyCane extends ModFoodItem {

    public ItemCandyCane() {
        super("candy_cane", 5, 0.6F, "foodCandycane");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    protected void registerOreNames() {
        for (EnumCandyCane enumCandyCane : EnumCandyCane.values()) {
            ItemStack stack = new ItemStack(this, 1, enumCandyCane.getMetadata());
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

    public String getUnlocalizedName(int meta) {
        return this.getUnlocalizedName() + ":" + EnumCandyCane.byMetadata(meta).getName();
    }

    @Override
    public void registerItemModel() {
        for (EnumCandyCane enumCandyCane : EnumCandyCane.values()) {
            CandyMod.proxy.registerItemRenderer(this, enumCandyCane.getMetadata(), "candy_cane/" + "candy_cane_" + enumCandyCane.getName());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (EnumCandyCane enumCandyCane : EnumCandyCane.values()) {
                items.add(new ItemStack(this, 1, enumCandyCane.getMetadata()));
            }
        }
    }
}

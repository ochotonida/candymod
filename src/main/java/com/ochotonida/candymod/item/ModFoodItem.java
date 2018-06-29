package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class ModFoodItem extends ItemFood {

    private static final List<ModFoodItem> ORE_LIST = new ArrayList<>();
    public final String name;
    public String oreName;

    /**
     * Initialize a basic food item
     */
    public ModFoodItem(String name, int healAmount, float saturation) {
        super(healAmount, saturation, false);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.name = name;
        this.setCreativeTab(CandyMod.TAB_ITEMS);
    }

    /**
     * Initialize a basic food item with an oreDictionary name
     */
    public ModFoodItem(String name, String oreName, int healAmount, float saturation) {
        this(name, healAmount, saturation);
        this.oreName = oreName;
        ORE_LIST.add(this);
    }

    /**
     * Add all instances of ModFoodItem to the oreDictionary
     */
    public static void initOreDict() {
        for (ModFoodItem item : ORE_LIST) {
            OreDictionary.registerOre(item.oreName, item);
        }
    }

    /**
     * Register the item model
     */
    public void registerItemModel() {
        CandyMod.proxy.registerItemRenderer(this, 0, this.name);
    }

}

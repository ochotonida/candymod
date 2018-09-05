package com.ochotonida.candymod.item;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.item.ItemFood;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

public class ModFoodItem extends ItemFood {

    private static final List<ModFoodItem> FOOD_ITEMS = new ArrayList<>();
    protected final String name;
    protected Set<String> oreNames = new HashSet<>();

    /**
     * Initialize a basic food item
     */
    public ModFoodItem(String name, int healAmount, float saturation) {
        super(healAmount, saturation, false);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.name = name;
        this.setCreativeTab(CandyMod.TAB_ITEMS);
        FOOD_ITEMS.add(this);
    }

    /**
     * Initialize a basic food item with oredictionary names
     */
    public ModFoodItem(String name, int healAmount, float saturation, String... oreNames) {
        this(name, healAmount, saturation);
        this.oreNames.addAll(Arrays.asList(oreNames));
    }

    public static ModFoodItem[] getFoodItems() {
        return FOOD_ITEMS.toArray(new ModFoodItem[]{});
    }

    /**
     * Register the oredictionary names for this item
     */
    protected void registerOreNames() {
        for (String oreName : oreNames) {
            OreDictionary.registerOre(oreName, this);
        }
    }

    /**
     * Register all oredictionary names
     */
    public static void initOreDict() {
        for (ModFoodItem item : FOOD_ITEMS) {
            item.registerOreNames();
        }
    }

    /**
     * Register the item model
     */
    public void registerItemModel() {
        CandyMod.proxy.registerItemRenderer(this, 0, this.name);
    }

    /**
     * Register all item models
     */
    public static void initItemModels() {
        for (ModFoodItem item : FOOD_ITEMS) {
            item.registerItemModel();
        }
    }
}

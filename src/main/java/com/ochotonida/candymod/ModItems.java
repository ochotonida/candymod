package com.ochotonida.candymod;

import com.ochotonida.candymod.item.*;
import com.ochotonida.candymod.item.tools.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("unused")
public final class ModItems {

    // simple food items
    public static final ModFoodItem BUTTER = new ModFoodItem("butter", 1, 1.0F, "foodButter");
    public static final ModFoodItem COTTON_CANDY = new ModFoodItem("cotton_candy", 4, 0.5F, "foodCottoncandy");
    public static final ModFoodItem WAFER_STICK = new ModFoodItem("wafer_stick", 5, 0.6F, "foodWaferstick", "stickWood");
    public static final ModFoodItem SUGAR_ROCK = new ModFoodItem("sugar_rock", 4, 0.2F, "foodRockcandy");

    // food items
    public static final ItemCandyCane CANDY_CANE = new ItemCandyCane();
    public static final ItemChocolate BROWNIE = new ItemChocolate("brownie", 4, 0.5F, "foodBrownie");
    public static final ItemChocolate CHOCOLATE_BAR = new ItemChocolate("chocolate_bar", "foodChocolatebar", "foodChocolate");
    public static final ItemChocolate CHOCOLATE_EGG = new ItemChocolate("chocolate_egg", 7, 0.8F, "foodChocolateegg");
    public static final ItemGummy GUMMY = new ItemGummy("gummy", 4, 0.6F, "foodGummy");
    public static final ItemGummy GUMMY_WORM = new ItemGummy("gummy_worm", 6, 1F, "foodGummyworm");
    public static final ItemTeleporter TELEPORTER = new ItemTeleporter();

    // tool
    public static final ToolSet TOOLSET_CHOCOLATE = new ToolSetChocolate();
    public static final ToolSet TOOLSET_COTTON_CANDY = new ToolSetCottonCandy();

    /**
     * register items
     */
    static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(ModFoodItem.getFoodItems());
        TOOLSET_CHOCOLATE.registerItems(registry);
        TOOLSET_COTTON_CANDY.registerItems(registry);
    }

    /**
     * Register item models
     */
    static void registerModels() {
        ModFoodItem.initItemModels();
        TOOLSET_CHOCOLATE.registerItemModels();
        TOOLSET_COTTON_CANDY.registerItemModels();
    }
}

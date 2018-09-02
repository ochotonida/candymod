package com.ochotonida.candymod;

import com.ochotonida.candymod.item.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("unused")
public final class ModItems {

    public static final ModFoodItem BUTTER = new ModFoodItem("butter", 1, 1.0F, "foodButter");
    public static final ModFoodItem COTTON_CANDY = new ModFoodItem("cotton_candy", 4, 0.5F, "foodCottoncandy");
    public static final ModFoodItem WAFER_STICK = new ModFoodItem("wafer_stick", 5, 0.6F, "foodWaferstick", "stickWood");
    public static final ModFoodItem SUGAR_ROCK = new ModFoodItem("sugar_rock", 4, 0.2F, "foodRockcandy");

    public static final ItemCandyCane CANDY_CANE = new ItemCandyCane();
    public static final ItemChocolate BROWNIE = new ItemChocolate("brownie", 4, 0.5F, "foodBrownie");
    public static final ItemChocolate CHOCOLATE_BAR = new ItemChocolate("chocolate_bar", "foodChocolatebar", "foodChocolate");
    public static final ItemChocolate CHOCOLATE_EGG = new ItemChocolate("chocolate_egg", 7, 0.8F, "foodChocolateegg");
    public static final ItemGummy GUMMY = new ItemGummy("gummy", 5, 0.5F, "foodGummy");
    public static final ItemGummy GUMMY_WORM = new ItemGummy("gummy_worm", 5, 1F, "foodGummyworm");
    public static final ItemTeleporter TELEPORTER = new ItemTeleporter();

    /**
     * register items
     */
    static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(ModFoodItem.getFoodItems());
    }

    /**
     * Register item models
     */
    static void registerModels() {
        ModFoodItem.initItemModels();
    }
}

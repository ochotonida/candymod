package com.ochotonida.candymod;

import com.ochotonida.candymod.item.*;
import com.ochotonida.candymod.item.tools.ItemPickaxeEdible;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.registries.IForgeRegistry;

@SuppressWarnings("unused")
public final class ModItems {

    public static final Item.ToolMaterial CHOCOLATE_TOOL_MATERIAL = EnumHelper.addToolMaterial("chocolate", 1, 180, 5.0F, 1.0F, 25);

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
    public static final ItemGummy GUMMY = new ItemGummy("gummy", 5, 0.5F, "foodGummy");
    public static final ItemGummy GUMMY_WORM = new ItemGummy("gummy_worm", 5, 1F, "foodGummyworm");
    public static final ItemTeleporter TELEPORTER = new ItemTeleporter();

    // tool
    public static final ItemPickaxeEdible CHOCOLATE_PICKAXE_MILK = new ItemPickaxeEdible("chocolate_pickaxe_milk", CHOCOLATE_TOOL_MATERIAL, 16, 0.6F);
    public static final ItemPickaxeEdible CHOCOLATE_PICKAXE_DARK = new ItemPickaxeEdible("chocolate_pickaxe_dark", CHOCOLATE_TOOL_MATERIAL, 16, 0.6F);
    public static final ItemPickaxeEdible CHOCOLATE_PICKAXE_WHITE = new ItemPickaxeEdible("chocolate_pickaxe_white", CHOCOLATE_TOOL_MATERIAL, 16, 0.6F);

    /**
     * register items
     */
    static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(ModFoodItem.getFoodItems());
        registry.register(CHOCOLATE_PICKAXE_MILK);
        registry.register(CHOCOLATE_PICKAXE_DARK);
        registry.register(CHOCOLATE_PICKAXE_WHITE);
    }

    /**
     * Register item models
     */
    static void registerModels() {
        ModFoodItem.initItemModels();
        CHOCOLATE_PICKAXE_MILK.registerItemModel();
        CHOCOLATE_PICKAXE_DARK.registerItemModel();
        CHOCOLATE_PICKAXE_WHITE.registerItemModel();
    }
}

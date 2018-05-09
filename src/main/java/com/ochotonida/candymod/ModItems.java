package com.ochotonida.candymod;

import com.ochotonida.candymod.item.ItemCandyCane;
import com.ochotonida.candymod.item.ItemChocolate;
import com.ochotonida.candymod.item.ItemGummy;
import com.ochotonida.candymod.item.ModFood;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    // Food items
    public static final ModFood BUTTER = new ModFood("butter", "foodButter", 1, 1.0F);
    public static final ModFood COTTON_CANDY = new ModFood("cotton_candy", "foodCottonCandy", 4, 0.5F);
    public static final ModFood WAFER_STICK = new ModFood("wafer_stick", "foodWaferStick", 5, 0.6F);
    public static final ModFood SUGAR_ROCK = new ModFood("sugar_rock", "foodRockCandy", 4, 0.2F);

    public static final ItemCandyCane CANDY_CANE = new ItemCandyCane();
    public static final ItemChocolate BROWNIE = new ItemChocolate("brownie", "foodBrownie", 4, 0.5F);
    public static final ItemChocolate CHOCOLATE_BAR = new ItemChocolate("chocolate_bar", "foodChocolate");
    public static final ItemChocolate CHOCOLATE_EGG = new ItemChocolate("chocolate_egg", "foodChocolateEgg", 7, 0.8F);
    public static final ItemGummy GUMMY = new ItemGummy("gummy", "foodGummy", 5, 0.5F);
    public static final ItemGummy GUMMY_WORM = new ItemGummy("gummy_worm", "foodGummyWorm", 5, 1F);

    // register items
    static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                BROWNIE,
                BUTTER,
                CANDY_CANE,
                CHOCOLATE_BAR,
                CHOCOLATE_EGG,
                COTTON_CANDY,
                SUGAR_ROCK,
                WAFER_STICK,
                GUMMY,
                GUMMY_WORM
        );
    }

    // item models
    static void registerModels() {
        BROWNIE.registerItemModel();
        BUTTER.registerItemModel();
        CANDY_CANE.registerItemModel();
        CHOCOLATE_BAR.registerItemModel();
        CHOCOLATE_EGG.registerItemModel();
        COTTON_CANDY.registerItemModel();
        WAFER_STICK.registerItemModel();
        SUGAR_ROCK.registerItemModel();
        GUMMY.registerItemModel();
        GUMMY_WORM.registerItemModel();
    }
}

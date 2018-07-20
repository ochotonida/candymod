package com.ochotonida.candymod;

import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.item.ModFoodItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

    private static final ResourceLocation MODRECIPES_GROUP = new ResourceLocation("candymod");

    public static void init() {
        ModRecipes.initOreDict();
        GameRegistry.addSmelting(Items.SUGAR, new ItemStack(ModItems.COTTON_CANDY), 0.35F);

        ItemStack itemStack;
        for (EnumChocolate value : EnumChocolate.values()) {
            itemStack = new ItemStack(ModItems.CHOCOLATE_BAR, 1, value.getMetadata());
            GameRegistry.addSmelting(new ItemStack(ModItems.CHOCOLATE_EGG, 1, value.getMetadata()), itemStack, 0.4F);
            itemStack = new ItemStack(ModBlocks.CHOCOLATE_BLOCK_IB, 1, value.getMetadata());
            GameRegistry.addSmelting(new ItemStack(ModBlocks.CHOCOLATE_BRICK_IB, 1, value.getMetadata()), itemStack, 0.2F);
        }

        if (!Loader.isModLoaded("harvestcraft")) {
            GameRegistry.addShapelessRecipe(new ResourceLocation("butter"), MODRECIPES_GROUP, new ItemStack(ModItems.BUTTER, 1), Ingredient.fromItem(Items.MILK_BUCKET));
        } else {
            CandyMod.LOGGER.info("harvestcraft detected, changing recipe(s)");
        }
    }

    private static void initOreDict() {
        ModFoodItem.initOreDict();
        OreDictionary.registerOre("crystalSugar", ModItems.SUGAR_ROCK);
        OreDictionary.registerOre("blockCrystalSugar", ModBlocks.SUGAR_BLOCK);
        OreDictionary.registerOre("blockSugar", ModBlocks.SUGAR_SAND);
    }
}

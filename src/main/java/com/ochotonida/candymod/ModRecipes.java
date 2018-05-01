package com.ochotonida.candymod;

import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.item.ModFood;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

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
    }

    private static void initOreDict() {
        ModFood.initOreDict();
        OreDictionary.registerOre("crystalSugar", ModItems.SUGAR_ROCK);
        OreDictionary.registerOre("blockCrystalSugar", ModBlocks.SUGAR_BLOCK);
        OreDictionary.registerOre("blockSugar", ModBlocks.SUGAR_SAND);
    }
}

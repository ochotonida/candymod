package com.ochotonida.candymod.item.tools;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Collection of tools of the same material
 */
public abstract class ToolSet {
    
    private final ItemPickaxeEdible[] pickaxes;
    private final ItemAxeEdible[] axes;
    private final ItemShovelEdible[] shovels;
    private final ItemSwordEdible[] swords;
    
    public ToolSet(String name, Item.ToolMaterial toolMaterial, int variants, int healAmount, float saturationModifier) {
        pickaxes = new ItemPickaxeEdible[variants];
        axes = new ItemAxeEdible[variants];
        shovels = new ItemShovelEdible[variants];
        swords = new ItemSwordEdible[variants];
        for (int i = 0; i < variants; i++) {
            pickaxes[i] = new ItemPickaxeEdible(name + "_pickaxe_" + getVariantSuffix(i), name, toolMaterial, healAmount, saturationModifier);
            axes[i] = new ItemAxeEdible(name + "_axe_" + getVariantSuffix(i), name, toolMaterial, healAmount, saturationModifier);
            shovels[i] = new ItemShovelEdible(name + "_shovel_" + getVariantSuffix(i), name, toolMaterial, healAmount, saturationModifier);
            swords[i] = new ItemSwordEdible(name + "_sword_" + getVariantSuffix(i), name, toolMaterial, healAmount, saturationModifier);
        }
    }
    
    protected abstract String getVariantSuffix(int variant);
    
    public final ItemPickaxeEdible getPickaxe(int variant) {
        return pickaxes[variant];
    }
    
    public final ItemAxeEdible getAxe(int variant) {
        return axes[variant];
    }
    
    public final ItemShovelEdible getShovel(int variant){
        return shovels[variant];
    }
    
    public final ItemSwordEdible getSword(int variant) {
        return swords[variant];
    }
    
    public final void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(pickaxes);
        registry.registerAll(axes);
        registry.registerAll(shovels);
        registry.registerAll(swords);
    }
    
    public final void registerItemModels() {
        for (ItemPickaxeEdible item : pickaxes) {
            item.registerItemModel();
        }
        for (ItemAxeEdible item : axes) {
            item.registerItemModel();
        }
        for (ItemShovelEdible item : shovels) {
            item.registerItemModel();
        }
        for (ItemSwordEdible item : swords) {
            item.registerItemModel();
        }
    }
}

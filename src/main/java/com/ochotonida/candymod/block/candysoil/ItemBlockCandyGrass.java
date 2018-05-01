package com.ochotonida.candymod.block.candysoil;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockCandyGrass extends ItemBlock {

    public ItemBlockCandyGrass() {
        super(ModBlocks.CANDY_GRASS);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(ModBlocks.CANDY_GRASS.getRegistryName());
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        EnumChocolate type = EnumChocolate.byMetadata(stack.getMetadata());
        return this.getUnlocalizedName() + ":" + type.getName();
    }
}

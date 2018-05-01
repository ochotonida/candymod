package com.ochotonida.candymod.block.candycane;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockCandyCane extends ItemBlock {

    public ItemBlockCandyCane() {
        super(ModBlocks.CANDY_CANE);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(ModBlocks.CANDY_CANE.getRegistryName());
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        EnumCandyCane type = EnumCandyCane.byMetadata(stack.getMetadata());
        return this.getUnlocalizedName() + ":" + type.getName();
    }

}

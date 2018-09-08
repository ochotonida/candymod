package com.ochotonida.candymod.block.candycane;

import com.ochotonida.candymod.enums.EnumCandyCane;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockCandyCane extends ItemBlock {

    public ItemBlockCandyCane(BlockCandyCane block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(block.getRegistryName());
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

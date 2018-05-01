package com.ochotonida.candymod.block.chocolate;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockChocolateMushroom extends ItemBlock {

    public ItemBlockChocolateMushroom() {
        super(ModBlocks.CHOCOLATE_MUSHROOM);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(ModBlocks.CHOCOLATE_MUSHROOM.getRegistryName());
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

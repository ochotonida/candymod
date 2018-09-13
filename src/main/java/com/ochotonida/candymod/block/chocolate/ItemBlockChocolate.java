package com.ochotonida.candymod.block.chocolate;

import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockChocolate extends ItemBlock {

    public ItemBlockChocolate(BlockChocolate block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setRegistryName(block.getName());
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

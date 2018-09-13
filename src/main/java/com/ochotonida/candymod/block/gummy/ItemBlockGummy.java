package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockGummy extends ItemBlock {

    public ItemBlockGummy(BlockGummyBase block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setRegistryName(block.getName());
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        EnumGummy type = EnumGummy.byMetadata(stack.getMetadata());
        return this.getUnlocalizedName() + ":" + type.getName();
    }
}

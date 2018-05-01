package com.ochotonida.candymod.block.candysoil;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemBlockCandySoil extends ItemBlock {

    public ItemBlockCandySoil() {
        super(ModBlocks.CANDY_SOIL);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(ModBlocks.CANDY_SOIL.getRegistryName());
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

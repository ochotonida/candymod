package com.ochotonida.candymod.block.ore;

import com.ochotonida.candymod.ModBlocks;
import net.minecraft.item.ItemBlock;

public class ItemBlockCookieOre extends ItemBlock {

    public ItemBlockCookieOre() {
        super(ModBlocks.COOKIE_ORE);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        //noinspection ConstantConditions
        this.setRegistryName(ModBlocks.COOKIE_ORE.getRegistryName());
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }
}

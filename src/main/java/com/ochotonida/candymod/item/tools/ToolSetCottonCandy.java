package com.ochotonida.candymod.item.tools;

import com.ochotonida.candymod.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ToolSetCottonCandy extends ToolSet {

    public static final Item.ToolMaterial COTTON_CANDY_TOOL_MATERIAL = EnumHelper.addToolMaterial("cotton_candy", 1, 5, 15.0F, 5.0F, 65);

    static {
        assert COTTON_CANDY_TOOL_MATERIAL != null;
        COTTON_CANDY_TOOL_MATERIAL.setRepairItem(new ItemStack(ModItems.COTTON_CANDY));
    }

    public ToolSetCottonCandy() {
        super("cotton_candy", COTTON_CANDY_TOOL_MATERIAL, 1, 6, 0.6F);
    }

    @Override
    protected String getVariantSuffix(int variant) {
        return null;
    }
}

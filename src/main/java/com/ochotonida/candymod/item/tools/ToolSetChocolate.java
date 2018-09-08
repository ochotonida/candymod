package com.ochotonida.candymod.item.tools;

import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ToolSetChocolate extends ToolSet {

    public static final Item.ToolMaterial CHOCOLATE_TOOL_MATERIAL = EnumHelper.addToolMaterial("chocolate", 2, 750, 7.0F, 2.5F, 25);

    static {
        assert CHOCOLATE_TOOL_MATERIAL != null;
        CHOCOLATE_TOOL_MATERIAL.setRepairItem(new ItemStack(ModItems.CHOCOLATE_BAR));
    }

    public ToolSetChocolate() {
        super("chocolate", CHOCOLATE_TOOL_MATERIAL, EnumChocolate.values().length, 20, 0.6F);
    }

    @Override
    protected String getVariantSuffix(int variant) {
        return EnumChocolate.byMetadata(variant).getName();
    }
}

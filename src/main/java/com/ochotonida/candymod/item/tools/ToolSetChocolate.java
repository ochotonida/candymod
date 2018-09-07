package com.ochotonida.candymod.item.tools;

import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ToolSetChocolate extends ToolSet {

    public static final Item.ToolMaterial CHOCOLATE_TOOL_MATERIAL = EnumHelper.addToolMaterial("chocolate", 2, 180, 5.0F, 2.0F, 25);

    public ToolSetChocolate() {
        super("chocolate", CHOCOLATE_TOOL_MATERIAL, EnumChocolate.values().length, 16, 0.6F);
    }

    @Override
    protected String getVariantSuffix(int variant) {
        return EnumChocolate.byMetadata(variant).getName();
    }
}

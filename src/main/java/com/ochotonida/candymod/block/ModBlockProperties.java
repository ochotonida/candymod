package com.ochotonida.candymod.block;

import com.ochotonida.candymod.enums.EnumAxis;
import com.ochotonida.candymod.enums.EnumCandyCane;
import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;

public class ModBlockProperties {

    public static final PropertyEnum<EnumChocolate> CHOCOLATE_TYPE = PropertyEnum.create("type", EnumChocolate.class);
    public static final PropertyEnum<EnumGummy> GUMMY_TYPE = PropertyEnum.create("type", EnumGummy.class);
    public static final PropertyEnum<EnumCandyCane> CANDY_CANE_TYPE = PropertyEnum.create("type", EnumCandyCane.class);
    public static final PropertyEnum<EnumAxis> AXIS = PropertyEnum.create("axis", EnumAxis.class);

    public static final PropertyBool IS_SUGAR_VARIANT = PropertyBool.create("sugar_variant");
}

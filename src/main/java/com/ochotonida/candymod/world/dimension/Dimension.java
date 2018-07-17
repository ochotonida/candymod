package com.ochotonida.candymod.world.dimension;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModConfig;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public final class Dimension {

    public static DimensionType dimensionType;

    public static void init() {
        dimensionType = DimensionType.register(CandyMod.MODID, "_dimension", ModConfig.dimensionId, DimensionWorldProvider.class, false);
        DimensionManager.registerDimension(ModConfig.dimensionId, dimensionType);
    }
}

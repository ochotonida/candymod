package com.ochotonida.candymod.block.fluid;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

    public static final Fluid LIQUID_CHOCOLATE;
    public static final Fluid LIQUID_CANDY;

    static {
        LIQUID_CHOCOLATE = new Fluid("liquid_chocolate", new ResourceLocation(CandyMod.MODID, "blocks/liquid_chocolate_still"), new ResourceLocation(CandyMod.MODID, "blocks/liquid_chocolate_flow"));
        LIQUID_CHOCOLATE.setDensity(1030).setTemperature(315);
        LIQUID_CANDY = new Fluid("liquid_candy", new ResourceLocation(CandyMod.MODID, "blocks/liquid_candy_still"), new ResourceLocation(CandyMod.MODID, "blocks/liquid_candy_flow"));
        LIQUID_CANDY.setDensity(2000).setTemperature(1000).setViscosity(3000).setLuminosity(10);
    }

    public static void init() {
        FluidRegistry.addBucketForFluid(LIQUID_CHOCOLATE);
        FluidRegistry.addBucketForFluid(LIQUID_CANDY);
    }
}

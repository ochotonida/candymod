package com.ochotonida.candymod.world.dimension;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;

public class DimensionWorldProvider extends WorldProvider {

    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return Dimension.dimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "CandyWorldDimension";
    }

    @Nonnull
    @Override
    public IChunkGenerator createChunkGenerator() {
        return new DimensionChunkGenerator(world);
    }
}

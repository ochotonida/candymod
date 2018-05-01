package com.ochotonida.candymod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ModBiome extends Biome {

    public WorldGenerator worldGenBiomeFoundation = null;

    public ModBiome(BiomeProperties properties) {
        super(properties);
        initSpawnList();
    }

    public abstract void initSpawnList();

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new DecoratorBase();
    }

    @Nullable
    public WorldGenerator getFoundationWorldGen() {
        return worldGenBiomeFoundation;
    }
}
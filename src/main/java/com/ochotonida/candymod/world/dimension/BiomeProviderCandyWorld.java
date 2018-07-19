package com.ochotonida.candymod.world.dimension;

import com.ochotonida.candymod.ModBiomes;
import com.ochotonida.candymod.world.dimension.layer.GenLayerCandyWorldBiome;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static net.minecraft.world.gen.layer.GenLayer.getModdedBiomeSize;

public class BiomeProviderCandyWorld extends BiomeProvider {

    public BiomeProviderCandyWorld(WorldInfo worldInfo) {
        super(worldInfo);
        getBiomesToSpawnIn().clear();
        getBiomesToSpawnIn().add(ModBiomes.BIOME_CHOCOLATE);
        getBiomesToSpawnIn().add(ModBiomes.BIOME_COTTON_CANDY);
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        original = initializeGenLayers(seed, worldType);
        return super.getModdedBiomeGenerators(worldType, seed, original);
    }

    private GenLayer[] initializeGenLayers(long seed, WorldType worldType) {
        int biomeSize = getModdedBiomeSize(worldType, (byte) (worldType == WorldType.LARGE_BIOMES ? 7 : 5));

        GenLayer genLayer = new GenLayerIsland(1L);
        genLayer = new GenLayerFuzzyZoom(2000L, genLayer);

        genLayer = new GenLayerCandyWorldBiome(100L, genLayer);
        genLayer = GenLayerZoom.magnify(2000L, genLayer, 1);

        genLayer = GenLayerZoom.magnify(2100L, genLayer, biomeSize);

        GenLayer indexLayer = new GenLayerVoronoiZoom(10L, genLayer);
        indexLayer.initWorldGenSeed(seed);
        genLayer.initWorldGenSeed(seed);

        return new GenLayer[]{genLayer, indexLayer, genLayer};
    }
}

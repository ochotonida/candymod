package com.ochotonida.candymod.world.dimension.layer;

import com.ochotonida.candymod.ModBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import javax.annotation.Nonnull;

public class GenLayerCandyWorldBiome extends GenLayer {

    private final int totalWeight;

    public GenLayerCandyWorldBiome(long seed, GenLayer parent) {
        super(seed);
        int totalWeight = 0;
        for (ModBiomes.ModBiomeEntry entry : ModBiomes.biomeEntryList) {
            totalWeight += entry.getDimensionWeight();
        }
        this.totalWeight = totalWeight;
        this.parent = parent;
    }

    @Nonnull
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] ints = IntCache.getIntCache(areaWidth * areaHeight);

        for (int zz = 0; zz < areaHeight; ++zz) {
            for (int xx = 0; xx < areaWidth; ++xx) {
                this.initChunkSeed(xx + areaX, zz + areaY);

                int rand = this.nextInt(totalWeight);
                Biome biome = ModBiomes.biomeEntryList.get(0).getBiome();
                for (ModBiomes.ModBiomeEntry entry : ModBiomes.biomeEntryList) {
                    if (rand < entry.getDimensionWeight()) {
                        biome = entry.getBiome();
                        break;
                    }
                    rand -= entry.getDimensionWeight();
                }
                ints[xx + zz * areaWidth] = Biome.getIdForBiome(biome);
            }
        }
        return ints;
    }
}

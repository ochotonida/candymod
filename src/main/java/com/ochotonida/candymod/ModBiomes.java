package com.ochotonida.candymod;

import com.ochotonida.candymod.world.biome.BiomeChocolate;
import com.ochotonida.candymod.world.biome.BiomeCottonCandy;
import com.ochotonida.candymod.world.biome.BiomeGummy;
import com.ochotonida.candymod.world.biome.ModBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ModBiomes {

    public static final List<ModBiomeEntry> biomeEntryList = new ArrayList<>();

    public static final BiomeCottonCandy BIOME_COTTON_CANDY = new BiomeCottonCandy();
    public static final BiomeChocolate BIOME_CHOCOLATE = new BiomeChocolate();
    public static final BiomeGummy BIOME_GUMMY = new BiomeGummy();

    static void registerBiomes(RegistryEvent.Register<Biome> event) {
        registerBiome(event, BIOME_COTTON_CANDY, BiomeType.COOL, ModConfig.weightCottonCanyPlains, DRY, SPARSE, RARE);
        registerBiome(event, BIOME_CHOCOLATE, BiomeType.WARM, ModConfig.weightChocolateForest, FOREST, DENSE, HILLS, RARE);
        registerBiome(event, BIOME_GUMMY, BiomeType.WARM, ModConfig.weightGummySwamp, SWAMP, WET, RARE);
    }

    private static void registerBiome(RegistryEvent.Register<Biome> event, ModBiome biome, BiomeType type, int weight, BiomeDictionary.Type... biomeDictTypes) {
        event.getRegistry().register(biome);

        // add all biomeDictTypes for said biome
        for (BiomeDictionary.Type biomeDictType : biomeDictTypes) {
            BiomeDictionary.addTypes(biome, biomeDictType);
        }

        // add biome to the biomeEntry list if weight is not 0
        biomeEntryList.add(new ModBiomeEntry(biome, type, weight));
    }

    public static class ModBiomeEntry {

        private final int weight;
        private final Biome biome;
        private final BiomeType type;
        private final BiomeManager.BiomeEntry entry;

        private ModBiomeEntry(ModBiome biome, BiomeType type, int weight) {
            this.type = type;
            this.biome = biome;
            this.weight = weight;
            this.entry = new BiomeManager.BiomeEntry(biome, weight);
        }

        public ModBiome getBiome() {
            return (ModBiome) this.biome;
        }

        public BiomeManager.BiomeEntry getEntry() {
            return this.entry;
        }

        public BiomeType getType() {
            return this.type;
        }

        public int getWeight() {
            return this.weight;
        }

        public int getDimensionWeight() {
            return this.weight;  //todo
        }
    }
}

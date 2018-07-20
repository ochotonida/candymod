package com.ochotonida.candymod.world.biome;


import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.entity.EntityCandySheep;
import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.world.worldgen.WorldGenBiomeFoundation;
import com.ochotonida.candymod.world.worldgen.WorldGenCottonCandyGrass;
import com.ochotonida.candymod.world.worldgen.WorldGenCottonCandyTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class BiomeCottonCandy extends ModBiome {

    public BiomeCottonCandy() {
        super(new BiomeProperties("CottonCandyPlains").setBaseHeight(0.125F).setHeightVariation(0.2F).setTemperature(0.8F).setRainfall(0.3F).setWaterColor(0xff0099));
        this.setRegistryName("biome_cotton_candy");
        this.decorator.grassPerChunk = 20;
        this.decorator.treesPerChunk = 1;
        this.decorator.generateFalls = false;
        this.topBlock = ModBlocks.CANDY_GRASS.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.MILK);
        this.fillerBlock = ModBlocks.CANDY_SOIL.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.MILK);
        this.worldGenBiomeFoundation = new WorldGenBiomeFoundation(this, 3, 8, 8, ModBlocks.SUGAR_BLOCK.getDefaultState());
    }

    @Override
    public void initSpawnList() {
        this.spawnableCreatureList.clear();
        if (ModConfig.weightCottonCandySheep > 0) {
            int multiplier = ModConfig.preventModdedMobSpawn ? 1000 : 1;
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCandySheep.class, ModConfig.weightCottonCandySheep * multiplier, 3, 6));
        }
    }

    @Override
    public int getSkyColorByTemp(float temp) {
        return 0xffaaee;
    }

    @Nonnull
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenCottonCandyGrass();
    }

    @Nonnull
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return new WorldGenCottonCandyTree(false);
    }
}

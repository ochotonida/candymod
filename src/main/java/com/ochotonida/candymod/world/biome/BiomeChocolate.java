package com.ochotonida.candymod.world.biome;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.entity.EntityEasterChicken;
import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.world.worldgen.WorldGenBiomeSpikes;
import com.ochotonida.candymod.world.worldgen.WorldGenChocolateForestGrass;
import com.ochotonida.candymod.world.worldgen.WorldGenChocolateForestTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BiomeChocolate extends ModBiome {

    public BiomeChocolate() {
        super(new BiomeProperties("ChocolateForest").setBaseHeight(-0.12F).setHeightVariation(0.05F).setTemperature(0.8F).setRainfall(0.3F));
        this.setRegistryName("biome_chocolate_forest");
        this.decorator.grassPerChunk = 20;
        this.decorator.treesPerChunk = 5;
        this.decorator.generateFalls = false;
        ((DecoratorBase) this.decorator).generateCaveChocolate = true;
        this.topBlock = ModBlocks.CANDY_GRASS.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.WHITE);
        this.fillerBlock = ModBlocks.CANDY_SOIL.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.WHITE);
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeChocolate.Decorator();
    }

    @Override
    public void initSpawnList() {
        this.spawnableCreatureList.clear();
        if (ModConfig.weightEasterChicken > 0) {
            int multiplier = ModConfig.preventModdedMobSpawn ? 1000 : 1;
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityEasterChicken.class, ModConfig.weightEasterChicken * multiplier, 3, 7));
        }
    }

    @Override
    public int getSkyColorByTemp(float temp) {
        return 0xffddaa;
    }

    @Nonnull
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new WorldGenChocolateForestGrass();
    }

    @Nonnull
    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return new WorldGenChocolateForestTree(false);
    }

    private class Decorator extends DecoratorBase {

        @Override
        protected void initOverworldWorldGens() {
            super.initOverworldWorldGens();
            spikesGen = new WorldGenBiomeSpikes(BiomeChocolate.this, 3, 24, 16,
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK),
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.WHITE),
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.DARK));
        }

        @Override
        protected void initDimensionWorldGens() {
            super.initDimensionWorldGens();
            spikesGen = new WorldGenBiomeSpikes(BiomeChocolate.this, 3, 24, 16,
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK),
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.WHITE),
                    ModBlocks.CHOCOLATE_BLOCK.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.DARK));
        }
    }
}

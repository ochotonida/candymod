package com.ochotonida.candymod.world.biome;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.world.worldgen.WorldGenCaveChocolate;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@SuppressWarnings("CanBeFinal")
public class DecoratorBase extends BiomeDecorator {

    public WorldGenerator soilMilkGen;
    public WorldGenerator soilWhiteGen;
    public WorldGenerator soilDarkGen;
    public WorldGenerator sugarBlockGen;
    public WorldGenerator cookieGen;
    public WorldGenerator sugarSandGen;
    public WorldGenerator caveChocolateGen = new WorldGenCaveChocolate();
    @Nullable
    public WorldGenerator foundationGen;

    public int caveChocolateAmount = 5;

    public boolean generateCaveChocolate;

    @Override
    @ParametersAreNonnullByDefault
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
        if (this.decorating) {
            throw new RuntimeException("Already decorating");
        } else {
            this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
            this.chunkPos = pos;

            if (biome instanceof ModBiome) {
                this.foundationGen = ((ModBiome) biome).getFoundationWorldGen();
            }

            this.soilMilkGen = new WorldGenMinable(ModBlocks.CANDY_SOIL.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.MILK), 25);
            this.soilWhiteGen = new WorldGenMinable(ModBlocks.CANDY_SOIL.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.WHITE), 25);
            this.soilDarkGen = new WorldGenMinable(ModBlocks.CANDY_SOIL.getDefaultState().withProperty(ModBlockProperties.CHOCOLATE_TYPE, EnumChocolate.DARK), 25);
            this.sugarBlockGen = new WorldGenMinable(ModBlocks.SUGAR_BLOCK.getDefaultState(), 20);
            this.cookieGen = new WorldGenMinable(ModBlocks.COOKIE_ORE.getDefaultState(), 3);
            this.sugarSandGen = new WorldGenMinable(ModBlocks.SUGAR_SAND.getDefaultState(), 20);

            this.coalGen = new WorldGenMinable(Blocks.COAL_ORE.getDefaultState(), this.chunkProviderSettings.coalSize);
            this.ironGen = new WorldGenMinable(Blocks.IRON_ORE.getDefaultState(), this.chunkProviderSettings.ironSize);
            this.goldGen = new WorldGenMinable(Blocks.GOLD_ORE.getDefaultState(), this.chunkProviderSettings.goldSize);
            this.redstoneGen = new WorldGenMinable(Blocks.REDSTONE_ORE.getDefaultState(), this.chunkProviderSettings.redstoneSize);
            this.diamondGen = new WorldGenMinable(Blocks.DIAMOND_ORE.getDefaultState(), this.chunkProviderSettings.diamondSize);
            this.lapisGen = new WorldGenMinable(Blocks.LAPIS_ORE.getDefaultState(), this.chunkProviderSettings.lapisSize);
            this.genDecorations(biome, worldIn, random);
            this.decorating = false;
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void genDecorations(Biome biome, World worldIn, Random rand) {

        if (foundationGen != null && TerrainGen.decorate(worldIn, rand, chunkPos, DecorateBiomeEvent.Decorate.EventType.CUSTOM)) {
            foundationGen.generate(worldIn, rand, chunkPos);
        }

        super.genDecorations(biome, worldIn, rand);

        if (TerrainGen.decorate(worldIn, rand, chunkPos, DecorateBiomeEvent.Decorate.EventType.CUSTOM)) {
            genBiomeDecorations(worldIn, rand);

            if (generateCaveChocolate) {
                for (int i = 0; i < this.caveChocolateAmount; i++) {
                    int j = rand.nextInt(16) + 8;
                    int k = rand.nextInt(16) + 8;
                    caveChocolateGen.generate(worldIn, rand, chunkPos.add(j, 0, k));
                }
            }
        }
    }

    protected void genBiomeDecorations(World worldIn, Random rand) {
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void generateOres(World worldIn, Random rand) {
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldIn, rand, chunkPos));

        if (TerrainGen.generateOre(worldIn, rand, soilMilkGen, chunkPos, EventType.CUSTOM))
            this.genStandardOre1(worldIn, rand, 1, soilMilkGen, 20, 40);
        if (TerrainGen.generateOre(worldIn, rand, soilWhiteGen, chunkPos, EventType.CUSTOM))
            this.genStandardOre1(worldIn, rand, 1, soilWhiteGen, 40, 64);
        if (TerrainGen.generateOre(worldIn, rand, soilDarkGen, chunkPos, EventType.CUSTOM))
            this.genStandardOre1(worldIn, rand, 1, soilDarkGen, 0, 25);
        if (TerrainGen.generateOre(worldIn, rand, sugarBlockGen, chunkPos, EventType.CUSTOM))
            this.genStandardOre1(worldIn, rand, 2, sugarBlockGen, 0, 30);
        if (TerrainGen.generateOre(worldIn, rand, cookieGen, chunkPos, EventType.CUSTOM)) {
            this.genStandardOre1(worldIn, rand, 200, cookieGen, 31, 32);
            this.genStandardOre2(worldIn, rand, 25, cookieGen, 32, 32);
        }
        if (TerrainGen.generateOre(worldIn, rand, sugarSandGen, chunkPos, EventType.CUSTOM))
            this.genStandardOre1(worldIn, rand, 5, sugarSandGen, 0, 256);

        if (TerrainGen.generateOre(worldIn, rand, coalGen, chunkPos, EventType.COAL))
            this.genStandardOre1(worldIn, rand, this.chunkProviderSettings.coalCount, this.coalGen, this.chunkProviderSettings.coalMinHeight, this.chunkProviderSettings.coalMaxHeight);
        if (TerrainGen.generateOre(worldIn, rand, ironGen, chunkPos, EventType.IRON))
            this.genStandardOre1(worldIn, rand, this.chunkProviderSettings.ironCount, this.ironGen, this.chunkProviderSettings.ironMinHeight, this.chunkProviderSettings.ironMaxHeight);
        if (TerrainGen.generateOre(worldIn, rand, goldGen, chunkPos, EventType.GOLD))
            this.genStandardOre1(worldIn, rand, this.chunkProviderSettings.goldCount, this.goldGen, this.chunkProviderSettings.goldMinHeight, this.chunkProviderSettings.goldMaxHeight);
        if (TerrainGen.generateOre(worldIn, rand, redstoneGen, chunkPos, EventType.REDSTONE))
            this.genStandardOre1(worldIn, rand, this.chunkProviderSettings.redstoneCount, this.redstoneGen, this.chunkProviderSettings.redstoneMinHeight, this.chunkProviderSettings.redstoneMaxHeight);
        if (TerrainGen.generateOre(worldIn, rand, diamondGen, chunkPos, EventType.DIAMOND))
            this.genStandardOre1(worldIn, rand, this.chunkProviderSettings.diamondCount, this.diamondGen, this.chunkProviderSettings.diamondMinHeight, this.chunkProviderSettings.diamondMaxHeight);
        if (TerrainGen.generateOre(worldIn, rand, lapisGen, chunkPos, EventType.LAPIS))
            this.genStandardOre2(worldIn, rand, this.chunkProviderSettings.lapisCount, this.lapisGen, this.chunkProviderSettings.lapisCenterHeight, this.chunkProviderSettings.lapisSpread);
        net.minecraftforge.common.MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldIn, rand, chunkPos));
    }
}

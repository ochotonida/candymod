package com.ochotonida.candymod.world.dimension;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;

public class DimensionChunkGenerator implements IChunkGenerator {

    private final World worldObj;
    private Random random;
    private Biome[] biomesForGeneration;

    @SuppressWarnings("UnusedAssignment")
    private MapGenBase caveGenerator = new MapGenCaves();
    private DimensionTerrainGenerator terraingen = new DimensionTerrainGenerator();

    public DimensionChunkGenerator(World worldObj) {
        this.worldObj = worldObj;
        long seed = worldObj.getSeed();
        this.random = new Random((seed + 516) * 314);
        terraingen.setup(worldObj, random);
        caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
    }

    @Nonnull
    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        // Setup biomes for terraingen
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        terraingen.setBiomesForGeneration(biomesForGeneration);
        terraingen.generate(x, z, chunkprimer);

        // Setup biomes again for actual biome decoration
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        // This will replace stone with the biome specific stones
        terraingen.replaceBiomeBlocks(x, z, chunkprimer, this, biomesForGeneration);

        // Generate caves
        this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

        byte[] biomeArray = chunk.getBiomeArray();
        for (int i = 0; i < biomeArray.length; ++i) {
            biomeArray[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome biome = this.worldObj.getBiome(blockpos.add(16, 0, 16));

        // Add biome decorations (like flowers, grass, trees, ...)
        biome.decorate(this.worldObj, this.random, blockpos);

        // Make sure animals appropriate to the biome spawn here when the chunk is generated
        WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome, i + 8, j + 8, 16, 16, this.random);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }
}

package com.ochotonida.candymod.world.biome;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.entity.EntityGummyBear;
import com.ochotonida.candymod.entity.EntityGummyMouse;
import com.ochotonida.candymod.enums.EnumGummy;
import com.ochotonida.candymod.world.worldgen.WorldGenGummyWorm;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BiomeGummy extends ModBiome {

    public BiomeGummy() {
        super(new BiomeProperties("GummySwamp").setBaseHeight(0.125F).setHeightVariation(0.01F).setRainfall(0.8F).setTemperature(0.9F));
        this.setRegistryName("biome_gummy_swamp");
        this.decorator.grassPerChunk = -999;
        this.decorator.treesPerChunk = -999;
        this.decorator.generateFalls = false;
        this.topBlock = ModBlocks.GUMMY_BLOCK.getDefaultState();
        this.fillerBlock = ModBlocks.HARDENED_GUMMY_BLOCK.getDefaultState();
    }

    @Override
    public void initSpawnList() {
        this.spawnableCreatureList.clear();
        if (ModConfig.weightGummyMouse > 0) {
            int multiplier = ModConfig.preventModdedMobSpawn ? 1000 : 1;
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGummyMouse.class, ModConfig.weightGummyMouse * multiplier, 4, 10));
            this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityGummyBear.class, ModConfig.weightGummyBear * multiplier, 2, 5));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        this.generateCustomTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }

    @Override
    public int getSkyColorByTemp(float t) {
        return 0xa3fbff;
    }

    @Nonnull
    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeGummy.Decorator();
    }

    @ParametersAreNonnullByDefault
    public void generateCustomTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = worldIn.getSeaLevel();
        int j = -1;
        int fillerToPlace = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        final EnumGummy gummy_color = EnumGummy.getGummyForGeneration(noiseVal);
        IBlockState topState = this.topBlock.withProperty(ModBlockProperties.GUMMY_TYPE, gummy_color);
        IBlockState fillerState = this.fillerBlock.withProperty(ModBlockProperties.GUMMY_TYPE, gummy_color);
        int chunkZ = x & 15;
        int chunkX = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int currentY = 255; currentY >= 0; --currentY) {
            if (currentY <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(chunkX, currentY, chunkZ, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(chunkX, currentY, chunkZ);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == Blocks.STONE || iblockstate2.getBlock() == ModBlocks.SUGAR_BLOCK) {
                    if (j == -1) {
                        if (currentY >= seaLevel - 4 && currentY <= seaLevel + 1) {
                            topState = this.topBlock.withProperty(ModBlockProperties.GUMMY_TYPE, gummy_color);
                            fillerState = this.fillerBlock.withProperty(ModBlockProperties.GUMMY_TYPE, gummy_color);
                        }

                        if (currentY < seaLevel && (topState == null || topState.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, currentY, z)) < 0.15F) {
                                topState = ICE;
                            } else {
                                topState = WATER;
                            }
                        }

                        j = fillerToPlace + rand.nextInt(8) + 4;

                        if (currentY >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(chunkX, currentY, chunkZ, topState);
                        } else if (currentY < seaLevel - 7 - fillerToPlace) {
                            chunkPrimerIn.setBlockState(chunkX, currentY, chunkZ, GRAVEL);
                        } else {
                            chunkPrimerIn.setBlockState(chunkX, currentY, chunkZ, fillerState);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(chunkX, currentY, chunkZ, fillerState);
                    }
                }
            }
        }
    }

    private class Decorator extends DecoratorBase {

        public WorldGenerator gummyWormgen = new WorldGenGummyWorm();

        @Override
        protected void genBiomeDecorations(World worldIn, Random rand) {
            for (int i = 0; i < 2; i++) {
                int x = rand.nextInt(16) + 8;
                int z = rand.nextInt(16) + 8;
                gummyWormgen.generate(worldIn, rand, chunkPos.add(x, 0, z));
            }
        }
    }
}

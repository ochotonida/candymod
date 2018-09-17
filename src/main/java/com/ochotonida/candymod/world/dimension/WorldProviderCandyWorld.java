package com.ochotonida.candymod.world.dimension;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class WorldProviderCandyWorld extends WorldProvider {

    @Override
    protected void init() {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderCandyWorld(world.getWorldInfo());
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Nonnull
    @Override
    public DimensionType getDimensionType() {
        return Dimension.dimensionType;
    }

    @Override
    public String getSaveFolder() {
        return "DIM_CANDY_WORLD";
    }

    @Nonnull
    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorCandyWorld(world);
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public WorldSleepResult canSleepAt(net.minecraft.entity.player.EntityPlayer player, BlockPos pos) {
        return WorldSleepResult.ALLOW;
    }
}

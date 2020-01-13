package com.ochotonida.candymod.world.dimension;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.ochotonida.candymod.ModConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderCandyWorld extends WorldProvider {
	
	private long worldTime = 0;

    @Override
    protected void init() {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderCandyWorld(world.getWorldInfo());
        NBTTagCompound nbttagcompound = this.world.getWorldInfo().getDimensionData(ModConfig.dimensionId);
        this.worldTime = this.world instanceof WorldServer ? nbttagcompound.getLong("CandyTime") : 0;
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
    
    @Override
	public long getWorldTime(){
        return this.worldTime;
    }
    
    @Override
	public void setWorldTime(long time){
        this.worldTime = time;
    }
    
    @Override
	public void onWorldSave(){
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setLong("CandyTime", this.worldTime);
        this.world.getWorldInfo().setDimensionData(ModConfig.dimensionId, nbttagcompound);
    }
}

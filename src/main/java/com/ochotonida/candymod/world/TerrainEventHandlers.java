package com.ochotonida.candymod.world;

import com.ochotonida.candymod.world.biome.ModBiome;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class TerrainEventHandlers {

    @SubscribeEvent
    public void onCreateDecorate(DecorateBiomeEvent.Decorate event) {
        World world = event.getWorld();

        if (!world.isRemote) {
            if (world.getBiome(event.getPos()) instanceof ModBiome) {

                switch (event.getType()) {
                    case LAKE_LAVA:
                    case LAKE_WATER:
                        event.setResult(Event.Result.DENY);
                    default:
                        break;
                }
            }
        }
    }

    @SubscribeEvent
    public void onCreatePopulate(PopulateChunkEvent.Populate event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            BlockPos blockpos = new BlockPos(event.getChunkX() * 16, 0, event.getChunkZ() * 16);

            if (world.getBiome(blockpos) instanceof ModBiome
                    || world.getBiome(blockpos.north(15)) instanceof ModBiome
                    || world.getBiome(blockpos.east(15)) instanceof ModBiome
                    || world.getBiome(blockpos.north(15).east(15)) instanceof ModBiome) {

                switch (event.getType()) {
                    case LAVA:
                        int i2 = event.getRand().nextInt(16) + 8;
                        int l2 = event.getRand().nextInt(event.getRand().nextInt(248) + 8);
                        int k3 = event.getRand().nextInt(16) + 8;

                        if (l2 < event.getWorld().getSeaLevel() - 8) {
                            (new WorldGenLakes(Blocks.LAVA)).generate(event.getWorld(), event.getRand(), blockpos.add(i2, l2, k3));
                        }
                        event.setResult(Event.Result.DENY);
                        break;
                    case LAKE:
                        event.setResult(Event.Result.DENY);
                        break;
                }
            }
        }
    }
}

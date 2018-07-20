package com.ochotonida.candymod.world;

import com.ochotonida.candymod.world.biome.ModBiome;
import com.ochotonida.candymod.world.dimension.WorldProviderCandyWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
public class TerrainEventHandlers {

    @SubscribeEvent
    public void onCreatePopulate(PopulateChunkEvent.Populate event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            BlockPos blockpos = new BlockPos(event.getChunkX() * 16, 0, event.getChunkZ() * 16);

            if (world.getBiome(blockpos) instanceof ModBiome) {

                switch (event.getType()) {
                    case LAVA:
                        event.setResult(Event.Result.DENY);
                        break;
                    case LAKE:
                        event.setResult(Event.Result.DENY);
                        break;
                }
            }
        }
    }

    @SubscribeEvent
    public void onDecorateBiome(DecorateBiomeEvent.Decorate event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            if (world.provider instanceof WorldProviderCandyWorld) {
                switch (event.getType()) {
                    case SHROOM:
                        event.setResult(Event.Result.DENY);
                        break;
                }
            }
        }
    }
}

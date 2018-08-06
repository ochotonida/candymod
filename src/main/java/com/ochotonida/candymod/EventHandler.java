package com.ochotonida.candymod;

import com.ochotonida.candymod.world.biome.ModBiome;
import com.ochotonida.candymod.world.dimension.WorldProviderCandyWorld;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = CandyMod.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onCheckSpawn(LivingSpawnEvent.CheckSpawn event) {
        // prevent water mobs such as squid from spawning in chocolate
        BlockPos pos = new BlockPos(event.getX(), event.getY(), event.getZ());
        if (event.getEntity() instanceof EntityWaterMob && event.getWorld().getBlockState(pos).getBlock() == ModBlocks.LIQUID_CHOCOLATE_BLOCK) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onCreatePopulate(PopulateChunkEvent.Populate event) {
        World world = event.getWorld();
        if (!world.isRemote) {
            BlockPos blockpos = new BlockPos(event.getChunkX() * 16, 0, event.getChunkZ() * 16);

            if (world.getBiome(blockpos) instanceof ModBiome) {
                switch (event.getType()) {
                    case LAVA:
                        event.setResult(Event.Result.DENY);
                        if (net.minecraftforge.event.terraingen.TerrainGen.populate(event.getGen(), event.getWorld(), event.getRand(), event.getChunkX(), event.getChunkZ(), event.isHasVillageGenerated(), net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.CUSTOM)) {
                            int x = event.getRand().nextInt(16) + 8;
                            int y = event.getRand().nextInt(event.getRand().nextInt(248) + 8);
                            int z = event.getRand().nextInt(16) + 8;

                            if (y < event.getWorld().getSeaLevel()) {
                                (new WorldGenLakes(ModBlocks.LIQUID_CHOCOLATE_BLOCK)).generate(event.getWorld(), event.getRand(), blockpos.add(x, y, z));
                            }
                        }
                        break;
                    case LAKE:
                        event.setResult(Event.Result.DENY);
                        if (net.minecraftforge.event.terraingen.TerrainGen.populate(event.getGen(), event.getWorld(), event.getRand(), event.getChunkX(), event.getChunkZ(), event.isHasVillageGenerated(), net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.CUSTOM)) {
                            int x = event.getRand().nextInt(16) + 8;
                            int y = event.getRand().nextInt(256);
                            int z = event.getRand().nextInt(16) + 8;
                            (new WorldGenLakes(ModBlocks.LIQUID_CHOCOLATE_BLOCK)).generate(event.getWorld(), event.getRand(), blockpos.add(x, y, z));
                        }
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
                        // prevent mushrooms from spawning in custom dimension
                        event.setResult(Event.Result.DENY);
                        break;
                }
            }
        }
    }
}

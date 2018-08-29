package com.ochotonida.candymod;

import com.ochotonida.candymod.command.TeleportCommand;
import com.ochotonida.candymod.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(modid = CandyMod.MODID, name = CandyMod.MODNAME, version = CandyMod.MODVERSION, updateJSON = "https://github.com/ochotonida/candymod/blob/master/update.json")
public class CandyMod {

    public static final String MODID = "candymod";
    public static final String MODNAME = "Candy World";
    public static final String MODVERSION = "0.4.5";

    public static final ItemTab TAB_ITEMS = new ItemTab();
    public static final BlockTab TAB_BLOCKS = new BlockTab();

    public static Logger LOGGER;

    @Mod.Instance
    public static CandyMod instance;

    @SidedProxy(clientSide = "com.ochotonida.candymod.proxy.ClientProxy", serverSide = "com.ochotonida.candymod.proxy.CommonProxy")
    public static CommonProxy proxy;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        ModConfig.loadConfig(event.getSuggestedConfigurationFile());
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init(event);
        ModRecipes.init();
        for (ModBiomes.ModBiomeEntry biomeEntry : ModBiomes.biomeEntryList) {
            if (biomeEntry.getWeight() > 0) {
                BiomeManager.addBiome(biomeEntry.getType(), biomeEntry.getEntry());
                BiomeManager.addStrongholdBiome(biomeEntry.getBiome());
            }
        }
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public static void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new TeleportCommand());
        if (ModConfig.preventModdedMobspawnNuclear) {
            for (ModBiomes.ModBiomeEntry biomeEntry : ModBiomes.biomeEntryList) {
                biomeEntry.getBiome().initSpawnList();
            }
        }
    }

    @Mod.EventBusSubscriber
    private static class RegistrationHandler {

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerBiomes(RegistryEvent.Register<Biome> event) {
            ModBiomes.registerBiomes(event);
        }


        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }

    private static final class BlockTab extends CreativeTabs {

        public BlockTab() {
            super(CandyMod.MODID + ".blocks");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.WAFER_STICK);
        }
    }

    private static final class ItemTab extends CreativeTabs {

        public ItemTab() {
            super(CandyMod.MODID + ".items");
        }

        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.WAFER_STICK);
        }

    }
}
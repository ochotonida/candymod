package com.ochotonida.candymod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ModConfig {

    public static final String CATEGORY_BIOMES = "biomes";
    public static final String CATEGORY_MOBS = "mobs";
    public static final String CATEGORY_DIMENSION = "dimension";
    public static Configuration config;
    public static int dimensionId = 23;
    public static int weightCottonCandyPlains = 1;
    public static int weightCottonCandyPlainsDim = 15;
    public static int weightChocolateForest = 1;
    public static int weightChocolateForestDim = 18;
    public static int weightGummySwamp = 1;
    public static int weightGummySwampDim = 11;
    public static int weightCottonCandySheep = 14;
    public static int weightEasterChicken = 14;
    public static int weightGummyMouse = 14;
    public static int weightGummyBear = 11;
    public static boolean disableTeleporter = false;
    public static boolean isGummyTransluscent = true;
    public static boolean preventModdedMobSpawn = false;
    public static boolean recursiveTreeTrunks = false;
    public static boolean stackableTreeTrunks = true;

    public static void loadConfig(File configFile) {
        config = new Configuration(configFile);
        config.load();
        init();
        MinecraftForge.EVENT_BUS.register(new ChangeListener());
    }

    public static void init() {
        String comment;

        comment = "Dimension id to use for the candy world dimension";
        dimensionId = loadInt(CATEGORY_DIMENSION, "candymod.dimensionId", comment, dimensionId);
        comment = "Setting this to true will prevent players from teleporting to the dimension";
        disableTeleporter = loadBool(CATEGORY_DIMENSION, "candymod.disableTeleporter", comment, disableTeleporter);

        comment = "Overworld cotton candy plains biome weight. 0 to prevent generation in overworld";
        weightCottonCandyPlains = loadInt(CATEGORY_BIOMES, "candymod.weightCottonCandyPlains", comment, weightCottonCandyPlains);
        comment = "Overworld chocolate forest biome weight. 0 to prevent generation in overworld";
        weightChocolateForest = loadInt(CATEGORY_BIOMES, "candymod.weightChocolateForest", comment, weightChocolateForest);
        comment = "Overworld gummy swamp biome weight. 0 to prevent generation in overworld";
        weightGummySwamp = loadInt(CATEGORY_BIOMES, "candymod.weightGummySwamp", comment, weightGummySwamp);

        comment = "Dimension cotton candy plains biome weight";
        weightCottonCandyPlainsDim = loadInt(CATEGORY_BIOMES, "candymod.weightCottonCandyPlainsDim", comment, weightCottonCandyPlainsDim);
        comment = "Dimension chocolate forest biome weight";
        weightChocolateForestDim = loadInt(CATEGORY_BIOMES, "candymod.weightChocolateForestDim", comment, weightChocolateForestDim);
        comment = "Dimension gummy swamp biome weight";
        weightGummySwampDim = loadInt(CATEGORY_BIOMES, "candymod.weightGummySwampDim", comment, weightGummySwampDim);

        comment = "Cotton candy sheep weight. 0 to prevent spawning";
        weightCottonCandySheep = loadInt(CATEGORY_MOBS, "candymod.weightCottonCandySheep", comment, weightCottonCandySheep);
        comment = "Easter chicken weight. 0 to prevent spawning";
        weightEasterChicken = loadInt(CATEGORY_MOBS, "candymod.weightEasterChicken", comment, weightEasterChicken);
        comment = "Gummy mice weight. 0 to prevent spawning";
        weightGummyMouse = loadInt(CATEGORY_MOBS, "candymod.weightGummyMouse", comment, weightGummyMouse);
        comment = "Gummy bear weight. 0 to prevent spawning";
        weightGummyBear = loadInt(CATEGORY_MOBS, "candymod.weightGummyBear", comment, weightGummyBear);

        comment = "setting this to true should prevent any non-Candy World mobs from spawning in candy world biomes";
        preventModdedMobSpawn = loadBool(CATEGORY_MOBS, "candymod.preventModdedMobspawn", comment, preventModdedMobSpawn);

        comment = "Setting this to false will make the game render gummy blocks solid, improving performance";
        isGummyTransluscent = loadBool(Configuration.CATEGORY_CLIENT, "candymod.isGummyTransluscent", comment, isGummyTransluscent);

        comment = "Setting this to true will make tree trunks take longer to mine the higher they are";
        recursiveTreeTrunks = loadBool(Configuration.CATEGORY_GENERAL, "candymod.recursiveTreeTrunks", comment, recursiveTreeTrunks);
        comment = "Setting this to false will make tree trunk blocks behave like normal blocks";
        stackableTreeTrunks = loadBool(Configuration.CATEGORY_GENERAL, "candymod.stackableTreeTrunks", comment, stackableTreeTrunks);


        if (config.hasChanged()) {
            config.save();
        }
    }

    public static int loadInt(String category, String name, String comment, int def) {
        final Property prop = config.get(category, name, def);
        prop.setComment(comment);
        int val = prop.getInt(def);
        if (val < 0) {
            val = 0;
            prop.set(0);
        }

        return val;
    }

    public static boolean loadBool(String category, String name, String comment, boolean def) {
        final Property prop = config.get(category, name, def);
        prop.setComment(comment);
        return prop.getBoolean(def);
    }

    public static class ChangeListener {
        @SubscribeEvent
        @SuppressWarnings("unused")
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(CandyMod.MODID)) {
                init();
            }
        }
    }
}

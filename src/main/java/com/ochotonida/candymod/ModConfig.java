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
    public static Configuration config;
    public static int weightCottonCanyPlains = 4;
    public static int weightChocolateForest = 4;
    public static int weightGummySwamp = 4;
    public static int weightCottonCandySheep = 14;
    public static int weightEasterChicken = 14;
    public static int weightGummyMouse = 14;
    public static int weightGummyBear = 11;
    public static boolean isGummyTransluscent = true;
    public static boolean preventModdedMobSpawn = false;
    public static boolean preventModdedMobspawnNuclear = false;

    public static void loadConfig(File configFile) {
        config = new Configuration(configFile);
        config.load();
        init();
        MinecraftForge.EVENT_BUS.register(new ChangeListener());
    }

    public static void init() {
        String comment;

        comment = "Cotton candy plains biome weight. 0 to prevent generation in overworld";
        weightCottonCanyPlains = loadInt(CATEGORY_BIOMES, "candymod.weightCandyCottonPlains", comment, weightCottonCanyPlains);
        comment = "Chocolate forest biome weight. 0 to prevent generation in overworld";
        weightChocolateForest = loadInt(CATEGORY_BIOMES, "candymod.weightChocolateForest", comment, weightChocolateForest);
        comment = "Gummy swamp biome weight. 0 to prevent generation in overworld";
        weightGummySwamp = loadInt(CATEGORY_BIOMES, "candymod.weightGummySwamp", comment, weightGummySwamp);

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
        comment += " (last-resort nuclear option, try other solutions first) \n" +
                "This will clear the spawn of the biomes list right before the server starts, if that doesn't work, I don't know what else will";
        preventModdedMobspawnNuclear = loadBool(CATEGORY_MOBS, "candymod.preventModdedMobspawnNuclear", comment, preventModdedMobspawnNuclear);

        comment = "Setting this to false will make the game render gummy blocks solid, improving performance";
        isGummyTransluscent = loadBool(Configuration.CATEGORY_CLIENT, "candymod.isGummyTransluscent", comment, isGummyTransluscent);

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

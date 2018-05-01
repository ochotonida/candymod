package com.ochotonida.candymod.entity;

import com.ochotonida.candymod.CandyMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTables {

    public static ResourceLocation ENTITY_BEAR_RED;
    public static ResourceLocation ENTITY_BEAR_ORANGE;
    public static ResourceLocation ENTITY_BEAR_YELLOW;
    public static ResourceLocation ENTITY_BEAR_WHITE;
    public static ResourceLocation ENTITY_BEAR_GREEN;

    public static ResourceLocation ENTITY_MOUSE_RED;
    public static ResourceLocation ENTITY_MOUSE_ORANGE;
    public static ResourceLocation ENTITY_MOUSE_YELLOW;
    public static ResourceLocation ENTITY_MOUSE_WHITE;
    public static ResourceLocation ENTITY_MOUSE_GREEN;

    public static void init() {
        ENTITY_BEAR_RED = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_bear/bear_red"));
        ENTITY_BEAR_ORANGE = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_bear/bear_orange"));
        ENTITY_BEAR_YELLOW = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_bear/bear_yellow"));
        ENTITY_BEAR_WHITE = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_bear/bear_white"));
        ENTITY_BEAR_GREEN = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_bear/bear_green"));

        ENTITY_MOUSE_RED = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_mouse/mouse_red"));
        ENTITY_MOUSE_ORANGE = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_mouse/mouse_orange"));
        ENTITY_MOUSE_YELLOW = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_mouse/mouse_yellow"));
        ENTITY_MOUSE_WHITE = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_mouse/mouse_white"));
        ENTITY_MOUSE_GREEN = LootTableList.register(new ResourceLocation(CandyMod.MODID, "entities/gummy_mouse/mouse_green"));
    }
}

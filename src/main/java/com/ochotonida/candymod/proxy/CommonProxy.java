package com.ochotonida.candymod.proxy;

import com.ochotonida.candymod.ModEntities;
import com.ochotonida.candymod.entity.LootTables;
import com.ochotonida.candymod.world.TerrainEventHandlers;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        //Dimension.init();
        ModEntities.init();
        LootTables.init();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEventHandlers());
    }

    public void postInit(FMLPostInitializationEvent event) {
    }

    // Clientside
    public void registerItemRenderer(Item item, int meta, String location) {
    }

    public void registerColoring() {
    }
}

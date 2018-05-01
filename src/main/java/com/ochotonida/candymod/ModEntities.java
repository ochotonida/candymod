package com.ochotonida.candymod;

import com.ochotonida.candymod.client.renderer.RenderCandySheep;
import com.ochotonida.candymod.client.renderer.RenderEasterChicken;
import com.ochotonida.candymod.client.renderer.RenderGummyBear;
import com.ochotonida.candymod.client.renderer.RenderGummyMouse;
import com.ochotonida.candymod.entity.EntityCandySheep;
import com.ochotonida.candymod.entity.EntityEasterChicken;
import com.ochotonida.candymod.entity.EntityGummyBear;
import com.ochotonida.candymod.entity.EntityGummyMouse;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModEntities {

    private static int entityId = 0;

    /**
     * Registers all entities
     */
    public static void init() {
        registerEntity("cotton_candy_sheep", EntityCandySheep.class, 0xff33ff, 0xffccff);
        registerEntity("easter_chicken", EntityEasterChicken.class, 0x996611, 0x774411);
        registerEntity("gummy_mouse", EntityGummyMouse.class, 0x00ff00, 0x33bb33);
        registerEntity("gummy_bear", EntityGummyBear.class, 0x000000, 0xffffff);
    }

    /**
     * Registers an entity
     */
    private static void registerEntity(String name, Class<? extends Entity> entityClass, int eggPrimary, int eggSecondary) {
        EntityRegistry.registerModEntity(new ResourceLocation(CandyMod.MODID, name), entityClass, name, ++entityId,
                CandyMod.instance, 64, 3, true, eggPrimary, eggSecondary);
    }

    /**
     * Registers all entity models
     */
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCandySheep.class, RenderCandySheep.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityEasterChicken.class, RenderEasterChicken.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGummyMouse.class, RenderGummyMouse.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityGummyBear.class, RenderGummyBear.FACTORY);
    }
}

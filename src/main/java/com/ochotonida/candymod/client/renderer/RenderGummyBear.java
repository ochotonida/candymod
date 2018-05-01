package com.ochotonida.candymod.client.renderer;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.client.model.ModelGummyBear;
import com.ochotonida.candymod.entity.EntityGummyBear;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.ParametersAreNonnullByDefault;

public class RenderGummyBear extends RenderLiving<EntityGummyBear> {

    public static final Factory FACTORY = new RenderGummyBear.Factory();
    private static final ResourceLocation POLAR_BEAR_TEXTURE = new ResourceLocation(CandyMod.MODID, "textures/entity/gummy_bear/gummy_bear.png");

    public RenderGummyBear(RenderManager manager) {
        super(manager, new ModelGummyBear(), 0.7F);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected ResourceLocation getEntityTexture(EntityGummyBear entity) {
        return POLAR_BEAR_TEXTURE;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void doRender(EntityGummyBear entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityGummyBear entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    public static class Factory implements IRenderFactory<EntityGummyBear> {

        @Override
        public Render<? super EntityGummyBear> createRenderFor(RenderManager manager) {
            return new RenderGummyBear(manager);
        }
    }
}

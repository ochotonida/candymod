package com.ochotonida.candymod.client.renderer;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.entity.EntityEasterChicken;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.ParametersAreNonnullByDefault;


public class RenderEasterChicken extends RenderLiving<EntityEasterChicken> {

    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation(CandyMod.MODID, "textures/entity/easter_chicken/easter_chicken.png");


    private RenderEasterChicken(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelChicken(), 0.3F);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected ResourceLocation getEntityTexture(EntityEasterChicken entity) {
        return CHICKEN_TEXTURES;
    }

    @Override
    protected float handleRotationFloat(EntityEasterChicken livingBase, float partialTicks) {
        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }

    public static class Factory implements IRenderFactory<EntityEasterChicken> {

        @Override
        public Render<? super EntityEasterChicken> createRenderFor(RenderManager manager) {
            return new RenderEasterChicken(manager);
        }
    }
}

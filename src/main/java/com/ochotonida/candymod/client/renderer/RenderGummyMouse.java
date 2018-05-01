package com.ochotonida.candymod.client.renderer;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.client.model.ModelGummyMouse;
import com.ochotonida.candymod.entity.EntityGummyMouse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@SideOnly(Side.CLIENT)
public class RenderGummyMouse extends RenderLiving<EntityGummyMouse> {

    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation TEXTURES = new ResourceLocation(CandyMod.MODID, "textures/entity/gummy_mouse/gummy_mouse.png");

    private RenderGummyMouse(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelGummyMouse(), 0.25F);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected ResourceLocation getEntityTexture(EntityGummyMouse entity) {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityGummyMouse> {

        @Override
        public Render<? super EntityGummyMouse> createRenderFor(RenderManager manager) {
            return new RenderGummyMouse(manager);
        }
    }
}

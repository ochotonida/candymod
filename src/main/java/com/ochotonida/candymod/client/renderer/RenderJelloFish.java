package com.ochotonida.candymod.client.renderer;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.client.model.ModelJelloFish;
import com.ochotonida.candymod.entity.EntityJelloFish;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@SideOnly(Side.CLIENT)
public class RenderJelloFish extends RenderLiving<EntityJelloFish> {

    public static final RenderJelloFish.Factory FACTORY = new RenderJelloFish.Factory();
    private static final ResourceLocation TEXTURES = new ResourceLocation(CandyMod.MODID, "textures/entity/jello_fish/jello_fish.png");

    private RenderJelloFish(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelJelloFish(), 0.25F);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected ResourceLocation getEntityTexture(EntityJelloFish entity) {
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityJelloFish> {

        @Override
        public Render<? super EntityJelloFish> createRenderFor(RenderManager manager) {
            return new RenderJelloFish(manager);
        }
    }

}

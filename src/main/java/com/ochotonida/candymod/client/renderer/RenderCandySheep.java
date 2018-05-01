package com.ochotonida.candymod.client.renderer;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.client.model.ModelCandySheep2;
import com.ochotonida.candymod.client.renderer.layers.LayerCandySheepWool;
import com.ochotonida.candymod.entity.EntityCandySheep;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@SideOnly(Side.CLIENT)
public class RenderCandySheep extends RenderLiving<EntityCandySheep> {

    public static final Factory FACTORY = new Factory();
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation(CandyMod.MODID, "textures/entity/candy_sheep/candy_sheep.png");

    private RenderCandySheep(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelCandySheep2(), 0.7F);
        this.addLayer(new LayerCandySheepWool(this));
    }

    @Override
    @ParametersAreNonnullByDefault
    protected ResourceLocation getEntityTexture(EntityCandySheep entity) {
        return SHEARED_SHEEP_TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityCandySheep> {

        @Override
        public Render<? super EntityCandySheep> createRenderFor(RenderManager manager) {
            return new RenderCandySheep(manager);
        }

    }
}
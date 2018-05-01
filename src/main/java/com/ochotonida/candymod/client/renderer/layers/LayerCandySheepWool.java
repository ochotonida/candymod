package com.ochotonida.candymod.client.renderer.layers;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.client.model.ModelCandySheep1;
import com.ochotonida.candymod.client.renderer.RenderCandySheep;
import com.ochotonida.candymod.entity.EntityCandySheep;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

@SideOnly(Side.CLIENT)
public class LayerCandySheepWool implements LayerRenderer<EntityCandySheep> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(CandyMod.MODID, "textures/entity/candy_sheep/candy_sheep_fur.png");
    private final RenderCandySheep sheepRenderer;
    private final ModelCandySheep1 sheepModel = new ModelCandySheep1();

    public LayerCandySheepWool(RenderCandySheep sheepRendererIn) {
        this.sheepRenderer = sheepRendererIn;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void doRenderLayer(EntityCandySheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            this.sheepRenderer.bindTexture(TEXTURE);
            this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
            this.sheepModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
            this.sheepModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }

}

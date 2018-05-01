package com.ochotonida.candymod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelJelloFish extends ModelBase {
    public ModelRenderer bodyOuter;
    public ModelRenderer bodyInner;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer[] legs = new ModelRenderer[6];

    public ModelJelloFish() {
        this.textureWidth = 28;
        this.textureHeight = 20;
        this.leg2 = new ModelRenderer(this, 0, 0);
        this.leg2.setRotationPoint(0.43F, 2.0F, 0.25F);
        this.leg2.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(leg2, 0.0F, 1.0471975511965976F, 0.0F);
        this.bodyInner = new ModelRenderer(this, 0, 12);
        this.bodyInner.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.bodyInner.addBox(-2.5F, -1.0F, -2.5F, 5, 3, 5, 0.0F);
        this.leg5 = new ModelRenderer(this, 0, 0);
        this.leg5.setRotationPoint(-0.43F, 2.0F, -0.25F);
        this.leg5.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(leg5, 0.0F, -2.0943951023931953F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 0);
        this.leg3.setRotationPoint(0.43F, 2.0F, -0.25F);
        this.leg3.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(leg3, 0.0F, 2.0943951023931953F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 0);
        this.leg4.setRotationPoint(0.0F, 2.0F, -0.5F);
        this.leg4.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(leg4, 0.0F, 3.141592653589793F, 0.0F);
        this.leg6 = new ModelRenderer(this, 0, 0);
        this.leg6.setRotationPoint(-0.43F, 2.0F, 0.25F);
        this.leg6.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.setRotateAngle(leg6, 0.0F, -1.0471975511965976F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 0);
        this.leg1.setRotationPoint(0.0F, 2.0F, 0.5F);
        this.leg1.addBox(-0.5F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
        this.bodyOuter = new ModelRenderer(this, 0, 0);
        this.bodyOuter.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.bodyOuter.addBox(-3.5F, -2.0F, -3.5F, 7, 5, 7, 0.0F);
        this.bodyInner.addChild(this.leg2);
        this.bodyInner.addChild(this.leg5);
        this.bodyInner.addChild(this.leg3);
        this.bodyInner.addChild(this.leg4);
        this.bodyInner.addChild(this.leg6);
        this.bodyInner.addChild(this.leg1);
        legs[0] = leg1;
        legs[1] = leg2;
        legs[2] = leg3;
        legs[3] = leg4;
        legs[4] = leg5;
        legs[5] = leg6;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bodyInner.render(f5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
        this.bodyOuter.render(f5);
        GlStateManager.disableBlend();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        float value = MathHelper.sin(ageInTicks * 0.6F) * (float) Math.PI * 0.04F;
        for (ModelRenderer leg : legs) {
            leg.rotateAngleX = value;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

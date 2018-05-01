package com.ochotonida.candymod.client.model;

import com.ochotonida.candymod.entity.EntityGummyBear;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class ModelGummyBear extends ModelQuadruped {

    public ModelRenderer headOuter;
    public ModelRenderer bodyOuter;
    public ModelRenderer leg1Outer;
    public ModelRenderer leg2Outer;
    public ModelRenderer leg3Outer;
    public ModelRenderer leg4Outer;

    public ModelRenderer leg1;
    public ModelRenderer leg4;
    public ModelRenderer leg2;
    public ModelRenderer leg3;

    public ModelGummyBear() {
        super(12, 0.0F);

        this.textureWidth = 128;
        this.textureHeight = 96;

        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.5F, -3.0F, -3.0F, 7, 7, 7, 0.0F);
        this.head.setRotationPoint(0.0F, 10.0F, -16.0F);

        this.headOuter = new ModelRenderer(this, 0, 0);
        this.headOuter.setRotationPoint(0.0F, 10.0F, -16.0F);
        this.headOuter.setTextureOffset(13, 49).addBox(-4.5F, -4.0F, -4.0F, 9, 9, 9, 0.0F);
        this.headOuter.setTextureOffset(0, 44).addBox(-2.5F, 1.0F, -6.0F, 5, 3, 3, 0.0F);
        this.headOuter.setTextureOffset(26, 0).addBox(-5.0F, -4.5F, -1.0F, 2, 2, 1, 0.0F);
        ModelRenderer modelrenderer = this.headOuter.setTextureOffset(26, 0);
        modelrenderer.mirror = true;
        modelrenderer.addBox(3.0F, -4.5F, -1.0F, 2, 2, 1, 0.0F);

        this.body = new ModelRenderer(this);
        this.body.setTextureOffset(0, 19).addBox(-5.0F, -13.0F, -7.0F, 14, 14, 11, 0.0F);
        this.body.setTextureOffset(39, 0).addBox(-4.0F, -25.0F, -7.0F, 12, 12, 10, 0.0F);
        this.body.setRotationPoint(-2.0F, 9.0F, 12.0F);

        this.bodyOuter = new ModelRenderer(this);
        this.bodyOuter.setRotationPoint(-2.0F, 9.0F, 12.0F);
        this.bodyOuter.setTextureOffset(0, 67).addBox(-6.0F, -14.0F, -8.0F, 16, 16, 13, 0.0F);
        this.bodyOuter.setTextureOffset(58, 70).addBox(-5.0F, -26.0F, -8.0F, 14, 14, 12, 0.0F);

        this.leg1 = new ModelRenderer(this, 50, 22);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg1.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.leg2 = new ModelRenderer(this, 50, 22);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 8, 0.0F);
        this.leg2.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.leg3 = new ModelRenderer(this, 50, 40);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg3.setRotationPoint(-2.5F, 14.0F, -7.0F);
        this.leg4 = new ModelRenderer(this, 50, 40);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 6, 0.0F);
        this.leg4.setRotationPoint(2.5F, 14.0F, -7.0F);

        this.leg1Outer = new ModelRenderer(this, 100, 32);
        this.leg1Outer.setRotationPoint(-3.5F, 14.0F, 6.0F);
        this.leg1Outer.addBox(-2.5F, -1.0F, -2.5F, 5, 11, 9, 0.0F);
        this.leg2Outer = new ModelRenderer(this, 100, 32);
        this.leg2Outer.setRotationPoint(3.5F, 14.0F, 6.0F);
        this.leg2Outer.addBox(-2.5F, -1.0F, -2.5F, 5, 11, 9, 0.0F);
        this.leg3Outer = new ModelRenderer(this, 104, 52);
        this.leg3Outer.setRotationPoint(-2.5F, 14.0F, -8.0F);
        this.leg3Outer.addBox(-2.5F, -1.0F, -2.5F, 5, 11, 7, 0.0F);
        this.leg4Outer = new ModelRenderer(this, 104, 52);
        this.leg4Outer.setRotationPoint(2.5F, 14.0F, -8.0F);
        this.leg4Outer.addBox(-2.5F, -1.0F, -2.5F, 5, 11, 7, 0.0F);

        --this.leg1.rotationPointX;
        --this.leg1Outer.rotationPointX;
        ++this.leg2.rotationPointX;
        ++this.leg2Outer.rotationPointX;
        --this.leg3.rotationPointX;
        --this.leg3Outer.rotationPointX;
        ++this.leg4.rotationPointX;
        ++this.leg4Outer.rotationPointX;
        --this.leg3.rotationPointZ;
        --this.leg3Outer.rotationPointZ;
        --this.leg4.rotationPointZ;
        --this.leg4Outer.rotationPointZ;

        this.childZOffset += 2.0F;
    }

    @Override
    public void render(@Nullable Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        float[] colorComponents = EnumGummy.RED.getColorComponentValues();

        if (entity != null) {
            this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
            colorComponents = ((EntityGummyBear) entity).getColor().getColorComponentValues();
        }

        color(1.0F, 1.0F, colorComponents);

        if (this.isChild) {
            this.childYOffset = 16.0F;
            this.childZOffset = 4.0F;

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.67F, 0.67F, 0.67F);
            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
            this.head.render(scale);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.body.render(scale);
            this.leg1.render(scale);
            this.leg2.render(scale);
            this.leg3.render(scale);
            this.leg4.render(scale);
            GlStateManager.popMatrix();

            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            color(0.8F, 1.0F, colorComponents);

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            this.bodyOuter.render(scale);
            this.leg1Outer.render(scale);
            this.leg2Outer.render(scale);
            this.leg3Outer.render(scale);
            this.leg4Outer.render(scale);
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.67F, 0.67F, 0.67F);
            GlStateManager.translate(0.0F, this.childYOffset * scale, this.childZOffset * scale);
            this.headOuter.render(scale);
            GlStateManager.popMatrix();

            GlStateManager.disableBlend();
            return;
        }

        this.head.render(scale);
        this.body.render(scale);
        this.leg1.render(scale);
        this.leg2.render(scale);
        this.leg3.render(scale);
        this.leg4.render(scale);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        color(0.8F, 1.0F, colorComponents);
        this.bodyOuter.render(scale);
        this.leg1Outer.render(scale);
        this.leg2Outer.render(scale);
        this.leg3Outer.render(scale);
        this.leg4Outer.render(scale);
        this.headOuter.render(scale);
        GlStateManager.disableBlend();
    }

    @SuppressWarnings("SameParameterValue")
    private void color(float alpha, float saturation, float[] colorComponents) {
        GlStateManager.color(1 - saturation * (1 - colorComponents[0]),
                1 - saturation * (1 - colorComponents[1]),
                1 - saturation * (1 - colorComponents[2]), alpha);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleX = ((float) Math.PI / 2F);
        this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.headOuter.rotateAngleX = this.head.rotateAngleX;
        this.headOuter.rotateAngleY = this.head.rotateAngleY;
        this.bodyOuter.rotateAngleX = this.body.rotateAngleX;
        this.leg1Outer.rotateAngleX = this.leg1.rotateAngleX;
        this.leg2Outer.rotateAngleX = this.leg2.rotateAngleX;
        this.leg3Outer.rotateAngleX = this.leg3.rotateAngleX;
        this.leg4Outer.rotateAngleX = this.leg4.rotateAngleX;

        this.head.offsetY = MathHelper.sin(ageInTicks * 0.04F) * 0.02F;
        this.body.offsetY = MathHelper.sin(ageInTicks * 0.04F + (float) Math.PI / 2) * 0.02F;

        float f = ageInTicks - (float) entityIn.ticksExisted;
        float f1 = ((EntityPolarBear) entityIn).getStandingAnimationScale(f);
        f1 = f1 * f1;
        float f2 = 1.0F - f1;

        this.body.rotateAngleX = ((float) Math.PI / 2F) - f1 * (float) Math.PI * 0.35F;
        this.bodyOuter.rotateAngleX = this.body.rotateAngleX;

        this.body.rotationPointY = 9.0F * f2 + 11.0F * f1;
        this.bodyOuter.rotationPointY = this.body.rotationPointY;

        this.leg3.rotationPointY = 14.0F * f2 + -6.0F * f1;
        this.leg3Outer.rotationPointY = this.leg3.rotationPointY;

        this.leg3.rotationPointZ = -8.0F * f2 + -4.0F * f1;
        this.leg3Outer.rotationPointZ = this.leg3.rotationPointZ;

        this.leg3.rotateAngleX -= f1 * (float) Math.PI * 0.45F;
        this.leg3Outer.rotateAngleX = this.leg3.rotateAngleX;

        this.leg4.rotationPointY = this.leg3.rotationPointY;
        this.leg4Outer.rotationPointY = this.leg4.rotationPointY;

        this.leg4.rotationPointZ = this.leg3.rotationPointZ;
        this.leg4Outer.rotationPointZ = this.leg4.rotationPointZ;

        this.leg4.rotateAngleX -= f1 * (float) Math.PI * 0.45F;
        this.leg4Outer.rotateAngleX = this.leg4.rotateAngleX;

        this.head.rotationPointY = 10.0F * f2 + -12.0F * f1;
        this.headOuter.rotationPointY = this.head.rotationPointY;

        this.head.rotationPointZ = -16.0F * f2 + -3.0F * f1;
        this.headOuter.rotationPointZ = this.head.rotationPointZ;

        this.head.rotateAngleX += f1 * (float) Math.PI * 0.15F;
        this.headOuter.rotateAngleX = this.head.rotateAngleX;
    }
}

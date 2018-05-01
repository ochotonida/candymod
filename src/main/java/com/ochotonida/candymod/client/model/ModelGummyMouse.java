package com.ochotonida.candymod.client.model;

import com.ochotonida.candymod.entity.EntityGummyMouse;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGummyMouse extends ModelBase {
    public final ModelRenderer body;
    public final ModelRenderer head;
    public final ModelRenderer tail;
    public final ModelRenderer earLeft;
    public final ModelRenderer earRight;
    public final ModelRenderer body_1;
    public final ModelRenderer tail_1;
    public final ModelRenderer head_1;

    public ModelGummyMouse() {
        this.textureWidth = 32;
        this.textureHeight = 22;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, -3.0F);
        this.body.addBox(-2.0F, 0.0F, 0.0F, 4, 3, 6, 0.0F);
        this.tail_1 = new ModelRenderer(this, 18, 8);
        this.tail_1.setRotationPoint(0.0F, 1.0F, 6.0F);
        this.tail_1.addBox(-0.5F, 0.0F, -1.5F, 1, 1, 6, 0.0F);
        this.head = new ModelRenderer(this, 0, 9);
        this.head.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.head.addBox(-1.5F, 0.0F, -3.0F, 3, 2, 3, 0.0F);
        this.earLeft = new ModelRenderer(this, 0, 14);
        this.earLeft.setRotationPoint(0.3F, -0.6F, -2.5F);
        this.earLeft.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.earRight = new ModelRenderer(this, 0, 17);
        this.earRight.setRotationPoint(-0.3F, -0.6F, -2.5F);
        this.earRight.addBox(-1.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.body_1 = new ModelRenderer(this, 16, 15);
        this.body_1.setRotationPoint(-2.7755575615628914E-17F, 21.5F, -2.5F);
        this.body_1.addBox(-1.5F, 0.0F, 0.0F, 3, 2, 5, 0.0F);
        this.tail = new ModelRenderer(this, 0, 14);
        this.tail.setRotationPoint(0.0F, 1.0F, 6.0F);
        this.tail.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 6, 0.0F);
        this.head_1 = new ModelRenderer(this, 22, 4);
        this.head_1.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.head_1.addBox(-1.0F, 0.0F, -3.0F, 2, 1, 3, 0.0F);
        this.body_1.addChild(this.tail_1);
        this.body.addChild(this.head);
        this.head.addChild(this.earLeft);
        this.head.addChild(this.earRight);
        this.body.addChild(this.tail);
        this.body_1.addChild(this.head_1);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        float[] afloat = ((EntityGummyMouse) entity).getColor().getColorComponentValues();
        GlStateManager.color(afloat[0], afloat[1], afloat[2]);
        this.body_1.render(scale);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(afloat[0], afloat[1], afloat[2], 0.6F);
        this.body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.tail.rotateAngleY = MathHelper.sin(ageInTicks * 0.6F) * (float) Math.PI * 0.04F;
        this.tail_1.rotateAngleY = MathHelper.sin(ageInTicks * 0.6F + 0.3F) * (float) Math.PI * 0.03F;
        this.body_1.offsetY = MathHelper.sin(ageInTicks * 0.05F) * 0.02F;
        this.body_1.offsetX = MathHelper.sin(ageInTicks * 0.6F + 0.3F) * 0.004F;
    }
}

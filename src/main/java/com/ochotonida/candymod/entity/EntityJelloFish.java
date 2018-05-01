package com.ochotonida.candymod.entity;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.World;

public class EntityJelloFish extends EntityWaterMob {

    /**
     * ticks since last time swum
     */
    public int swimTimer = 0;

    public EntityJelloFish(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    public void swim() {
        if (this.inWater) {
            this.motionY = world.isAirBlock(this.getPosition().up()) ? 0.25 : 0.3F + rand.nextFloat() * 0.1;
        }
        this.swimTimer = 0;
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isPushedByWater() {
        return true;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (motionY < -0.015 && this.inWater) {
            motionY = -0.015;
        }
        if (swimTimer > 55 && rand.nextInt(10) == 0) {
            this.swim();
        }
        swimTimer++;
    }

    static class AIMoveRandom extends EntityAIBase {

        @Override
        public boolean shouldExecute() {
            return false;
        }
    }
}

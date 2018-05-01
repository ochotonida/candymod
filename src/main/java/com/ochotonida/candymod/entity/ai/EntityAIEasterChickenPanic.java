package com.ochotonida.candymod.entity.ai;

import com.ochotonida.candymod.entity.EntityEasterChicken;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.util.DamageSource;

/**
 * New panic AI, used for explosion
 */
public class EntityAIEasterChickenPanic extends EntityAIPanic {

    private final EntityEasterChicken creature;

    public EntityAIEasterChickenPanic(final EntityEasterChicken creature, double speedIn) {
        super(creature, speedIn);
        this.creature = creature;
    }

    @Override
    public boolean shouldExecute() {
        if (this.creature.explodeWhenDone) {
            return this.findRandomPosition();
        }
        return super.shouldExecute();
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (!this.creature.getNavigator().noPath()) {
            return true;
        }
        if (creature.explodeWhenDone) {
            this.creature.attackEntityFrom(DamageSource.GENERIC, 0.0F);
        }
        return false;
    }
}
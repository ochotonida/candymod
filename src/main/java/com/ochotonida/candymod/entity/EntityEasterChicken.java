package com.ochotonida.candymod.entity;

import com.google.common.collect.Sets;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.entity.ai.EntityAIEasterChickenPanic;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

public class EntityEasterChicken extends EntityAnimal {

    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(
            ModItems.WAFER_STICK,
            ModItems.CHOCOLATE_BAR);

    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    /**
     * This chicken will explode when eggComboAmount reaches 0
     */
    public boolean explodeWhenDone;
    private float wingRotDelta = 1.0F;
    /**
     * The time until the next egg is spawned
     */
    private int timeUntilNextEgg;
    /**
     * Type for the next egg
     */
    private int nextEggType;
    /**
     * Amount of eggs still to be laid in quick succession
     */
    private int eggComboAmount;

    public EntityEasterChicken(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.7F);
        this.timeUntilNextEgg = this.rand.nextInt(4000) + 6000;
        this.setPathPriority(PathNodeType.WATER, 0.0F);
        this.explodeWhenDone = false;
        this.nextEggType = -1;
        this.spawnableBlock = ModBlocks.CANDY_GRASS;
    }

    @Override
    public void spawnRunningParticles() {
        if (this.isSprinting()) {
            this.createRunningParticles();
        }
    }

    @Override
    protected void createRunningParticles() {
        this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIEasterChickenPanic(this, 1.3D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    public float getEyeHeight() {
        return this.height;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!this.isChild() && this.nextEggType == -1 && !this.explodeWhenDone) {
            if (itemStack.getItem() == Items.FLINT_AND_STEEL) {
                firePanic();
                player.swingArm(hand);
                itemStack.damageItem(1, player);
                return true;
            }

            if (itemStack.getItem() == ModItems.CHOCOLATE_BAR) {
                if (!this.world.isRemote) {
                    this.timeUntilNextEgg = 30 + this.rand.nextInt(30);
                    itemStack.shrink(1);
                    this.nextEggType = itemStack.getMetadata();
                }
                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void spawnExplosionParticle() {
        super.spawnExplosionParticle();
        if (this.world.isRemote) {
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        } else {
            this.world.setEntityState(this, (byte) 20);
        }
    }

    private void firePanic() {
        if (!this.world.isRemote) {
            this.timeUntilNextEgg = 20 + this.rand.nextInt(50);
            this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
            this.explodeWhenDone = true;
            this.setSprinting(true);
            this.eggComboAmount = 25 + this.rand.nextInt(20);
            this.playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0F, 1.0F);
        }
    }

    private void explode() {
        this.spawnExplosionParticle();
        this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        for (int i = 0; i <= this.rand.nextInt(5) + 3; i++) {
            EntityItem ent = this.entityDropItem(new ItemStack(Items.FEATHER, 1, 0), 0);
            if (ent != null) {
                ent.motionY += this.rand.nextFloat() * 0.4F;
                ent.motionX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
                ent.motionZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
            }
        }
        for (int i = 0; i <= this.rand.nextInt(3) + 3; i++) {
            EntityItem ent = this.entityDropItem(new ItemStack(ModItems.CHOCOLATE_EGG, 1, this.rand.nextInt(EnumChocolate.META_LOOKUP.length)), 0);
            if (ent != null) {
                ent.motionY += this.rand.nextFloat() * 0.4F;
                ent.motionX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
                ent.motionZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F;
            }
        }
        this.setDead();
    }

    private void dropEgg(int meta) {
        ItemStack stack = new ItemStack(ModItems.CHOCOLATE_EGG, 1, meta);
        EntityItem entityitem = new EntityItem(this.world,
                this.posX - this.motionX * 5, this.posY, this.posZ - this.motionZ * 5, stack);
        entityitem.setDefaultPickupDelay();

        if (this.captureDrops) {
            this.capturedDrops.add(entityitem);
        } else {
            this.world.spawnEntity(entityitem);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.oFlap = this.wingRotation;
        this.oFlapSpeed = this.destPos;
        this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
        this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

        if (!this.onGround && this.wingRotDelta < 1.0F) {
            this.wingRotDelta = 1.0F;
        }

        this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        this.wingRotation += this.wingRotDelta * 2.0F;

        if (this.isBurning()) {
            this.extinguish();
            firePanic();
        }

        // drop an egg
        if (!this.world.isRemote && !this.isChild() && --this.timeUntilNextEgg <= 0) {

            // whether the chicken has been fed chocolate, combo's should not happen
            boolean flag = false;

            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);

            // choose egg type
            int meta = this.rand.nextInt(EnumChocolate.META_LOOKUP.length);
            if (this.nextEggType != -1) {
                meta = this.nextEggType;
                flag = true;
                this.nextEggType = -1;
            }
            this.dropEgg(meta);

            // chance for combo, can only happen when currently not in combo and last egg was not chocolate induced
            if (this.eggComboAmount <= 0 && this.rand.nextInt(100) == 0 && !flag) {
                this.eggComboAmount = this.rand.nextInt(30) + 30;
                this.attackEntityFrom(DamageSource.GENERIC, 0.0F);
            }

            // set time until next egg
            if (this.eggComboAmount-- > 0) {
                this.timeUntilNextEgg = 1;
            } else {
                this.timeUntilNextEgg = this.rand.nextInt(4000) + 6000;
            }

            // check whether the chicken should explode
            if (this.eggComboAmount == 0 && this.explodeWhenDone) {
                this.explode();
            }
        }
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_CHICKEN;
    }

    @Override
    @ParametersAreNonnullByDefault
    public EntityEasterChicken createChild(EntityAgeable ageable) {
        return new EntityEasterChicken(this.world);
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == ModItems.WAFER_STICK;
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("EggLayTime")) {
            this.timeUntilNextEgg = compound.getInteger("EggLayTime");
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("EggLayTime", this.timeUntilNextEgg);
    }
}

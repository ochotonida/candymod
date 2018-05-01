package com.ochotonida.candymod.entity;

import com.ochotonida.candymod.block.ModBlockProperties;
import com.ochotonida.candymod.block.gummy.BlockGummyBase;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class EntityGummyMouse extends EntityAnimal {

    private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(EntityGummyMouse.class, DataSerializers.BYTE);

    public EntityGummyMouse(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.4F);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        switch (getColor()) {
            case RED:
                return LootTables.ENTITY_MOUSE_RED;
            case ORANGE:
                return LootTables.ENTITY_MOUSE_ORANGE;
            case YELLOW:
                return LootTables.ENTITY_MOUSE_YELLOW;
            case WHITE:
                return LootTables.ENTITY_MOUSE_WHITE;
            case GREEN:
                return LootTables.ENTITY_MOUSE_GREEN;
            default:
                return null;
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(COLOR, (byte) 0);
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        IBlockState state = this.getEntityWorld().getBlockState(this.getPosition().down());
        if(state.getBlock() instanceof BlockGummyBase) {
            this.setColor(state.getValue(ModBlockProperties.GUMMY_TYPE));
        } else {
            this.setColor(EnumGummy.random(this.rand));
        }
        return livingdata;
    }

    public EnumGummy getColor() {
        return EnumGummy.byMetadata(this.dataManager.get(COLOR));
    }

    public void setColor(EnumGummy enumgummy) {
        this.dataManager.set(COLOR, (byte) enumgummy.getMetadata());
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityPlayer.class, 1.2F, 0.85F, 1.33F));
        this.tasks.addTask(2, new EntityAIAvoidEntity<>(this, EntityOcelot.class, 8.0F, 0.85F, 1.33F));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_RABBIT_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_RABBIT_DEATH;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("Color", (byte) this.getColor().getMetadata());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setColor(EnumGummy.byMetadata(compound.getByte("Color")));
    }
}

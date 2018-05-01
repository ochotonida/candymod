package com.ochotonida.candymod.entity;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.entity.ai.EntityAIEatCandyGrass;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

// TODO loottable
public class EntityCandySheep extends EntityAnimal { //

    private static final DataParameter<Boolean> SHEARED = EntityDataManager.createKey(EntityCandySheep.class, DataSerializers.BOOLEAN);
    private int sheepTimer;
    private EntityAIEatCandyGrass entityAIEatCandyGrass;

    public EntityCandySheep(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 1.3F);
        this.spawnableBlock = ModBlocks.CANDY_GRASS;
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.getDataManager().register(SHEARED, Boolean.FALSE);
    }

    @Override
    public void initEntityAI() {
        this.entityAIEatCandyGrass = new EntityAIEatCandyGrass(this);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(3, new EntityAITempt(this, 1.1D, Items.SUGAR, false));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, this.entityAIEatCandyGrass);
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    public void updateAITasks() {
        this.sheepTimer = this.entityAIEatCandyGrass.getEatingGrassTimer();
        super.updateAITasks();
    }

    @Override
    public void onLivingUpdate() {
        if (this.world.isRemote) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }

        super.onLivingUpdate();
    }

    @Override
    public void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
    }

    @Override
    public ResourceLocation getLootTable() {
        if (this.getSheared()) {
            return LootTableList.ENTITIES_SHEEP;
        } else {
            return LootTableList.ENTITIES_SHEEP;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.sheepTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean processInteract(EntityPlayer player, EnumHand hand) {

        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.STICK && !this.getSheared() && !this.isChild()) {

            player.swingArm(hand);

            if (!this.world.isRemote) {
                this.setSheared(true);
                if (itemstack.getCount() == 1) {
                    // changes the held stick to cotton candy
                    player.setHeldItem(hand, new ItemStack(ModItems.COTTON_CANDY));
                } else {
                    itemstack.shrink(1);
                    if (!player.addItemStackToInventory(new ItemStack(ModItems.COTTON_CANDY, 1))) {
                        // drop cotton candy
                        player.dropItem(new ItemStack(ModItems.COTTON_CANDY), false);
                    }
                }
                this.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 1.0F, 1.0F);
            }
            return true;
        }

        // bone meal
        if (itemstack.getItem() == Items.DYE && EnumDyeColor.byDyeDamage(itemstack.getMetadata()) == EnumDyeColor.WHITE && this.getSheared()) {

            player.swingArm(hand);

            if (!this.world.isRemote) {
                this.setSheared(false);
                itemstack.shrink(1);
                this.playSound(SoundEvents.BLOCK_CLOTH_PLACE, 1.0F, 1.0F);
                this.spawnExplosionParticle();
            }
            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.SUGAR;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 5;
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationPointY(float p_70894_1_) {
        if (this.sheepTimer <= 0) {
            return 0.0F;
        } else if (this.sheepTimer >= 4 && this.sheepTimer <= 36) {
            return 1.0F;
        } else {
            return this.sheepTimer < 4 ? ((float) this.sheepTimer - p_70894_1_) / 4.0F : -((float) (this.sheepTimer - 40) - p_70894_1_) / 4.0F;
        }
    }

    @SideOnly(Side.CLIENT)
    public float getHeadRotationAngleX(float p_70890_1_) {
        if (this.sheepTimer > 4 && this.sheepTimer <= 36) {
            float f = ((float) (this.sheepTimer - 4) - p_70890_1_) / 32.0F;
            return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
        } else {
            return this.sheepTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sheared", this.getSheared());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SHEEP_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SHEEP_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SHEEP_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
    }


    public boolean getSheared() {
        return this.getDataManager().get(SHEARED);
    }


    private void setSheared(boolean sheared) {
        this.getDataManager().set(SHEARED, sheared);
    }

    @Override
    @ParametersAreNonnullByDefault
    public EntityCandySheep createChild(EntityAgeable entity) {
        return new EntityCandySheep(this.world);
    }

    @Override
    public void eatGrassBonus() {
        this.setSheared(false);

        if (this.isChild()) {
            this.addGrowth(60);
        }
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setSheared(false);
        return livingdata;
    }

    @Override
    public float getEyeHeight() {
        return 0.95F * this.height;
    }
}

package com.ochotonida.candymod.entity;

import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGummyBear extends EntityPolarBear {

    private static final DataParameter<Byte> COLOR = EntityDataManager.createKey(EntityGummyBear.class, DataSerializers.BYTE);

    public EntityGummyBear(World worldIn) {
        super(worldIn);
    }

    public EntityGummyBear(World worldIn, EnumGummy color) {
        this(worldIn);
        this.setColor(color);
    }

    public EnumGummy getColor() {
        return EnumGummy.byMetadata(this.dataManager.get(COLOR));
    }

    public void setColor(EnumGummy enumgummy) {
        this.dataManager.set(COLOR, (byte) enumgummy.getMetadata());
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        switch (getColor()) {
            case RED:
                return LootTables.ENTITY_BEAR_RED;
            case ORANGE:
                return LootTables.ENTITY_BEAR_ORANGE;
            case YELLOW:
                return LootTables.ENTITY_BEAR_YELLOW;
            case WHITE:
                return LootTables.ENTITY_BEAR_WHITE;
            case GREEN:
                return LootTables.ENTITY_BEAR_GREEN;
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
        if (livingdata instanceof GroupData) {
            if (((GroupData)livingdata).madeParent) {
                if (!((GroupData)livingdata).madeSecondParent && rand.nextInt(3) == 0) {
                    ((GroupData)livingdata).madeSecondParent = true;
                } else {
                    this.setGrowingAge(-24000);
                }
            }
            if (((GroupData)livingdata).color != null) {
                setColor(((GroupData)livingdata).color);
            }
            else {
                setColor(EnumGummy.random(this.rand));
            }
        }
        else
        {
            GroupData entitygummybear$groupdata = new GroupData();
            entitygummybear$groupdata.madeParent = true;
            entitygummybear$groupdata.color = EnumGummy.random(this.rand);
            setColor(entitygummybear$groupdata.color);
            livingdata = entitygummybear$groupdata;
        }

        return livingdata;
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

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        if (ageable instanceof EntityGummyBear) {
            return new EntityGummyBear(this.world, ((EntityGummyBear) ageable).getColor());
        }
        return new EntityGummyBear(this.world);
    }

    static class GroupData implements IEntityLivingData
    {
        public boolean madeParent;
        public boolean madeSecondParent;
        public EnumGummy color;

        private GroupData()
        {
        }
    }
}

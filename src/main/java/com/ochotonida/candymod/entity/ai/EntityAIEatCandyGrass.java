package com.ochotonida.candymod.entity.ai;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class EntityAIEatCandyGrass extends EntityAIBase {

    private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(ModBlocks.COTTON_CANDY_PLANT);
    private final EntityLiving grassEaterEntity;
    private final World entityWorld;
    private int eatingGrassTimer;

    public EntityAIEatCandyGrass(final EntityLiving grassEaterEntityIn) {
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }

    @Override
    public boolean shouldExecute() {
        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos))) {
                return true;
            } else {
                return this.entityWorld.getBlockState(blockpos.down()) == ModBlocks.CANDY_GRASS.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK);
            }
        }
    }

    @Override
    public void startExecuting() {
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
        this.grassEaterEntity.getNavigator().clearPath();
    }

    @Override
    public void resetTask() {
        this.eatingGrassTimer = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.eatingGrassTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eat grasa
     */
    public int getEatingGrassTimer() {
        return this.eatingGrassTimer;
    }

    @Override
    public void updateTask() {
        this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

        if (this.eatingGrassTimer == 4) {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockpos))) {
                if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                    this.entityWorld.destroyBlock(blockpos, false);
                }

                this.grassEaterEntity.eatGrassBonus();
            } else {
                BlockPos blockpos1 = blockpos.down();

                if (this.entityWorld.getBlockState(blockpos1) == ModBlocks.CANDY_GRASS.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK)) {
                    if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                        // sound played / particles
                        this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(ModBlocks.COTTON_CANDY_LEAVES));
                        // block replacement
                        this.entityWorld.setBlockState(blockpos1, ModBlocks.CANDY_SOIL.getDefaultState(), 2);
                    }

                    this.grassEaterEntity.eatGrassBonus();
                }
            }


        }
    }

}
package com.ochotonida.candymod.item;

import com.ochotonida.candymod.ModConfig;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemTeleporter extends ModFoodItem {

    public ItemTeleporter() {
        super("teleporter", 1, 1.0F);
        setAlwaysEdible();
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entity;
            entityplayer.getFoodStats().addStats(this, stack);
            world.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, world, entityplayer);
            // noinspection ConstantConditions
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP && (ModConfig.disableTeleporter || world.provider.getDimension() == ModConfig.dimensionId)) {
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
            }
        }
        if (!(entity instanceof EntityPlayer) || (world.provider.getDimension() == ModConfig.dimensionId && !((EntityPlayer) entity).capabilities.isCreativeMode) || ModConfig.disableTeleporter) {
            stack.shrink(1);
        }
        if (!world.isRemote && entity instanceof EntityPlayer && !ModConfig.disableTeleporter) {
            if (world.provider.getDimension() == ModConfig.dimensionId) {
                entity.changeDimension(0, new CustomTeleporter());
            } else {
                entity.changeDimension(ModConfig.dimensionId, new CustomTeleporter());
            }
        }
        return stack;
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    private static BlockPos findTargetPos(EntityPlayer player, int targetWorldId) {
        // noinspection ConstantConditions
        World targetWorld = player.getServer().getWorld(targetWorldId);
        BlockPos targetPos = player.getBedLocation(targetWorldId);
        // noinspection ConstantConditions
        if (targetPos != null) {
            targetPos = EntityPlayer.getBedSpawnLocation(targetWorld, targetPos, false);
            if (targetPos == null) {
                player.sendStatusMessage(new TextComponentString("Your home bed was missing or obstructed"), false);
                player.setSpawnChunk(null, false, targetWorldId);
            }
        }
        if (targetPos == null) { // player has no bed, or it is missing/obstructed
            if (targetWorldId == ModConfig.dimensionId) {
                targetPos = targetWorld.getTopSolidOrLiquidBlock(new BlockPos(player));
            } else {
                targetPos = targetWorld.getTopSolidOrLiquidBlock(targetWorld.getSpawnPoint());
            }
        }
        return targetPos;
    }

    public class CustomTeleporter implements ITeleporter {

        public CustomTeleporter() {
        }

        @Override
        public void placeEntity(World world, Entity entity, float yaw) {
            if (!(entity instanceof EntityPlayer)) {
                throw new IllegalArgumentException("This teleporter can only teleport players");
            }
            entity.moveToBlockPosAndAngles(findTargetPos((EntityPlayer) entity, world.provider.getDimension()), yaw, entity.rotationPitch);
        }
    }
}

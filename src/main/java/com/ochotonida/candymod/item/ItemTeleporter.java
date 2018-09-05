package com.ochotonida.candymod.item;

import com.ochotonida.candymod.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemTeleporter extends ModFoodItem {

    public ItemTeleporter() {
        super("teleporter", 1, 1.0F);
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entity) {
        if (!world.isRemote && entity instanceof EntityPlayer && !ModConfig.disableTeleporter) {
            if (world.provider.getDimension() == ModConfig.dimensionId) {
                entity.changeDimension(0, new CustomTeleporter());
            } else {
                entity.changeDimension(ModConfig.dimensionId, new CustomTeleporter());
            }
        }
        return super.onItemUseFinish(stack, world, entity);
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

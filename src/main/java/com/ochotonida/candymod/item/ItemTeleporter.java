package com.ochotonida.candymod.item;

import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.command.CustomTeleporter;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        if (!world.isRemote && entity instanceof EntityPlayer) {
            if (world.provider.getDimension() == ModConfig.dimensionId) {
                entity.changeDimension(0, new CustomTeleporter());
            } else {
                entity.changeDimension(ModConfig.dimensionId, new CustomTeleporter());
            }
        }
        return super.onItemUseFinish(stack, world, entity);
    }
}

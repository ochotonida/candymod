package com.ochotonida.candymod.item;

import com.ochotonida.candymod.command.CustomTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class ItemTeleporter extends ModFoodItem {

    public ItemTeleporter() {
        super("teleporter", 1, 1.0F);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        if (!worldIn.isRemote) {
            CustomTeleporter.teleport(player);
        }
    }
}

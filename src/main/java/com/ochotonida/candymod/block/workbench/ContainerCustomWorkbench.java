package com.ochotonida.candymod.block.workbench;

import com.ochotonida.candymod.interfaces.IWorkbenchBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class ContainerCustomWorkbench extends ContainerWorkbench {

    private final BlockPos pos;
    private final World world;

    public ContainerCustomWorkbench(InventoryPlayer playerInventory, World world, BlockPos pos) {
        super(playerInventory, world, pos);
        this.pos = pos;
        this.world = world;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (!(this.world.getBlockState(this.pos).getBlock() instanceof IWorkbenchBlock)) {
            return false;
        } else {
            return playerIn.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
}

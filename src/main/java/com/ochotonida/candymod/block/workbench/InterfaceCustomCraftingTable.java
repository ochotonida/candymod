package com.ochotonida.candymod.block.workbench;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class InterfaceCustomCraftingTable extends BlockWorkbench.InterfaceCraftingTable {

    private final World world;
    private final BlockPos pos;

    public InterfaceCustomCraftingTable(World world, BlockPos pos) {
        super(world, pos);
        this.pos = pos;
        this.world = world;
    }

    @Nonnull
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerCustomWorkbench(playerInventory, this.world, this.pos);
    }
}

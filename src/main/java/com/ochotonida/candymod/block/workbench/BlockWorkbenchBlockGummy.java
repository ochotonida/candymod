package com.ochotonida.candymod.block.workbench;

import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.gummy.BlockGummyBase;
import com.ochotonida.candymod.interfaces.IWorkbenchBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.GUMMY_TYPE;

public class BlockWorkbenchBlockGummy extends BlockGummyBase implements IWorkbenchBlock {

    public BlockWorkbenchBlockGummy() {
        super("gummy_workbench");
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModBlocks.GUMMY_BLOCK.getItemDropped(ModBlocks.GUMMY_BLOCK.getDefaultState().withProperty(GUMMY_TYPE, state.getValue(GUMMY_TYPE)), rand, fortune);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            playerIn.displayGui(new InterfaceCustomCraftingTable(worldIn, pos));
            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }
}

package com.ochotonida.candymod.block.gummy;

import com.ochotonida.candymod.enums.EnumAxis;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.ochotonida.candymod.block.ModBlockProperties.AXIS;
import static com.ochotonida.candymod.block.ModBlockProperties.GUMMY_TYPE;

public class BlockGummyWorm extends BlockGummyBase {

    public BlockGummyWorm() {
        super("gummy_worm_block");
        this.setDefaultState(super.getDefaultState().withProperty(AXIS, EnumAxis.X));
    }

    @Nonnull
    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, GUMMY_TYPE, AXIS);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(GUMMY_TYPE, EnumGummy.byMetadata(meta % 5))
                .withProperty(AXIS, EnumAxis.byMetadata(meta / 5));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(GUMMY_TYPE).getMetadata() + state.getValue(AXIS).getMetadata() * 5;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state.withProperty(AXIS, EnumAxis.X));
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, this.damageDropped(state));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getStateFromMeta(meta).withProperty(AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
    }
}

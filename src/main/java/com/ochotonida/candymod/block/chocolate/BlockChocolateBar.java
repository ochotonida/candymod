package com.ochotonida.candymod.block.chocolate;

import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockChocolateBar extends BlockChocolate {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private static final AxisAlignedBB AABB_NS = new AxisAlignedBB((2.5 / 16), (0.0 / 16), (5.5 / 16), (13.5 / 16), (14.5 / 16), (10.5 / 16));
    private static final AxisAlignedBB AABB_EW = new AxisAlignedBB((5.5 / 16), (0.0 / 16), (2.5 / 16), (10.5 / 16), (14.5 / 16), (13.5 / 16));

    public BlockChocolateBar() {
        super(Material.CAKE, "chocolate_bar_block");
        this.setDefaultState(this.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.CHOCOLATE_BAR;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(CHOCOLATE_TYPE).getMetadata();
    }


    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
        if (state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
            return AABB_NS;
        }
        return AABB_EW;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        if (state.getValue(FACING) == EnumFacing.NORTH || state.getValue(FACING) == EnumFacing.SOUTH) {
            return AABB_NS;
        }
        return AABB_EW;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumChocolate type = EnumChocolate.byMetadata(meta % 4);
        int facingbits = (meta & 12) >> 2;
        EnumFacing facing = EnumFacing.getHorizontal(facingbits);
        return this.getDefaultState().withProperty(CHOCOLATE_TYPE, type).withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumChocolate type = state.getValue(CHOCOLATE_TYPE);
        EnumFacing facing = state.getValue(FACING);

        int typebits = type.getMetadata();
        int facingbits = facing.getHorizontalIndex() << 2;
        return facingbits | typebits;
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, CHOCOLATE_TYPE, FACING);

    }

    @Nonnull
    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos psos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumChocolate type = EnumChocolate.byMetadata(meta);
        EnumFacing facing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
        return this.getDefaultState().withProperty(CHOCOLATE_TYPE, type).withProperty(FACING, facing);
    }
}

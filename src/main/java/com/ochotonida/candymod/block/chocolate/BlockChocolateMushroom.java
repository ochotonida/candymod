package com.ochotonida.candymod.block.chocolate;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.ModItems;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockChocolateMushroom extends BlockBush implements IShearable, IPlantable {

    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);

    public BlockChocolateMushroom() {
        super(Material.VINE, MapColor.BROWN);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setSoundType(SoundType.PLANT);
        this.setRegistryName("chocolate_mushroom_block");
        this.setUnlocalizedName("chocolate_mushroom_block");
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK));
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumChocolate.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHOCOLATE_TYPE);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(CHOCOLATE_TYPE).getMetadata();
    }

    @Nonnull
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public boolean canSustainBush(IBlockState state) {
        return state.getBlock() == ModBlocks.CANDY_GRASS
                || state.getBlock() == ModBlocks.CANDY_SOIL
                || state.getBlock() == Blocks.DIRT
                || state.getBlock() == Blocks.GRASS;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
        return true;
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
    public int quantityDropped(Random random) {
        return random.nextInt(15) == 0 ? 1 : 0;
    }

    @Nonnull
    @Override
    public Block.EnumOffsetType getOffsetType() {
        return Block.EnumOffsetType.XYZ;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack stack, IBlockAccess world, BlockPos pos) {
        return stack.getItem() instanceof ItemShears;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack stack, IBlockAccess world, BlockPos pos, int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Item.getItemFromBlock(ModBlocks.CHOCOLATE_MUSHROOM)));
        return drops;
    }
}

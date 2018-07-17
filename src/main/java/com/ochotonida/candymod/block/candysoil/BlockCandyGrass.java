package com.ochotonida.candymod.block.candysoil;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModSoundTypes;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockCandyGrass extends Block implements IGrowable {

    public BlockCandyGrass() {
        super(Material.GRASS, MapColor.PINK);

        this.setUnlocalizedName("candy_grass_block");
        this.setRegistryName("candy_grass_block");

        this.setTickRandomly(true);
        this.setHardness(0.6F);
        this.setSoundType(ModSoundTypes.CANDY_GRASS);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setHarvestLevel("shovel", 0);

        this.setDefaultState(this.blockState.getBaseState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        switch (state.getValue(CHOCOLATE_TYPE).getMetadata()) {
            case 1:
                return MapColor.DIRT;
            case 2:
            default:
                return this.blockMapColor;
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(CHOCOLATE_TYPE).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumChocolate.META_LOOKUP.length - 1; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, Entity entity) {
        if (state.getBlock() != this) {
            return getSoundType();
        }
        switch (state.getValue(CHOCOLATE_TYPE)) {
            case MILK:
                return ModSoundTypes.CANDY_GRASS;
            case WHITE:
                return ModSoundTypes.CANDY_DIRT;
        }
        return getSoundType();
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
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CHOCOLATE_TYPE);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing face, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumChocolate type = EnumChocolate.byMetadata(meta);
        return this.getDefaultState().withProperty(CHOCOLATE_TYPE, type);
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModBlocks.CANDY_SOIL.getItemDropped(ModBlocks.CANDY_SOIL.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK), rand, fortune);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        final Random rand = world instanceof World ? ((World) world).rand : RANDOM;

        final int count = this.quantityDropped(state, fortune, rand);
        for (int i = 0; i < count; i++) {
            final Item item = this.getItemDropped(state, rand, fortune);
            if (item != Items.AIR) {
                drops.add(new ItemStack(item, 1, this.damageDropped(state)));
            }
        }
    }

    @Override
    public void updateTick(final World worldIn, final BlockPos pos, final IBlockState state, final Random rand) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3)) {
                return;
            }

            // grass block is converted to brownie
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, ModBlocks.CANDY_SOIL.getDefaultState().withProperty(CHOCOLATE_TYPE, state.getValue(CHOCOLATE_TYPE)));
            } else {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    // grass tries to spread 3 times
                    for (int i = 0; i < 3; i++) {
                        final BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

                        // block not loaded
                        if (blockpos.getY() >= 0 && blockpos.getY() < 256 && !worldIn.isBlockLoaded(blockpos)) {
                            return;
                        }

                        final IBlockState iblockstate = worldIn.getBlockState(blockpos.up());
                        final IBlockState iblockstate1 = worldIn.getBlockState(blockpos);

                        // block is valid
                        if (iblockstate1 == ModBlocks.CANDY_SOIL.getDefaultState().withProperty(CHOCOLATE_TYPE, state.getValue(CHOCOLATE_TYPE))
                                && worldIn.getLightFromNeighbors(blockpos.up()) >= 4
                                && iblockstate.getLightOpacity(worldIn, pos.up()) <= 2) {
                            worldIn.setBlockState(blockpos, ModBlocks.CANDY_GRASS.getDefaultState().withProperty(CHOCOLATE_TYPE, state.getValue(CHOCOLATE_TYPE)));
                        }
                    }
                }
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSilkHarvest() {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return ModBlocks.COTTON_CANDY_PLANT.canPlaceBlockAt(world, pos.up()) && state == this.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state) {

        return state == this.getDefaultState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void grow(World world, Random rand, BlockPos pos, IBlockState state) {
        world.setBlockState(pos.up(), ModBlocks.COTTON_CANDY_PLANT.getDefaultState());
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        if (plantable == ModBlocks.COTTON_CANDY_SAPLING ||
                plantable == ModBlocks.COTTON_CANDY_PLANT ||
                plantable == ModBlocks.CHOCOLATE_SAPLING) {
            return true;
        }
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

}

package com.ochotonida.candymod.block.candysoil;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModBlocks;
import com.ochotonida.candymod.block.ModSoundTypes;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockCandySoil extends Block {

    public BlockCandySoil() {
        super(Material.GROUND);

        this.setUnlocalizedName("candy_soil_block");
        this.setRegistryName("candy_soil_block");

        this.setHardness(0.6F);
        this.setSoundType(ModSoundTypes.CANDY_DIRT);
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
        this.setHarvestLevel("shovel", 0);

        this.setDefaultState(this.blockState.getBaseState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK));
    }

    public void registerOreNames() {
        for (EnumChocolate enumChocolate : EnumChocolate.values()) {
            ItemStack stack = new ItemStack(this, 1, enumChocolate.getMetadata());
            OreDictionary.registerOre("blockBrownie", stack);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(CHOCOLATE_TYPE).getMetadata();
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
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing face,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumChocolate type = EnumChocolate.byMetadata(meta);
        return this.getDefaultState().withProperty(CHOCOLATE_TYPE, type);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumChocolate.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
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

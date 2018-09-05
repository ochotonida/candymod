package com.ochotonida.candymod.block.chocolate;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.enums.EnumChocolate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
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
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.ochotonida.candymod.block.ModBlockProperties.CHOCOLATE_TYPE;

public class BlockChocolate extends Block {

    private final String name;
    private final String oreName;

    public BlockChocolate(Material material, String name, @Nullable String oreName) {
        super(material);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.name = name;
        this.oreName = oreName;
        this.setHardness(0.7F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHOCOLATE_TYPE, EnumChocolate.MILK));
        this.setCreativeTab(CandyMod.TAB_BLOCKS);
    }

    public String getName() {
        return name;
    }

    public void registerOreNames() {
        if (oreName == null) {
            return;
        }
        for (EnumChocolate enumChocolate : EnumChocolate.values()) {
            ItemStack stack = new ItemStack(this, 1, enumChocolate.getMetadata());
            OreDictionary.registerOre(oreName, stack);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumChocolate.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
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

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        switch (state.getValue(CHOCOLATE_TYPE)) {
            case MILK:
                return MapColor.BROWN;
            case WHITE:
                return MapColor.SAND;
            case DARK:
                return MapColor.BROWN_STAINED_HARDENED_CLAY;
        }
        return this.blockMapColor;
    }
}

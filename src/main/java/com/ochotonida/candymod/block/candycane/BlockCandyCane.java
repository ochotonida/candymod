package com.ochotonida.candymod.block.candycane;


import com.ochotonida.candymod.ModConfig;
import com.ochotonida.candymod.block.BlockStackable;
import com.ochotonida.candymod.enums.EnumCandyCane;
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

import static com.ochotonida.candymod.block.ModBlockProperties.CANDY_CANE_TYPE;

public class BlockCandyCane extends BlockStackable {

    private final String oreName;

    public BlockCandyCane(String name, String oreName) {
        super(Material.WOOD, MapColor.WHITE_STAINED_HARDENED_CLAY, ModConfig.stackableTreeTrunks, ModConfig.recursiveTreeTrunks, true);
        this.setName(name);
        this.setHardness(1.2F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(CANDY_CANE_TYPE, EnumCandyCane.WHITE));
        this.oreName = oreName;
    }

    public BlockCandyCane(String name) {
        this(name, null);
    }

    public void registerOreNames() {
        if (oreName == null) {
            return;
        }
        for (EnumCandyCane enumCandyCane : EnumCandyCane.values()) {
            ItemStack stack = new ItemStack(this, 1, enumCandyCane.getMetadata());
            OreDictionary.registerOre("blockCandycane", stack);
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(CANDY_CANE_TYPE).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumCandyCane.META_LOOKUP.length; ++i) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(CANDY_CANE_TYPE, EnumCandyCane.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(CANDY_CANE_TYPE).getMetadata();
    }

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CANDY_CANE_TYPE);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing face,
                                            float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        EnumCandyCane type = EnumCandyCane.byMetadata(meta);
        return this.getDefaultState().withProperty(CANDY_CANE_TYPE, type);
    }
}
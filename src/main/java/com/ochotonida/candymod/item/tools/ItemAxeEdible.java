package com.ochotonida.candymod.item.tools;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.interfaces.IItemToolEdible;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemAxeEdible extends ItemAxe implements IItemToolEdible {

    protected final String name;
    protected final String materialName;

    public ItemAxeEdible(String name, String materialName, ToolMaterial material, int healAmount, float saturation) {
        super(material, 8F, -3F);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.name = name;
        this.materialName = materialName;
        this.healAmount = healAmount;
        this.saturationModifier = saturation;
        this.setCreativeTab(CandyMod.TAB_TOOLS);
    }

    public void registerItemModel() {
        CandyMod.proxy.registerItemRenderer(this, 0, "tool/" + materialName + "/" + this.name);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Food implementation
    ///////////////////////////////////////////////////////////////////////////

    private final int healAmount;
    private final float saturationModifier;

    @Override
    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public float getSaturationModifier() {
        return saturationModifier;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.EAT;
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        return IItemToolEdible.super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Nonnull
    @Override
    @ParametersAreNonnullByDefault
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return IItemToolEdible.super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        IItemToolEdible.super.onFoodEaten(stack, worldIn, player);
    }
}

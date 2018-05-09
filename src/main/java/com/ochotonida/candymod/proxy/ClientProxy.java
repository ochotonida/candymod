package com.ochotonida.candymod.proxy;

import com.ochotonida.candymod.CandyMod;
import com.ochotonida.candymod.ModEntities;
import com.ochotonida.candymod.interfaces.IBlockColored;
import com.ochotonida.candymod.interfaces.IItemColored;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private static final List<IBlockColored> coloredBlocks = new ArrayList<>();
    private static final List<IItemColored> coloredItems = new ArrayList<>();

    public static void addColoredBlock(IBlockColored block) {
        if (coloredBlocks.contains(block)) {
            throw new IllegalArgumentException();
        }
        coloredBlocks.add(block);
    }

    @SuppressWarnings("unused")
    public static void addColoredItem(IItemColored item) {
        if (coloredItems.contains(item)) {
            throw new IllegalArgumentException();
        }
        coloredItems.add(item);
    }

    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        ModEntities.initModels();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerColoring();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(CandyMod.MODID + ":" + location, "inventory"));
    }

    @Override
    public void registerColoring() {
        for (IBlockColored block : coloredBlocks) {
            if (block.getBlockColor() != null) {
                Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(block.getBlockColor(), (Block) block);
            }
            if (block.getBlockColor() != null) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(block.getItemColor(), (Block) block);
            }
        }

        for (IItemColored item : coloredItems) {
            if (item.getItemColor() != null) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(item.getItemColor(), (Item) item);
            }
        }
    }
}

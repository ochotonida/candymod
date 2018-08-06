package com.ochotonida.candymod;

import com.ochotonida.candymod.block.BlockSugar;
import com.ochotonida.candymod.block.BlockSugarSand;
import com.ochotonida.candymod.block.BlockWaferStick;
import com.ochotonida.candymod.block.candycane.BlockCandyCane;
import com.ochotonida.candymod.block.candycane.ItemBlockCandyCane;
import com.ochotonida.candymod.block.candysoil.BlockCandyGrass;
import com.ochotonida.candymod.block.candysoil.BlockCandySoil;
import com.ochotonida.candymod.block.candysoil.ItemBlockCandyGrass;
import com.ochotonida.candymod.block.candysoil.ItemBlockCandySoil;
import com.ochotonida.candymod.block.chocolate.*;
import com.ochotonida.candymod.block.cottoncandy.BlockCottonCandyGrass;
import com.ochotonida.candymod.block.cottoncandy.BlockCottonCandyLeaves;
import com.ochotonida.candymod.block.cottoncandy.BlockCottonCandyPlant;
import com.ochotonida.candymod.block.cottoncandy.BlockCottonCandySapling;
import com.ochotonida.candymod.block.gummy.BlockGummy;
import com.ochotonida.candymod.block.gummy.BlockGummySolid;
import com.ochotonida.candymod.block.gummy.BlockGummyWorm;
import com.ochotonida.candymod.block.gummy.ItemBlockGummy;
import com.ochotonida.candymod.block.ore.BlockCookieOre;
import com.ochotonida.candymod.block.ore.ItemBlockCookieOre;
import com.ochotonida.candymod.enums.EnumCandyCane;
import com.ochotonida.candymod.enums.EnumChocolate;
import com.ochotonida.candymod.enums.EnumGummy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.registries.IForgeRegistry;

import static com.ochotonida.candymod.block.fluid.ModFluids.LIQUID_CHOCOLATE;

public class ModBlocks {

    // Regular blocks
    public static final BlockChocolateSapling CHOCOLATE_SAPLING = new BlockChocolateSapling();
    public static final BlockCottonCandyLeaves COTTON_CANDY_LEAVES = new BlockCottonCandyLeaves();
    public static final BlockCottonCandyPlant COTTON_CANDY_PLANT = new BlockCottonCandyPlant();
    public static final BlockCottonCandySapling COTTON_CANDY_SAPLING = new BlockCottonCandySapling();
    public static final BlockCottonCandyGrass COTTON_CANDY_GRASS = new BlockCottonCandyGrass();
    public static final BlockSugar SUGAR_BLOCK = new BlockSugar();
    public static final BlockSugarSand SUGAR_SAND = new BlockSugarSand();
    public static final BlockWaferStick WAFER_STICK = new BlockWaferStick();

    // Blocks with subtypes
    public static final BlockCandyCane CANDY_CANE = new BlockCandyCane();
    public static final BlockCandyGrass CANDY_GRASS = new BlockCandyGrass();
    public static final BlockCandySoil CANDY_SOIL = new BlockCandySoil();
    public static final BlockChocolateBar CHOCOLATE_BAR = new BlockChocolateBar();
    public static final BlockChocolateLeaves CHOCOLATE_LEAVES = new BlockChocolateLeaves();
    public static final BlockChocolateMushroom CHOCOLATE_MUSHROOM = new BlockChocolateMushroom();
    public static final BlockChocolate CHOCOLATE_BLOCK = new BlockChocolate(Material.ROCK, "chocolate_block");
    public static final BlockChocolate CHOCOLATE_BRICK = new BlockChocolate(Material.ROCK, "chocolate_brick_block");
    public static final BlockCookieOre COOKIE_ORE = new BlockCookieOre();
    public static final BlockGummy GUMMY_BLOCK = new BlockGummy();
    public static final BlockGummySolid HARDENED_GUMMY_BLOCK = new BlockGummySolid();
    public static final BlockGummyWorm GUMMY_WORM_BLOCK = new BlockGummyWorm();

    // ItemBlocks (for blocks with subtypes)
    public static final ItemBlockCandyCane CANDY_CANE_IB = new ItemBlockCandyCane();
    public static final ItemBlockCandyGrass CANDY_GRASS_IB = new ItemBlockCandyGrass();
    public static final ItemBlockCandySoil CANDY_SOIL_IB = new ItemBlockCandySoil();
    public static final ItemBlockChocolateLeaves CHOCOLATE_LEAVES_IB = new ItemBlockChocolateLeaves();
    public static final ItemBlockChocolateMushroom CHOCOLATE_MUSHROOM_IB = new ItemBlockChocolateMushroom();
    public static final ItemBlockChocolate CHOCOLATE_BLOCK_IB = new ItemBlockChocolate(CHOCOLATE_BLOCK);
    public static final ItemBlockChocolate CHOCOLATE_BRICK_IB = new ItemBlockChocolate(CHOCOLATE_BRICK);
    public static final ItemBlockCookieOre COOKIE_ORE_IB = new ItemBlockCookieOre();
    public static final ItemBlockGummy GUMMY_BLOCK_IB = new ItemBlockGummy(GUMMY_BLOCK);
    public static final ItemBlockGummy HARDENED_GUMMY_IB = new ItemBlockGummy(HARDENED_GUMMY_BLOCK);
    public static final ItemBlockGummy GUMMY_WORM_IB = new ItemBlockGummy(GUMMY_WORM_BLOCK);

    // fluids
    // using Material.WATER has some shitty side effects, but its the best I can do right now
    public static final BlockFluidClassic LIQUID_CHOCOLATE_BLOCK = (BlockFluidClassic) new BlockFluidClassic(LIQUID_CHOCOLATE, Material.WATER).setRegistryName("liquid_chocolate_block").setUnlocalizedName("liquid_chocolate_block");

    /**
     * Register all blocks
     */
    static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                CANDY_CANE,
                CANDY_GRASS,
                CANDY_SOIL,
                COOKIE_ORE,
                COTTON_CANDY_LEAVES,
                COTTON_CANDY_PLANT,
                COTTON_CANDY_SAPLING,
                COTTON_CANDY_GRASS,
                CHOCOLATE_BAR,
                CHOCOLATE_LEAVES,
                CHOCOLATE_MUSHROOM,
                CHOCOLATE_SAPLING,
                CHOCOLATE_BLOCK,
                CHOCOLATE_BRICK,
                SUGAR_BLOCK,
                SUGAR_SAND,
                WAFER_STICK,
                GUMMY_BLOCK,
                HARDENED_GUMMY_BLOCK,
                GUMMY_WORM_BLOCK,
                LIQUID_CHOCOLATE_BLOCK
        );
    }

    /**
     * Register all ItemBlocks
     */
    static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                COTTON_CANDY_LEAVES.createItemBlock(),
                COTTON_CANDY_PLANT.createItemBlock(),
                COTTON_CANDY_SAPLING.createItemBlock(),
                COTTON_CANDY_GRASS.createItemBlock(),
                CHOCOLATE_SAPLING.createItemBlock(),
                SUGAR_BLOCK.createItemBlock(),
                WAFER_STICK.createItemBlock(),
                SUGAR_SAND.createItemBlock(),
                CANDY_GRASS_IB,
                CANDY_CANE_IB,
                CANDY_SOIL_IB,
                CHOCOLATE_LEAVES_IB,
                CHOCOLATE_MUSHROOM_IB,
                CHOCOLATE_BLOCK_IB,
                CHOCOLATE_BRICK_IB,
                COOKIE_ORE_IB,
                GUMMY_BLOCK_IB,
                HARDENED_GUMMY_IB,
                GUMMY_WORM_IB
        );
    }

    /**
     * Register an itemblock model without subtypes
     */
    private static void registerModel(Block block, String location) {
        CandyMod.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, location);
    }

    /**
     * Register all ItemBlock models
     */
    static void registerModels() {
        registerModel(COTTON_CANDY_PLANT, "block/sugar/cotton_candy_plant_block");
        registerModel(COTTON_CANDY_LEAVES, "block/sugar/cotton_candy_leaves_block");
        registerModel(COTTON_CANDY_SAPLING, "block/sugar/cotton_candy_sapling_block");
        registerModel(COTTON_CANDY_GRASS, "block/sugar/cotton_candy_grass_block");
        registerModel(CHOCOLATE_SAPLING, "block/chocolate/chocolate_sapling_block");
        registerModel(SUGAR_BLOCK, "block/sugar/sugar_block");
        registerModel(SUGAR_SAND, "block/sugar/sugar_sand_block");
        registerModel(WAFER_STICK, "block/wafer_stick_block");

        ModelResourceLocation itemMRL;

        itemMRL = new ModelResourceLocation("candymod:block/ore/cookie_ore_block");
        ModelLoader.setCustomModelResourceLocation(COOKIE_ORE_IB, 0, itemMRL);
        itemMRL = new ModelResourceLocation("candymod:block/ore/cookie_ore_block_sugar");
        ModelLoader.setCustomModelResourceLocation(COOKIE_ORE_IB, 1, itemMRL);

        // Candy Cane based blocks
        for (EnumCandyCane enumcandycane : EnumCandyCane.values()) {
            itemMRL = new ModelResourceLocation("candymod:block/candy_cane/candy_cane_block_" + enumcandycane.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CANDY_CANE_IB, enumcandycane.getMetadata(), itemMRL);
        }

        // Chocolate based blocks
        for (EnumChocolate enumchocolate : EnumChocolate.values()) {
            itemMRL = new ModelResourceLocation("candymod:block/chocolate/candy_soil_block_" + enumchocolate.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CANDY_SOIL_IB, enumchocolate.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/chocolate/chocolate_leaves_block_" + enumchocolate.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CHOCOLATE_LEAVES_IB, enumchocolate.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/chocolate/chocolate_mushroom_block_" + enumchocolate.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CHOCOLATE_MUSHROOM_IB, enumchocolate.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/chocolate/chocolate_block_" + enumchocolate.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CHOCOLATE_BLOCK_IB, enumchocolate.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/chocolate/chocolate_brick_block_" + enumchocolate.getName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(CHOCOLATE_BRICK_IB, enumchocolate.getMetadata(), itemMRL);

            // TODO implement dark candy grass
            if (enumchocolate != EnumChocolate.DARK) {
                itemMRL = new ModelResourceLocation("candymod:block/chocolate/candy_grass_block_" + enumchocolate.getName(), "inventory");
                ModelLoader.setCustomModelResourceLocation(CANDY_GRASS_IB, enumchocolate.getMetadata(), itemMRL);
            }
        }

        // Gummy based blocks
        for (EnumGummy enumgummy : EnumGummy.values()) {
            itemMRL = new ModelResourceLocation("candymod:block/gummy/gummy_block", "inventory");
            ModelLoader.setCustomModelResourceLocation(GUMMY_BLOCK_IB, enumgummy.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/gummy/hardened_gummy_block", "inventory");
            ModelLoader.setCustomModelResourceLocation(HARDENED_GUMMY_IB, enumgummy.getMetadata(), itemMRL);

            itemMRL = new ModelResourceLocation("candymod:block/gummy/gummy_worm_block", "inventory");
            ModelLoader.setCustomModelResourceLocation(GUMMY_WORM_IB, enumgummy.getMetadata(), itemMRL);
        }
    }
}

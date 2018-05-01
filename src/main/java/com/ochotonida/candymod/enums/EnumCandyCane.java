package com.ochotonida.candymod.enums;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum EnumCandyCane implements IStringSerializable {

    WHITE(0, "white"),
    RED(1, "red"),
    GREEN(2, "green"),
    WHITE_RED(3, "white_red"),
    WHITE_GREEN(4, "white_green"),
    RED_GREEN(5, "red_green");

    public static final EnumCandyCane[] META_LOOKUP = new EnumCandyCane[values().length];

    static {
        for (EnumCandyCane enumcandycane : values()) {
            META_LOOKUP[enumcandycane.getMetadata()] = enumcandycane;
        }
    }

    private final int meta;
    private final String name;

    EnumCandyCane(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public static EnumCandyCane byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMetadata() {
        return this.meta;
    }

    @Nonnull
    @Override
    public String getName() {
        return this.name;
    }
}

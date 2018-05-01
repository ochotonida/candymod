package com.ochotonida.candymod.enums;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum EnumChocolate implements IStringSerializable {

    MILK(0, "milk"),
    WHITE(1, "white"),
    DARK(2, "dark");

    public static final EnumChocolate[] META_LOOKUP = new EnumChocolate[values().length];

    static {
        for (EnumChocolate enumchocolate : values()) {
            META_LOOKUP[enumchocolate.getMetadata()] = enumchocolate;
        }
    }

    private final int meta;
    private final String name;

    EnumChocolate(int meta, String name) {
        this.meta = meta;
        this.name = name;
    }

    public static EnumChocolate byMetadata(int meta) {
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

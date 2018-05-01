package com.ochotonida.candymod.enums;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

public enum EnumAxis implements IStringSerializable {
    X(0, "x"),
    Y(1, "y"),
    Z(2, "z");

    public static final EnumAxis[] META_LOOKUP = new EnumAxis[values().length];

    static {
        for (EnumAxis enumaxis : EnumAxis.values()) {
            META_LOOKUP[enumaxis.getMetadata()] = enumaxis;
        }
    }

    private final String name;
    private final int meta;

    EnumAxis(int meta, String name) {
        this.name = name;
        this.meta = meta;
    }

    public static EnumAxis byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public static EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
        switch (axis) {
            case X:
                return X;
            case Y:
                return Y;
            case Z:
                return Z;
            default:
                return X;
        }
    }

    public int getMetadata() {
        return this.meta;
    }

    @Override
    @Nonnull
    public String getName() {
        return this.name;
    }
}

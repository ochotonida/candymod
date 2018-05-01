package com.ochotonida.candymod.block;

import net.minecraft.block.SoundType;
import net.minecraft.init.SoundEvents;

public class ModSoundTypes {

    public static final SoundType COTTON_CANDY = new SoundType(1.0F, 0F, SoundEvents.BLOCK_CLOTH_BREAK, SoundEvents.BLOCK_CLOTH_STEP, SoundEvents.BLOCK_CLOTH_PLACE, SoundEvents.BLOCK_CLOTH_HIT, SoundEvents.BLOCK_CLOTH_FALL);
    public static final SoundType CANDY_GRASS = new SoundType(1.0F, 0F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_CLOTH_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_CLOTH_FALL);
    public static final SoundType CANDY_DIRT = new SoundType(1.0F, 0F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_GRAVEL_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_FALL);
    public static final SoundType GUMMY = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_SLIME_STEP, SoundEvents.ENTITY_SLIME_SQUISH, SoundEvents.BLOCK_SLIME_PLACE, SoundEvents.BLOCK_SLIME_HIT, SoundEvents.BLOCK_SLIME_FALL);
}

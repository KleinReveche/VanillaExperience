package com.vanilla.experience.forge.zerotickunpatch.mixin;

import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

// this abstracts Kelp, TwistingVines and WeepingVines
@Mixin(AbstractPlantBlock.class)
public abstract class ZeroTickAbstractPlantBlock extends AbstractBlock {
    public ZeroTickAbstractPlantBlock(Properties propertiesIn) {
        super(propertiesIn);
    }

    @Inject(at = @At("TAIL"), method = "tick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/server/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V")
    public void tick(final BlockState state, final ServerWorld world, final BlockPos pos, final Random random, CallbackInfo info) {
        if (!state.isValidPosition(world, pos)) {
            world.destroyBlock(pos, true);
            return;
        }

        if(!world.isAirBlock(pos.down())) {
            this.randomTick(state, world, pos, random);
        }
    }
}
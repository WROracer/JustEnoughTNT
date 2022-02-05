package de.wroracer.justenoughtnt.util;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class FuseExplosion extends Explosion {

    public FuseExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
    }

    public FuseExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
            int perTick) {
        super(world, pos, source, radius, dropChance, randomness, perTick);
    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = this.getLevel().getBlockState(pos).getBlock();

        return block instanceof BaseTNTBlock;
    }

}

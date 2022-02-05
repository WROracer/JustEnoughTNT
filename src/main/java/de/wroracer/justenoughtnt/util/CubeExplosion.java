package de.wroracer.justenoughtnt.util;

import java.util.ArrayList;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class CubeExplosion extends Explosion {

    public CubeExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
    }

    public CubeExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
            int perTick) {
        super(world, pos, source, radius, dropChance, randomness, perTick);
    }

    @Override
    public ArrayList<BlockPos> getBlocks() {
        float radius = this.getRadius();
        BlockPos pos = this.getPos();
        JustEnoughTNT.LOGGER.info("Getting blocks in radius: " + radius);

        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = -(int) radius; x <= radius; x++) {
            for (int y = -(int) radius; y <= radius; y++) {
                for (int z = -(int) radius; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    // double distance = Math.sqrt(x * x + y * y + z * z);
                    // if (distance <= radius + randomness * (Math.random() - 0.5)) {
                    if (shouldDestroy(blockPos))
                        blocks.add(blockPos);
                    // }
                }
            }
        }
        JustEnoughTNT.LOGGER.info("Blocks: " + blocks.size());
        return blocks;
    }

}

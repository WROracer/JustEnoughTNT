package de.wroracer.justenoughtnt.util;

import java.util.ArrayList;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class FlatExplosion extends Explosion {

    public FlatExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
    }

    public FlatExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
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
        boolean isDestructable = block == Blocks.BEDROCK || block == Blocks.AIR;
        return !isDestructable && this.getPos().getY() <= pos.getY();
    }

    @Override
    public ArrayList<BlockPos> getBlocks() {
        float radius = this.getRadius();
        double randomness = this.getRandomness();
        BlockPos pos = this.getPos();
        JustEnoughTNT.LOGGER.info("Getting blocks in radius: " + radius);

        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = -(int) radius; x <= radius; x++) {
            for (int y = -(int) radius; y <= radius / 3; y++) {
                for (int z = -(int) radius; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= radius + randomness * (Math.random() - 0.5)) {
                        if (shouldDestroy(blockPos))
                            blocks.add(blockPos);
                    }
                }
            }
        }
        JustEnoughTNT.LOGGER.info("Blocks: " + blocks.size());
        return blocks;
    }

}

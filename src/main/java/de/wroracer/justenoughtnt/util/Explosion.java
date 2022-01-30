package de.wroracer.justenoughtnt.util;

import java.util.ArrayList;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class Explosion {

    private Level world;
    private BlockPos pos;
    private Entity source;
    private float radius;
    private double dropChance;
    private double randomness;

    public Explosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        this.world = world;
        this.pos = pos;
        this.source = source;
        this.radius = radius;
        this.dropChance = dropChance;
        this.randomness = randomness;
    }

    public void explode() {
        // get all blocks within the radius from pos
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = -(int) radius; x <= radius; x++) {
            for (int y = -(int) radius; y <= radius; y++) {
                for (int z = -(int) radius; z <= radius; z++) {
                    BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= radius + randomness * (Math.random() - 0.5)) {
                        blocks.add(blockPos);
                    }
                }
            }
        }

        for (BlockPos blockPos : blocks) {
            boolean drop = Math.random() <= dropChance;
            world.destroyBlock(blockPos, drop);
        }

    }

    public Level getLevel() {
        return world;
    }

    public void setLevel(Level world) {
        this.world = world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public Entity getSource() {
        return source;
    }

    public void setSource(Entity source) {
        this.source = source;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public double getDropChance() {
        return dropChance;
    }

    public void setDropChance(double dropChance) {
        this.dropChance = dropChance;
    }
}

package de.wroracer.justenoughtnt.util;

import java.util.ArrayList;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class Explosion {

    private Level world;
    private BlockPos pos;
    private Entity source;
    private float radius;
    private double dropChance;
    private double randomness;

    private int inTicks;
    private int currentTick;
    private ArrayList<ArrayList<BlockPos>> chunkBlocks;

    public Explosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        this.world = world;
        this.pos = pos;
        this.source = source;
        this.radius = radius;
        this.dropChance = dropChance;
        this.randomness = randomness;
        this.currentTick = 0;
        this.inTicks = 0;
        chunkBlocks = new ArrayList<ArrayList<BlockPos>>();

    }

    public Explosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
            int inTicks) {
        this.world = world;
        this.pos = pos;
        this.source = source;
        this.radius = radius;
        this.dropChance = dropChance;
        this.randomness = randomness;
        this.inTicks = inTicks;
        this.currentTick = 0;
        chunkBlocks = new ArrayList<ArrayList<BlockPos>>();
    }

    public void explode() {
        // get all blocks within the radius from pos

        ArrayList<BlockPos> blocks = getBlocks();
        if (inTicks > 0 && currentTick == 0) {
            // split blocks into inTicks chunks
            int chunkSize = (int) Math.floor(blocks.size() / inTicks);
            if (chunkSize == 0) {
                chunkSize = 1;
            }
            for (int i = 0; i < inTicks; i++) {
                chunkBlocks.add(new ArrayList<BlockPos>());
                for (int j = 0; j < chunkSize || (i + 1 == inTicks && !(blocks.size() - 1 < i * chunkSize + j)); j++) {
                    int index = i * chunkSize + j;
                    chunkBlocks.get(i).add(blocks.get(index));
                }
            }
            damageEntities();
        }
        if (inTicks == 0 && currentTick == 0) {
            modifyWorld(blocks);
            damageEntities();
        }
    }

    private ArrayList<BlockPos> getBlocks() {
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = -(int) radius; x <= radius; x++) {
            for (int y = -(int) radius; y <= radius; y++) {
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
        return blocks;
    }

    public boolean tick() {
        if (inTicks <= 0) {
            throw new IllegalStateException("Tried to tick explosion when the explosion is set to explode at once");
        }

        currentTick++;
        ArrayList<BlockPos> blocks = chunkBlocks.get(currentTick - 1);

        modifyWorld(blocks);
        // chunkBlocks.remove(blocks);

        return currentTick >= chunkBlocks.size();
    }

    private void damageEntities() {

        // damage entities
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity entity : world.getEntities(source, new AABB(pos).inflate(radius, radius, radius))) {
            if (entity instanceof LivingEntity) {
                entities.add(entity);
            }
        }
        for (Entity entity : entities) {

            double distance = getEntityDistance(entity);
            if (distance <= radius) {
                double damage = getEntityDamage(distance);
                // JustEnoughTNT.LOGGER.debug(
                //         "Damageing: " + entity.getEncodeId() + " with damage: " + damage
                //                 + "; distance: " + distance);
                LivingEntity entity2 = (LivingEntity) entity;
                entity2.hurt(DamageSource.explosion((LivingEntity) source), (float) damage);
            }
        }
    }

    private void modifyWorld(ArrayList<BlockPos> blocks) {
        // remove blocks
        for (BlockPos blockPos : blocks) {
            destroyBlock(blockPos);
        }

    }

    public double getEntityDistance(Entity entity) {
        int x = entity.getBlockX();
        int y = entity.getBlockY();
        int z = entity.getBlockZ();
        double distance = Math
                .sqrt(Math.pow(x - pos.getX(), 2) + Math.pow(y - pos.getY(), 2) + Math.pow(z - pos.getZ(), 2));
        return distance;
    }

    public double getEntityDamage(double distance) {
        // the larger the distance, the smaller the damage. minimum damage is 1 at radius
        return Math.max(1, (radius - distance));

    }

    public boolean shouldDestroy(BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        boolean isDestructable = block == Blocks.BEDROCK || block == Blocks.AIR;
        return !isDestructable;
    }

    public void destroyBlock(BlockPos pos) {

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock) {
            // it is one of our tnt blocks
            BaseTNTBlock tntBlock = (BaseTNTBlock) block;
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) source);
        } else {
            boolean drop = Math.random() <= dropChance;
            world.destroyBlock(pos, drop);
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

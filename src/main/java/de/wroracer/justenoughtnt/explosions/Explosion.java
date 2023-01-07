package de.wroracer.justenoughtnt.explosions;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.util.DistanceStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class Explosion {

    private final ArrayList<ArrayList<BlockPos>> chunkBlocks;
    private Level world;
    private BlockPos pos;
    private Entity source;
    private float radius;
    private double dropChance;
    private double randomness;
    private int perTick;
    private int currentTick;

    public Explosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        this.world = world;
        this.pos = pos;
        this.source = source;
        this.radius = radius;
        this.dropChance = dropChance;
        this.randomness = randomness;
        this.currentTick = 0;
        this.perTick = 0;
        chunkBlocks = new ArrayList<ArrayList<BlockPos>>();

    }

    public Explosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
            int perTick) {
        this.world = world;
        this.pos = pos;
        this.source = source;
        this.radius = radius;
        this.dropChance = dropChance;
        this.randomness = randomness;
        this.perTick = perTick;
        this.currentTick = 0;
        chunkBlocks = new ArrayList<ArrayList<BlockPos>>();
    }

    public void explode() {
        // get all blocks within the radius from pos

        if (perTick > 0 && currentTick == 0) {
            ArrayList<BlockPos> blocks = getBlocks();
            // split blocks into perTick chunks

            // todo: change to split on blocks per chunk and not on ticks to explode

            for (int i = 0; i < blocks.size(); i += perTick) {
                ArrayList<BlockPos> chunk = new ArrayList<BlockPos>();
                for (int j = 0; j < perTick; j++) {
                    if (i + j < blocks.size()) {
                        chunk.add(blocks.get(i + j));
                    }
                }
                chunkBlocks.add(chunk);
            }

            JustEnoughTNT.LOGGER.info("spread in " + chunkBlocks.size() + " chunks of " + perTick + " blocks");

            modifyEntities();
        }
        if (perTick == 0 && currentTick == 0) {
            ArrayList<BlockPos> blocks = getBlocks();
            modifyEntities();
            modifyWorld(blocks);
        }
    }

    public ArrayList<BlockPos> getBlocks() {
        JustEnoughTNT.LOGGER.info("Getting blocks in radius: " + radius);

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
        blocks = roundOrder(blocks);
        JustEnoughTNT.LOGGER.info("Blocks: " + blocks.size());
        return blocks;
    }

    public void modifyEntities() {
        ArrayList<Entity> entities = damageEntities();
        for (Entity entity : entities) {
            Vec3 newVelocity = getEntityVelocity(entity);
            Vec3 oldVelocity = entity.getDeltaMovement();
            entity.setDeltaMovement(oldVelocity.add(newVelocity));
        }
    }

    private void modifyWorld(ArrayList<BlockPos> blocks) {
        // remove blocks
        for (BlockPos blockPos : blocks) {
            destroyBlock(blockPos);
        }

    }

    public boolean shouldDestroy(BlockPos pos) {
        Block block = world.getBlockState(pos).getBlock();
        boolean isDestructable = block == Blocks.BEDROCK || block == Blocks.AIR;

        return !isDestructable || liquidCheck(world.getBlockState(pos));
    }

    public ArrayList<BlockPos> roundOrder(ArrayList<BlockPos> blocks) {
        ArrayList<DistanceStorage> distances = new ArrayList<DistanceStorage>();
        ArrayList<BlockPos> rounded = new ArrayList<BlockPos>();

        for (BlockPos blockPos : blocks) {
            double distance = Math.sqrt(Math.pow(blockPos.getX() - pos.getX(), 2)
                    + Math.pow(blockPos.getY() - pos.getY(), 2) + Math.pow(blockPos.getZ() - pos.getZ(), 2));
            distances.add(new DistanceStorage(distance, blockPos));
        }

        distances.sort((DistanceStorage d1, DistanceStorage d2) -> {
            if (d1.getDistance() < d2.getDistance())
                return -1;
            else if (d1.getDistance() > d2.getDistance())
                return 1;
            else
                return 0;
        });

        for (DistanceStorage distance : distances) {
            rounded.add(distance.getPos());
        }

        return rounded;
    }

    public ArrayList<Entity> getEntities() {

        ArrayList<Entity> finalEntities = new ArrayList<Entity>();
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for (Entity entity : world.getEntities(source, new AABB(pos).inflate(radius, radius, radius))) {
            if (entity instanceof LivingEntity) {
                entities.add(entity);
            }
        }

        for (Entity entity : entities) {

            double distance = getEntityDistance(entity);
            if (distance <= radius) {
                finalEntities.add(entity);
            }
        }

        return finalEntities;

    }

    private ArrayList<Entity> damageEntities() {
        // damage entities
        ArrayList<Entity> entities = getEntities();

        for (Entity entity : entities) {

            double distance = getEntityDistance(entity);
            if (distance <= radius) {
                double damage = getEntityDamage(distance);

                LivingEntity entity2 = (LivingEntity) entity;
                entity2.hurt(DamageSource.explosion((LivingEntity) source), (float) damage);
            }
        }
        return entities;
    }

    public Vec3 getEntityVelocity(Entity entity) {
        // move away from the direction of the explosion
        double distanceFromExplosion = getEntityDistance(entity);
        double x = entity.getBlockX() - pos.getX();
        double y = entity.getBlockY() - pos.getY();
        double z = entity.getBlockZ() - pos.getZ();
        double distance = Math.sqrt(x * x + y * y + z * z);
        x /= distance;
        y /= distance;
        z /= distance;
        x *= (distanceFromExplosion / 2);
        y *= (distanceFromExplosion / 2);
        z *= (distanceFromExplosion / 2);
        return new Vec3(x, y, z);
    }

    public void destroyBlock(BlockPos pos) {

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock tntBlock) {
            // it is one of our tnt blocks
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) source);
        } else {
            if (liquidCheck(world.getBlockState(pos))) {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            } else {

                boolean drop = Math.random() <= dropChance;
                world.destroyBlock(pos, drop);
            }
        }

    }

    private boolean liquidCheck(BlockState blockState) {
        Material material = blockState.getMaterial();
        boolean isDrainable = material.isLiquid() || material == Material.WATER_PLANT
                || material == Material.REPLACEABLE_WATER_PLANT;
        return isDrainable;
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

    public boolean tick() {
        if (perTick <= 0) {
            throw new IllegalStateException("Tried to tick explosion when the explosion is set to explode at once");
        }

        if (currentTick == chunkBlocks.size()) {
            this.explosionFinished();
            return true;
        }

        currentTick++;
        ArrayList<BlockPos> blocks = chunkBlocks.get(currentTick - 1);

        modifyWorld(blocks);
        // chunkBlocks.remove(blocks);

        if (currentTick >= chunkBlocks.size()) {
            this.explosionFinished();
            return true;
        }
        return false;
    }

    public void explosionFinished() {
        JustEnoughTNT.LOGGER.info("Explosion finished");
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

    public double getRandomness() {
        return randomness;
    }

    public void setRandomness(double randomness) {
        this.randomness = randomness;
    }

    public int getPerTick() {
        return perTick;
    }

    public void setPerTick(int perTick) {
        this.perTick = perTick;
    }

}

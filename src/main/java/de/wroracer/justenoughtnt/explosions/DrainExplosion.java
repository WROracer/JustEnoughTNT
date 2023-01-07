package de.wroracer.justenoughtnt.explosions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;

public class DrainExplosion extends Explosion {

    private int max;

    // public DrainExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance,
    //         double randomness, int inTicks) {
    //     super(world, pos, source, radius, dropChance, randomness, inTicks);
    // }

    public DrainExplosion(Level world, BlockPos pos, Entity source, float radius, int max, double dropChance,
            double randomness, int inTicks) {
        super(world, pos, source, radius, dropChance, randomness, inTicks);
        this.max = max;
    }

    public DrainExplosion(Level world, BlockPos pos, Entity source, float radius, int max, double dropChance,
            double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
        this.max = max;
    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = this.getLevel().getBlockState(pos).getBlock();
        double distance = distance(this.getPos(), pos);

        boolean isDestructable = block == Blocks.BEDROCK || block == Blocks.AIR;

        // liquids
        Material material = this.getLevel().getBlockState(pos).getMaterial();
        boolean isDrainable = material.isLiquid() || material == Material.WATER_PLANT
                || material == Material.REPLACEABLE_WATER_PLANT; // set to true if block is a liquid

        // JustEnoughTNT.LOGGER.debug("this.getLevel().getFluidState(pos) = " + this.getLevel().getFluidState(pos));

        return ((!isDestructable && isDrainable) || block instanceof BaseTNTBlock) && distance <= this.getRadius();
    }

    @Override
    public void destroyBlock(BlockPos pos) {
        Level world = this.getLevel();

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock tntBlock) {
            // it is one of our tnt blocks
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) this.getSource());
        } else {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }

    }

    @Override
    public ArrayList<BlockPos> getBlocks() {
        JustEnoughTNT.LOGGER.info("Getting blocks with max: " + max + " blocks");

        ArrayList<BlockPos> blocks = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();

        // get all liquid blocks connected to the source
        ArrayList<BlockPos> neighbors = new ArrayList<>();
        neighbors.addAll(getNeighbors(this.getPos()));

        while (blocks.size() < max && !neighbors.isEmpty()) {
            ArrayList<BlockPos> newNeighbors = new ArrayList<>(neighbors.size());
            for (int i = 0; i < neighbors.size(); i++) {
                BlockPos pos = neighbors.get(i);
                if (visited.contains(pos)) {
                    continue;
                }
                visited.add(pos);
                if (shouldDestroy(pos)) {
                    blocks.add(pos);
                    for (BlockPos newPos : getNeighbors(pos)) {
                        if (shouldDestroy(newPos) && !visited.contains(newPos)) {
                            newNeighbors.add(newPos);
                        }
                    }
                }
            }
            neighbors = newNeighbors;
        }

        blocks = roundOrder(blocks);
        JustEnoughTNT.LOGGER.info("Blocks: " + blocks.size());
        return blocks;
    }

    private ArrayList<BlockPos> getNeighbors(BlockPos pos) {
        ArrayList<BlockPos> neighbors = new ArrayList<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos newPos = pos.offset(x, y, z);
                    neighbors.add(newPos);
                }
            }
        }

        return neighbors;
    }

    @Override
    public void explosionFinished() {
        JustEnoughTNT.LOGGER.info("Drain finished");
    }
}

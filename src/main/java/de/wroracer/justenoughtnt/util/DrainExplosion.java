package de.wroracer.justenoughtnt.util;

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

    public DrainExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
            int inTicks) {
        super(world, pos, source, radius, dropChance, randomness, inTicks);
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = this.getLevel().getBlockState(pos).getBlock();
        boolean isDestructable = block == Blocks.BEDROCK || block == Blocks.AIR;

        // liquids
        Material material = this.getLevel().getBlockState(pos).getMaterial();
        boolean isDrainable = material.isLiquid() || material == Material.WATER_PLANT
                || material == Material.REPLACEABLE_WATER_PLANT; // set to true if block is a liquid

        // JustEnoughTNT.LOGGER.debug("this.getLevel().getFluidState(pos) = " + this.getLevel().getFluidState(pos));

        return (!isDestructable && isDrainable) || block instanceof BaseTNTBlock;
    }

    @Override
    public void destroyBlock(BlockPos pos) {
        Level world = this.getLevel();

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock) {
            // it is one of our tnt blocks
            BaseTNTBlock tntBlock = (BaseTNTBlock) block;
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) this.getSource());
        } else {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }

    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public void explosionFinished() {
        JustEnoughTNT.LOGGER.info("Drain finished");
    }
}

package de.wroracer.justenoughtnt.util;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class OreMinerExplosion extends Explosion {

    public OreMinerExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance,
            double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
    }

    public OreMinerExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance,
            double randomness, int perTick) {
        super(world, pos, source, radius, dropChance, randomness, perTick);
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = this.getLevel().getBlockState(pos).getBlock();
        // check if block is an ore
        return Tags.Blocks.ORES.contains(block) || block instanceof BaseTNTBlock;
    }

    @Override
    public void destroyBlock(BlockPos pos) {
        Level world = this.getLevel();

        if (this.getSource() == null) {
            return;
        }
        double x = this.getSource().getX();
        double y = this.getSource().getY();
        double z = this.getSource().getZ();
        BlockPos playerPos = new BlockPos(x, y + 2, z);

        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock) {
            // it is one of our tnt blocks
            BaseTNTBlock tntBlock = (BaseTNTBlock) block;
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) this.getSource());
        } else {
            // set the block to air
            // and put it above the player and mine it
            BlockState state = world.getBlockState(pos);
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            // world.destroyBlock(pos, false);
            world.setBlock(playerPos, state, 3);
            world.destroyBlock(playerPos, true, this.getSource());

        }

    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

}

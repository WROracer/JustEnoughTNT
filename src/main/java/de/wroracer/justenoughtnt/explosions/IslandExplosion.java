package de.wroracer.justenoughtnt.explosions;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class IslandExplosion extends Explosion {

    private final int yOffset;

    public IslandExplosion(Level world, BlockPos pos, Entity source, float radius,
                           double randomness, int yOffset) {
        super(world, pos, source, radius, 0, randomness);
        this.yOffset = yOffset;
    }

    public IslandExplosion(Level world, BlockPos pos, Entity source, float radius, double randomness,
                           int perTick, int yOffset) {
        super(world, pos, source, radius, 0, randomness, perTick);
        this.yOffset = yOffset;
    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public void destroyBlock(BlockPos pos) {

        Level world = getLevel();
        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock tntBlock) {
            // it is one of our tnt blocks
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) getSource());
        } else {
            // world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            moveBlock(pos);

        }

    }

    private void moveBlock(BlockPos pos) {
        Level world = getLevel();
        BlockState blockState = world.getBlockState(pos);

        // new position by yOffset 
        BlockPos newPos = pos;

        newPos = newPos.above(yOffset);

        world.setBlock(newPos, blockState, 3);
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

    }

}

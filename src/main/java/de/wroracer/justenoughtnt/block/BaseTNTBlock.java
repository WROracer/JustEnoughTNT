package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BaseTNTBlock extends TntBlock {

    public BaseTNTBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player igniter,
            InteractionHand hand, BlockHitResult hit) {
        // System.out.println("ignite block");
        ignite(world, pos, igniter, -1);
        return InteractionResult.PASS;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state2, boolean p_57470_) {
        if (!state2.is(state.getBlock())) {
            if (world.hasNeighborSignal(pos)) {
                ignite(world, pos, null, -1);
            }

        }
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        if (!world.isClientSide) {
            // PrimedTnt primedtnt = new PrimedTnt(
            //         world, (double) pos.getX() + 0.5D, (double) pos.getY(),
            //         (double) pos.getZ() + 0.5D, explosion.getSourceMob());

            ignite(world, pos, (Player) explosion.getSourceMob(), world.random.nextInt(80 / 4) + 80 / 8);
            // world.addFreshEntity(primedtnt);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block,
            BlockPos pos2, boolean p_57462_) {
        if (world.hasNeighborSignal(pos)) {
            ignite(world, pos, null, -1);
        }

    }

    @Override
    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide) {
            BlockPos blockpos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(world, blockpos)) {
                ignite(world, blockpos, entity instanceof LivingEntity ? (Player) entity : null, -1);
            }
        }

    }

    public void ignite(Level world, BlockPos pos, Player igniter, int fuse) {
        // System.out.println("ignite block asdasd");
        world.removeBlock(pos, false);
        BaseTNT tnt = new BaseTNT(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, this, igniter);
        if (fuse != -1) {
            tnt.setFuse((short) fuse);
        }

        world.addFreshEntity(tnt);
    }

    public void onExplode(BaseTNT tnt) {
        // System.out.println("Exploded");
        int posX = tnt.getBlockX();
        int posY = tnt.getBlockY();
        int posZ = tnt.getBlockZ();

        tnt.getLevel().explode(tnt, (double) posX, (double) posY, (double) posZ, 4.0F,
                Explosion.BlockInteraction.BREAK);
        tnt.discard();

    }

}

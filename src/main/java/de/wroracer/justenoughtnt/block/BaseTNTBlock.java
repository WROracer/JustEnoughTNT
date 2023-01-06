package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.TNTEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class BaseTNTBlock<T extends TNTEntity> extends TntBlock {

    private final RegistryObject<EntityType<T>> entityType;
    private int fuse = -1;
    private Player placer = null;

    public BaseTNTBlock(Properties properties, int fuse, RegistryObject<EntityType<T>> entityType) {
        super(properties);
        this.fuse = fuse;
        this.entityType = entityType;
    }

    public BaseTNTBlock(Properties properties, RegistryObject<EntityType<T>> entityType) {
        super(properties);
        this.entityType = entityType;
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state2, boolean p_57470_) {
        if (!state2.is(state.getBlock())) {
            if (world.hasNeighborSignal(pos)) {
                ignite(world, pos, placer, fuse);
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block,
                                BlockPos pos2, boolean p_57462_) {
        if (world.hasNeighborSignal(pos)) {
            ignite(world, pos, placer, fuse);
        }

    }

    @Override
    public void wasExploded(Level p_57441_, BlockPos p_57442_, Explosion p_57443_) {
        if (!p_57441_.isClientSide) {
            ignite(p_57441_, p_57442_, placer, fuse);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player igniter,
                                 InteractionHand hand, BlockHitResult hit) {
        // System.out.println("ignite block");

        ItemStack stack = igniter.getItemInHand(hand);

        if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {

            if (!igniter.isCreative()) {
                if (stack.is(Items.FLINT_AND_STEEL)) {
                    stack.hurtAndBreak(1, igniter, (player) -> {
                        player.broadcastBreakEvent(hand);
                    });
                }
                if (stack.is(Items.FIRE_CHARGE)) {
                    stack.shrink(1);
                }
            }
            ignite(world, pos, igniter, fuse);
            igniter.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            return InteractionResult.sidedSuccess(world.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }

    }

    @Override
    public void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile) {
        if (!world.isClientSide) {
            BlockPos blockpos = hit.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(world, blockpos)) {
                ignite(world, blockpos, entity instanceof LivingEntity ? (Player) entity : null, fuse);
            }
        }

    }

    public void ignite(Level world, BlockPos pos, Player igniter, int fuse) {
        // System.out.println("ignite block asdasd");
        world.playSound(null, pos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.removeBlock(pos, false);

        T tnt = entityType.get().create(world);

        tnt.setPos(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
        tnt.setOwner(igniter);
        if (fuse != -1) {
            tnt.setFuse((short) fuse);
        }
        world.addFreshEntity(tnt);
        world.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        onFuse(tnt);
    }

    public void onFuse(TNTEntity tnt) {

    }

    @Override
    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
        if (p_49850_ instanceof Player) {
            placer = (Player) p_49850_;
        }
    }

    public void wasExplodedByJET(Level world, BlockPos pos, LivingEntity cause) {
        Block block = world.getBlockState(pos).getBlock();
        BaseTNTBlock tntBlock;
        if (block instanceof BaseTNTBlock)
            tntBlock = (BaseTNTBlock) block;
        else
            return;
        int fuse = tntBlock.getFuse();
        handleExploded(world, pos, cause, fuse);
    }

    // getter and setter
    public int getFuse() {
        return fuse;
    }

    public void handleExploded(Level world, BlockPos pos, LivingEntity cause, int defaultFuse) {
        int fuse = defaultFuse == -1 ? 80 : defaultFuse;

        if (fuse != 0)
            fuse = world.random.nextInt(fuse / 4) + fuse / 8;
        ignite(world, pos, (Player) cause, fuse);
    }

    public void setFuse(int fuse) {
        this.fuse = fuse;
    }

    public boolean fuseTick(TNTEntity tnt) {
        return true;

    }
}

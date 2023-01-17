package de.wroracer.justenoughtnt.entity;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;

import de.wroracer.justenoughtnt.luckyBlock.DeathFallAction;
import de.wroracer.justenoughtnt.luckyBlock.LuckyBlockAction;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LuckyBlockTNTEntity extends TNTEntity {
    private ArrayList<Class<? extends LuckyBlockAction>> actions;

    public LuckyBlockTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public LuckyBlockTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.LUCKY_BLOCK_TNT.get(), level, x, y, z, livingEntity);
    }

    private void initActions() {
        actions = new ArrayList<>();

        actions.add(DeathFallAction.class);
    }

    private LuckyBlockAction getRandomAction() {
        if (actions == null) {
            initActions();
        }

        int index = (int) (Math.random() * actions.size());
        Class<? extends LuckyBlockAction> actionClass = actions.get(index);

        try {
            return actionClass.getConstructor(Level.class, BlockPos.class, Entity.class).newInstance(this.level,
                    this.blockPosition(), this.getOwner());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void dropAsBlock() {
        BlockPos pos = this.blockPosition();

        // drop as item

        ItemEntity itemEntity = new ItemEntity(getLevel(), pos.getX(), pos.getY(), pos.getZ(),
                new ItemStack(ModBlocks.LUCKY_BLOCK_TNT.get()));
        getLevel().addFreshEntity(itemEntity);
        getLevel().playSound(null, pos, SoundEvents.BEACON_DEACTIVATE, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    public void explode() {
        if (getOwner() == null) {
            dropAsBlock();
            discard();
            return;
        }
        LuckyBlockAction action = getRandomAction();
        if (action != null) {
            boolean success = action.execute();
            if (!success)
                dropAsBlock();
        }
        discard();
    }

}

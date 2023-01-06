package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class RainTNTEntity extends TNTEntity {
    private boolean isExploding = false;

    public RainTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public RainTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.RAIN_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isExploding) return;
        Vec3 motion = getDeltaMovement();

        motion = motion.add(0, 0.05, 0);
        setDeltaMovement(motion);
    }

    @Override
    public void explode() {
        if (isExploding) {
            this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
        } else {
            for (int i = 0; i < 300; i++) {
                RainTNTEntity newTNT = ModEntities.RAIN_TNT.get().create(this.level);
                assert newTNT != null;
                newTNT.setIsExploding();
                Vec3 velocity = newTNT.getDeltaMovement();

                double zMovementOffset = (Math.random() - 0.5) * 1.5;
                double xMovementOffset = (Math.random() - 0.5) * 1.5;

                velocity = velocity.add(xMovementOffset, 0, zMovementOffset);
                newTNT.setPos(this.getX(), this.getY(0.0625D), this.getZ());
                newTNT.setVelocity(velocity.x, velocity.y, velocity.z);
                getLevel().addFreshEntity(newTNT);
            }
        }
        discard();
    }

    private void setIsExploding() {
        isExploding = true;
    }
}

package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.explosions.Explosion;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TNTX20Entity extends TNTEntity {
    public TNTX20Entity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public TNTX20Entity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.TNT_X20.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        Level world = getLevel();

        world.playSound(null, getPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 0.8f);
        Explosion explosion = new Explosion(getLevel(), getPos(), getOwner(), 19f, 0.05D, 2D); // 1 tnt ~ 4 2 tnt ~ 6
        explosion.explode();

        discard();
    }
}

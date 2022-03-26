package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TNTX10Entity extends TNTEntity {
    public TNTX10Entity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public TNTX10Entity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.TNT_X10.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        Level world = getLevel();

        world.playSound(null, getPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 0.8f);

        Explosion explosion = new Explosion(getLevel(), getPos(), getOwner(), 13f, 0.05D, 2D); // 1 tnt ~ 4 2 tnt ~ 6
        explosion.explode();

        discard();
    }
}

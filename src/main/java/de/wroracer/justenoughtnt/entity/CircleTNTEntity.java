package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class CircleTNTEntity extends TNTEntity {
    private final HashMap<BlockPos, Explosion> explosions = new HashMap<>();

    public CircleTNTEntity(Level world, double x, double y, double z, LivingEntity igniter) {
        super(ModEntities.CIRCLE_TNT.get(), world, x, y, z, igniter);
    }

    public CircleTNTEntity(EntityType<? extends TNTEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();
        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new Explosion(getLevel(), pos, getOwner(), 10, 0D, 0, 1000)); //max 1000 blocks per tick
        }

        Explosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

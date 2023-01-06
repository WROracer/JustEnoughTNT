package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.explosions.DrainExplosion;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class DrainTNTEntity extends TNTEntity {
    private final HashMap<BlockPos, DrainExplosion> explosions;

    public DrainTNTEntity(Level world, double x, double y, double z, LivingEntity igniter) {
        super(ModEntities.DRAIN_TNT.get(), world, x, y, z, igniter);
        this.blocksBuilding = true;
        explosions = new HashMap<>();
    }

    public DrainTNTEntity(EntityType<? extends TNTEntity> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
        explosions = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = this.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new DrainExplosion(this.getLevel(), pos, this.getOwner(), 45f, 0.001D, 2D, 3000)); //max 3000 blocks per tick
        }

        DrainExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            this.discard();
        }
    }
}

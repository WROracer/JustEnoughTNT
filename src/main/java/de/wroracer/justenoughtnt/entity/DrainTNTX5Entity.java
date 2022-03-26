package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.DrainExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class DrainTNTX5Entity extends TNTEntity {

    private HashMap<BlockPos, DrainExplosion> explosions;

    public DrainTNTX5Entity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.DRAIN_TNT_5.get(), level, x, y, z, livingEntity);
    }

    public DrainTNTX5Entity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);

        explosions = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();
        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new DrainExplosion(getLevel(), pos, getOwner(), 100, 0.001D, 2D, 3000)); //max 3000 blocks per tick
        }

        DrainExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }

}

package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.FlatExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class FlatTNTEntity extends TNTEntity {
    private HashMap<BlockPos, FlatExplosion> explosions;
    public FlatTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<BlockPos, FlatExplosion>();
    }

    public FlatTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.FLAT_TNT.get(), level, x, y, z, livingEntity);
        explosions = new HashMap<BlockPos, FlatExplosion>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new FlatExplosion(getLevel(), pos, getOwner(), 19, 0.05D, 2D, 1000)); //max 1000 blocks per tick
        }

        FlatExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

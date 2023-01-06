package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.FuseExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class FuseTNTEntity extends TNTEntity {
    private final HashMap<BlockPos, FuseExplosion> explosions;

    public FuseTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<>();
    }

    public FuseTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.FUSE_TNT.get(), level, x, y, z, livingEntity);
        explosions = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new FuseExplosion(getLevel(), pos, getOwner(), 45, 0.05D, 20, 1000)); //max 1000 blocks per tick
        }

        FuseExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

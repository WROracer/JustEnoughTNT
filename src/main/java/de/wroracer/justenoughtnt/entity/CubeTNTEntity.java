package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.CubeExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class CubeTNTEntity extends TNTEntity {
    private final HashMap<BlockPos, CubeExplosion> explosions = new HashMap<>();

    public CubeTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public CubeTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.CUBE_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new CubeExplosion(getLevel(), pos, getOwner(), 5, 0.05D, 0, 1000)); //max 1000 blocks per tick
        }

        CubeExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

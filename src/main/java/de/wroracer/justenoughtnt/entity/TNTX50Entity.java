package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class TNTX50Entity extends TNTEntity {
    private HashMap<BlockPos, Explosion> explosions;

    public TNTX50Entity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<>();
    }

    public TNTX50Entity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.TNT_X50.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new Explosion(getLevel(), pos, getOwner(), 30, 0.01D, 2D, 500)); //max 500 blocks per tick
        }

        Explosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.OreMinerExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class OreMinerTNTEntity extends TNTEntity {
    private HashMap<BlockPos, OreMinerExplosion> explosions;
    public OreMinerTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<>();
    }

    public OreMinerTNTEntity( Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.ORE_MINER_TNT.get(), level, x, y, z, livingEntity);
        explosions = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new OreMinerExplosion(getLevel(), pos, getOwner(), 30, 0D, 2D, 5)); //max 1 block per tick
        }

        OreMinerExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }
}

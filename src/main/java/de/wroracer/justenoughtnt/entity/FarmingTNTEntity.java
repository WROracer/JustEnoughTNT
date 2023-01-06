package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import de.wroracer.justenoughtnt.util.FarmExplosion;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class FarmingTNTEntity extends TNTEntity {
    private HashMap<BlockPos, FarmExplosion> explosions;

    public FarmingTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<>();
    }

    public FarmingTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.FARMING_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new FarmExplosion(getLevel(), pos, getOwner(), 13, 0D, 0D, 1000)); //max 1000 blocks per tick
        }

        FarmExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }
    }

}

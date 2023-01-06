package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.explosions.BlockChangeExplosion;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class BlockChangeTNTEntity extends TNTEntity {
    private final HashMap<BlockPos, BlockChangeExplosion> explosions;

    public BlockChangeTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        explosions = new HashMap<>();
    }

    public BlockChangeTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.BLOCK_CHANGE_TNT.get(), level, x, y, z, livingEntity);
        explosions = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new BlockChangeExplosion(getLevel(), pos, getOwner(), 30, 0D, 2D, 500)); //max 1 block per tick
        }

        BlockChangeExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            discard();
        }

    }
}

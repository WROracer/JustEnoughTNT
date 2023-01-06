package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DupstepTNTEntity extends TNTEntity {
    public DupstepTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public DupstepTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.DUPSTEP_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void tick() {
        super.tick();
        int fuse = getFuse();
        Level world = getLevel();
        if (fuse <= 20) {
            PrimedTnt newTnt = new PrimedTnt(world, getX(), getY() + 20, getZ(), getOwner());
            // set the fuse between 20 and 80
            // newTnt.setFuse(world.random.nextInt(40) + 20);

            world.addFreshEntity(newTnt);

        }
    }

    @Override
    public void explode() {
        this.discard();
    }
}

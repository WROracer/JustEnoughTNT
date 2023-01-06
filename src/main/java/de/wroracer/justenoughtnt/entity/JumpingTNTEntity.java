package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class JumpingTNTEntity extends TNTEntity {

    private int cycle = 8;

    public JumpingTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.JUMPING_TNT.get(), level, x, y, z, livingEntity);
    }

    public JumpingTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();


        if (cycle == 0) {
            this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
            discard();
        } else {
            for (int i = 0; i < 2; i++) {
                double x = pos.getX();
                double y = pos.getY();
                double z = pos.getZ();
                JumpingTNTEntity newTNT = ModEntities.JUMPING_TNT.get().create(this.level);
                assert newTNT != null;

                //Move The Velocity of the new TNT to a Random Direction
                Vec3 vec = new Vec3(this.random.nextDouble() * 0.5D - 0.1D, 1D, this.random.nextDouble() * 0.5D - 0.1D);
                vec = vec.normalize();
                newTNT.setVelocity(vec.x, vec.y, vec.z);

                newTNT.setPos(x, y, z);
                newTNT.setCycle(cycle - 1);
                newTNT.setFuse(50);
                getLevel().addFreshEntity(newTNT);
            }
            discard();

        }
    }

    private void setCycle(int cycle) {
        this.cycle = cycle;
    }
}

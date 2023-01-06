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

public class OneJumpTNTEntity extends TNTEntity {
    private int cycle = 8;

    public OneJumpTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public OneJumpTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.ONE_JUMP_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (cycle == 0) {
            this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F,
                    Explosion.BlockInteraction.BREAK);
            discard();
        } else {

            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            OneJumpTNTEntity newTNT = ModEntities.ONE_JUMP_TNT.get().create(this.level);
            assert newTNT != null;

            //Move The Velocity of the new TNT to a Random Direction
            Vec3 vec = new Vec3(this.random.nextDouble() * 0.5D - 0.1D, 1D, this.random.nextDouble() * 0.5D - 0.1D);
            vec = vec.normalize();
            System.out.println("Vec: " + vec.x + " " + vec.y + " " + vec.z);
            newTNT.setVelocity(vec.x, vec.y, vec.z);

            newTNT.setPos(x, y, z);
            newTNT.setCycle(cycle - 1);
            newTNT.setFuse(50);
            System.out.println("Spawned new TNT with cycle: " + cycle + " at " + x + " " + y + " " + z);
            getLevel().addFreshEntity(newTNT);

            discard();

        }
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
}

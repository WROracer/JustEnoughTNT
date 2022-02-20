package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class JumpingTNTEntity extends BaseTNT{

    private HashMap<BaseTNT, Integer> cycles;

    public JumpingTNTEntity( Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.JUMPING_TNT.get(), level, x, y, z, livingEntity);
    }

    public JumpingTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        cycles = new HashMap<>();
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        if (!cycles.containsKey(this)) {
            cycles.put(this, 8);
        }

        int cycle = this.cycles.get(this);
        cycles.remove(this);

        if (cycle == 0) {
            discard();

        } else {
            for (int i = 0; i < 2; i++) {
                double x = pos.getX();
                double y = pos.getY();
                double z = pos.getZ();
                BaseTNT newTNT = new BaseTNT(EntityType.TNT,getLevel(), x, y, z,getOwner());
                newTNT.setDeltaMovement(newTNT.getDeltaMovement().multiply(new Vec3(8, 8, 8)));
                cycles.put(newTNT, cycle - 1);
            }
            discard();

        }
    }
}

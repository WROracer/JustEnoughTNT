package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.TNTEntity;
import de.wroracer.justenoughtnt.entity.DupstepTNTEntity;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class DupstepTNT extends BaseTNTBlock<DupstepTNTEntity> {
    public DupstepTNT(Properties properties, int fuse) {
        super(properties, fuse, ModEntities.DUPSTEP_TNT);
    }

    public DupstepTNT(Properties properties) {
        super(properties, ModEntities.DUPSTEP_TNT);
    }

    // idea by @nevr4givezedevil from twitch

    @Override
    public void onFuse(TNTEntity tnt) {
        tnt.setFuse(50);
        Level world = tnt.getLevel();
        double tntX = tnt.getX();
        double tntY = tnt.getY() + 0.5;
        double tntZ = tnt.getZ();

        for (int i = 0; i < 1000; i++) {
            double positionOffset = world.random.nextDouble() * 0.5;
            double heightOffset = world.random.nextDouble() * 0.5;
            double xMovementOffset = world.random.nextDouble() * 0.05;
            double zMovementOffset = world.random.nextDouble() * 0.05;

            double height = tntY + (world.random.nextBoolean() ? -heightOffset : heightOffset);

            double x = tntX + (world.random.nextBoolean() ? positionOffset : -positionOffset);
            double z = tntZ + (world.random.nextBoolean() ? positionOffset : -positionOffset);

            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, height, z, xMovementOffset, 1, zMovementOffset);
        }
    }

}

package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

public class DupstepTNT extends BaseTNTBlock {
    // idea by @nevr4givezedevil from twitch

    public DupstepTNT(Properties properties) {
        super(properties, 30);
    }

    @Override
    public void onFuse(BaseTNT tnt) {
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

    @Override
    public boolean fuseTick(BaseTNT tnt) {
        int fuse = tnt.getFuse();
        Level world = tnt.getLevel();
        if (fuse <= 20) {
            PrimedTnt newTnt = new PrimedTnt(world, tnt.getX(), tnt.getY() + 20, tnt.getZ(), tnt.getOwner());
            // set the fuse between 20 and 80
            // newTnt.setFuse(world.random.nextInt(40) + 20);

            world.addFreshEntity(newTnt);

        }
        return true;

    }

    @Override
    public void onExplode(BaseTNT tnt) {
        tnt.discard();

    }

}

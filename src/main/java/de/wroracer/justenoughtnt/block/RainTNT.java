package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.phys.Vec3;

public class RainTNT extends BaseTNTBlock {
    // idea by @nevr4givezedevil from twitch

    public RainTNT(Properties properties) {
        super(properties);
    }

    @Override
    public boolean fuseTick(BaseTNT tnt) {

        Vec3 motion = tnt.getDeltaMovement();

        motion = motion.add(0, 0.05, 0);
        tnt.setDeltaMovement(motion);

        return true;
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        for (int i = 0; i < 300; i++) {
            PrimedTnt newTNT = new PrimedTnt(tnt.getLevel(), tnt.getPos().getX(), tnt.getPos().getY(),
                    tnt.getPos().getZ(), tnt.getOwner());

            Vec3 velocity = newTNT.getDeltaMovement();

            double zMovementOffset = (Math.random() - 0.5) * 1.5;
            double xMovementOffset = (Math.random() - 0.5) * 1.5;

            velocity = velocity.add(xMovementOffset, 0, zMovementOffset);
            newTNT.setDeltaMovement(velocity);
            tnt.setFuse(100);
            tnt.getLevel().addFreshEntity(newTNT);
        }
        tnt.discard();

    }

}

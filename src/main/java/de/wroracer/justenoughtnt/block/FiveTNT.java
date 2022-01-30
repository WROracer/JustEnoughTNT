package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.Explosion;

public class FiveTNT extends BaseTNTBlock {

    public FiveTNT(Properties properties) {
        super(properties);
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        Explosion explosion = new Explosion(tnt.getLevel(), tnt.getPos(), tnt.getOwner(), 8f, 0.05D, 2D); // 1 tnt ~ 4
        explosion.explode();

        tnt.discard();
    }

}

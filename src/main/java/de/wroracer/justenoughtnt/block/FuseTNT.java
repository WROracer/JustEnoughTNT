package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.FuseExplosion;
import net.minecraft.core.BlockPos;

public class FuseTNT extends BaseTNTBlock {

    private HashMap<BlockPos, FuseExplosion> explosions;

    public FuseTNT(Properties properties) {
        super(properties);
        explosions = new HashMap<BlockPos, FuseExplosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new FuseExplosion(tnt.getLevel(), pos, tnt.getOwner(), 45, 0.05D, 20, 1000)); //max 1000 blocks per tick
        }

        FuseExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }

}

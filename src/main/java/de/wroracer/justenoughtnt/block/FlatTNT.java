package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.FlatExplosion;
import net.minecraft.core.BlockPos;

public class FlatTNT extends BaseTNTBlock {
    private HashMap<BlockPos, FlatExplosion> explosions;

    public FlatTNT(Properties properties) {
        super(properties);
        explosions = new HashMap<BlockPos, FlatExplosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new FlatExplosion(tnt.getLevel(), pos, tnt.getOwner(), 19, 0.05D, 2D, 1000)); //max 1000 blocks per tick
        }

        FlatExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }
}

package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.CubeExplosion;
import net.minecraft.core.BlockPos;

public class CubeTNT extends BaseTNTBlock {

    private HashMap<BlockPos, CubeExplosion> explosions;

    public CubeTNT(Properties properties) {
        super(properties);
        explosions = new HashMap<BlockPos, CubeExplosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new CubeExplosion(tnt.getLevel(), pos, tnt.getOwner(), 5, 0.05D, 0, 1000)); //max 1000 blocks per tick
        }

        CubeExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }

}

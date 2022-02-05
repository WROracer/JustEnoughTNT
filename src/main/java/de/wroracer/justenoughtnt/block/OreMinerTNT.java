package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.OreMinerExplosion;
import net.minecraft.core.BlockPos;

public class OreMinerTNT extends BaseTNTBlock {
    private HashMap<BlockPos, OreMinerExplosion> explosions;

    public OreMinerTNT(Properties properties) {
        super(properties);
        explosions = new HashMap<BlockPos, OreMinerExplosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new OreMinerExplosion(tnt.getLevel(), pos, tnt.getOwner(), 30, 0D, 2D, 5)); //max 1 block per tick
        }

        OreMinerExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }

}

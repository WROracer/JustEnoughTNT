package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.DrainExplosion;
import net.minecraft.core.BlockPos;

public class DrainTNT extends BaseTNTBlock {

    private HashMap<BlockPos, DrainExplosion> explosions;

    public DrainTNT(Properties properties) {
        super(properties, 8 * 20); // 8 seconds
        explosions = new HashMap<BlockPos, DrainExplosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new DrainExplosion(tnt.getLevel(), pos, tnt.getOwner(), 45f, 0.001D, 2D, 3000)); //max 3000 blocks per tick
        }

        DrainExplosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }
}

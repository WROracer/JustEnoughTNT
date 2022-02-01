package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.core.BlockPos;

public class TNTX50 extends BaseTNTBlock {
    private HashMap<BlockPos, Explosion> explosions;

    public TNTX50(Properties properties) {
        super(properties, 13 * 20); // 13 seconds
        explosions = new HashMap<BlockPos, Explosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new Explosion(tnt.getLevel(), pos, tnt.getOwner(), 30, 0.01D, 2D, 500)); //max 500 blocks per tick
        }

        Explosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }

}

package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.core.BlockPos;

public class TNTX500 extends BaseTNTBlock {
    private HashMap<BlockPos, Explosion> explosions;

    public TNTX500(Properties properties) {
        super(properties, 20 * 20); // 20 seconds
        explosions = new HashMap<BlockPos, Explosion>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!explosions.containsKey(pos)) {
            explosions.put(pos, new Explosion(tnt.getLevel(), pos, tnt.getOwner(), 67, 0D, 2D, 1000)); //max 1000 blocks per tick
        }

        Explosion explosion = this.explosions.get(pos);

        explosion.explode();
        if (explosion.tick()) {
            explosions.remove(pos);
            tnt.discard();
        }
    }

}

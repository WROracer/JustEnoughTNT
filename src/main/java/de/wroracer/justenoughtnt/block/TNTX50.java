package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class TNTX50 extends BaseTNTBlock {
    private Explosion explosion;

    public TNTX50(Properties properties) {
        super(properties, 13 * 20); // 13 sec
        explosion = null;
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        // play explosion sound
        Level world = tnt.getLevel();

        if (explosion == null) {
            world.playSound(null, tnt.getPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 0.8f);
            explosion = new Explosion(tnt.getLevel(), tnt.getPos(), tnt.getOwner(), 30f, 0.03D, 2D, 100); /// 1 tnt ~ 4 2 tnt ~ 6
        }
        explosion.explode();
        if (explosion.tick()) {
            tnt.discard();
            explosion = null;
        }
    }

}

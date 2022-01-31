package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import de.wroracer.justenoughtnt.util.Explosion;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class TNTX10 extends BaseTNTBlock {

    public TNTX10(Properties properties) {
        super(properties, 6 * 20); // 6 sec
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        // play explosion sound
        Level world = tnt.getLevel();

        world.playSound(null, tnt.getPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 0.8f);

        Explosion explosion = new Explosion(tnt.getLevel(), tnt.getPos(), tnt.getOwner(), 13f, 0.05D, 2D); // 1 tnt ~ 4 2 tnt ~ 6
        explosion.explode();

        tnt.discard();
    }

}
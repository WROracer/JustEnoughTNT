package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class JumpingTNT extends BaseTNTBlock {

    private HashMap<BaseTNT, Integer> cycles;

    public JumpingTNT(Properties properties) {
        super(properties, 3 * 20); // 13 seconds
        cycles = new HashMap<BaseTNT, Integer>(); // TODO: fix this
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!cycles.containsKey(tnt)) {
            cycles.put(tnt, 8);
        }

        int cycle = this.cycles.get(tnt);
        cycles.remove(tnt);

        if (cycle == 0) {
            tnt.discard();

        } else {
            for (int i = 0; i < 2; i++) {
                double x = pos.getX();
                double y = pos.getY();
                double z = pos.getZ();
                BaseTNT newTNT = new BaseTNT(tnt.getLevel(), x, y, z, this, tnt.getOwner());
                newTNT.setDeltaMovement(newTNT.getDeltaMovement().multiply(new Vec3(8, 8, 8)));
                cycles.put(newTNT, cycle - 1);
            }
            tnt.discard();

        }
    }

}

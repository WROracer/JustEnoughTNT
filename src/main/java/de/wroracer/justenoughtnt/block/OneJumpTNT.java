package de.wroracer.justenoughtnt.block;

import java.util.HashMap;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.phys.Vec3;

public class OneJumpTNT extends BaseTNTBlock {

    private HashMap<BaseTNT, Integer> cycles;

    public OneJumpTNT(Properties properties) {
        super(properties);
        cycles = new HashMap<BaseTNT, Integer>();
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        BlockPos pos = tnt.getPos();

        if (!cycles.containsKey(tnt)) {
            cycles.put(tnt, 7);
        }

        int cycle = this.cycles.get(tnt);
        cycles.remove(tnt);

        if (cycle == 0) {
            PrimedTnt primedTnt = new PrimedTnt(tnt.level, pos.getX(), pos.getY(), pos.getZ(), tnt.getOwner());
            tnt.getLevel().addFreshEntity(primedTnt);
            tnt.discard();

        } else {

            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;
            BaseTNT newTNT = new BaseTNT(tnt.getLevel(), x, y, z, this, tnt.getOwner());
            newTNT.setDeltaMovement(newTNT.getDeltaMovement().multiply(new Vec3(3, 3, 3)));
            newTNT.setFuse(newTNT.getFuse() / 2);
            newTNT.getLevel().addFreshEntity(newTNT);
            cycles.put(newTNT, cycle - 1);

            tnt.discard();

        }
    }

}

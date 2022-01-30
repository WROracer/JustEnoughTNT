package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExampleTNT extends BaseTNTBlock {
    public ExampleTNT(Properties properties) {
        super(properties);
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        Level world = tnt.getLevel();
        BlockPos pos = tnt.getPos();

        for (int i = 0; i < 10; i++) {
            // world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            PrimedTnt toSpawn = new PrimedTnt(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    tnt.getOwner());
            Vec3 movement = toSpawn.getDeltaMovement().multiply(8, 8, 8);
            toSpawn.setDeltaMovement(movement);
            world.addFreshEntity(toSpawn);
        }
        tnt.discard();
    }
}

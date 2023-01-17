package de.wroracer.justenoughtnt.luckyBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class DeathFallAction extends LuckyBlockAction {

    public DeathFallAction(Level world, BlockPos pos, Entity source) {
        super(world, pos, source);
    }

    @Override
    public boolean execute() {
        // make a 3x3 hole below the player that is 10 blocks deep and at the bottom 3 high lava
        if (source == null) {
            return false;
        }
        BlockPos playerPos = source.blockPosition();

        // making the hole
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = -3; y <= 20; y++) {
                    world.setBlockAndUpdate(playerPos.offset(x, -y, z), Blocks.AIR.defaultBlockState());
                }
            }
        }

        // lava
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = 0; y <= 3; y++) {
                    world.setBlockAndUpdate(playerPos.offset(x, -y - 20, z), Blocks.LAVA.defaultBlockState());
                }
            }
        }

        return true;

    }

}

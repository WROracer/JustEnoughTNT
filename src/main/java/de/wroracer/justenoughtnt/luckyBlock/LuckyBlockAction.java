package de.wroracer.justenoughtnt.luckyBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public abstract class LuckyBlockAction {

    protected Level world;
    protected BlockPos pos;
    protected Entity source;

    public LuckyBlockAction(Level world, BlockPos pos, Entity source) {

        this.world = world;
        this.pos = pos;
        this.source = source;
    }

    public abstract boolean execute();

}

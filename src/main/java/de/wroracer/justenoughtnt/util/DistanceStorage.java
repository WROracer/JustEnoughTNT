package de.wroracer.justenoughtnt.util;

import net.minecraft.core.BlockPos;

public class DistanceStorage {
    private BlockPos pos;
    private double distance;

    public DistanceStorage(double distance, BlockPos pos) {
        this.distance = distance;
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }

    public double getDistance() {
        return distance;
    }
}

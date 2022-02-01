package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

public class BaseTNT extends PrimedTnt {

    private BaseTNTBlock tntBlock;

    public BaseTNT(Level world, double x, double y, double z, BaseTNTBlock tntBlock, LivingEntity igniter) {
        super(world, x, y, z, igniter);
        this.tntBlock = tntBlock;
    }

    @Override
    public void tick() {

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            // this.discard();
            if (!this.level.isClientSide) {
                this.explode_();
            }
        } else {
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
            if (this.onGround) {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
            }
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D,
                        0.0D);
            }
        }

    }

    private void explode_() {
        tntBlock.onExplode(this);
    }

    public BlockPos getPos() {
        return new BlockPos(this.getBlockX(), this.getBlockY(), this.getBlockZ());
    }
}

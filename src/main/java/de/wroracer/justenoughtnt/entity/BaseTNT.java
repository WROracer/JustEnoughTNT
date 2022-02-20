package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class BaseTNT extends Entity {

    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(BaseTNT.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE_TIME = 80;
    @Nullable
    private LivingEntity owner;

    public BaseTNT(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public BaseTNT(EntityType<? extends Entity> entityType,Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        this(entityType, level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = livingEntity;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    protected void addAdditionalSaveData(CompoundTag p_32097_) {
        p_32097_.putShort("Fuse", (short)this.getFuse());
    }

    protected void readAdditionalSaveData(CompoundTag p_32091_) {
        this.setFuse(p_32091_.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    protected float getEyeHeight(Pose p_32088_, EntityDimensions p_32089_) {
        return 0.15F;
    }

    public void setFuse(int p_32086_) {
        this.entityData.set(DATA_FUSE_ID, p_32086_);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void tick() {

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            //this.discard();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            if (fuseTick(this)) {

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
                    this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D,
                            0.0D, 0.0D);
                }
            }
        }

    }

    public void explode(){
        float f = 4.0F;
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Explosion.BlockInteraction.BREAK);
    }

    public boolean fuseTick(BaseTNT tnt) {
        return tntBlock.fuseTick(tnt);
    }

    public BlockPos getPos() {
        return new BlockPos(this.getX(), this.getY(), this.getZ());
    }

    public BaseTNT setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
        return this;
    }
}

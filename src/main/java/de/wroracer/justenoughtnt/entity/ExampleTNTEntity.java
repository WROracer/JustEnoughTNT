package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ExampleTNTEntity extends BaseTNT{
    public ExampleTNTEntity(Level world, double x, double y, double z, LivingEntity igniter) {
        super(ModEntities.EXAMPLE_TNT.get(), world, x, y, z, igniter);
    }
    public ExampleTNTEntity(EntityType<? extends BaseTNT> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    @Override
    public void explode() {
        Level world = getLevel();

        world.playSound(null, getPos(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1f, 1f);
        BlockPos pos = getPos();

        for (int i = 0; i < 10; i++) {
            // world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            PrimedTnt toSpawn = new PrimedTnt(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                    getOwner());
            Vec3 movement = toSpawn.getDeltaMovement().multiply(8, 8, 8);
            toSpawn.setDeltaMovement(movement);
            world.addFreshEntity(toSpawn);
        }
        discard();
    }
}

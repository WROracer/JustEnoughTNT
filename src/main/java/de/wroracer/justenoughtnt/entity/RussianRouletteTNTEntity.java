package de.wroracer.justenoughtnt.entity;

import de.wroracer.justenoughtnt.setup.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RussianRouletteTNTEntity extends TNTEntity {
    public RussianRouletteTNTEntity(EntityType<? extends Entity> entityType, Level level) {
        super(entityType, level);
    }

    public RussianRouletteTNTEntity(Level level, double x, double y, double z, @Nullable LivingEntity livingEntity) {
        super(ModEntities.RUSSIAN_ROULETTE_TNT.get(), level, x, y, z, livingEntity);
    }

    @Override
    public void explode() {
        BlockPos pos = getPos();

        Level world = getLevel();
        LivingEntity owner = getOwner();
        int num = world.random.nextInt(8);
        // JustEnoughTNT.LOGGER.info(num);
        if (num == 0) {
            PrimedTnt primedTnt = new PrimedTnt(level, pos.getX(), pos.getY(), pos.getZ(), getOwner());
            primedTnt.setFuse(0);
            getLevel().addFreshEntity(primedTnt);
            discard();
            if (owner != null) {
                owner.sendMessage(new TextComponent("[Russian Roulette TNT] You got not very lucky my friend"),
                        owner.getUUID());
            }
        } else {
            if (owner != null) {

                owner.sendMessage(new TextComponent("[Russian Roulette TNT] You got lucky my friend"), owner.getUUID());
            }
            discard();
        }
    }
}

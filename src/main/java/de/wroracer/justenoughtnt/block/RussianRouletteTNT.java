package de.wroracer.justenoughtnt.block;

import de.wroracer.justenoughtnt.entity.BaseTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;

public class RussianRouletteTNT extends BaseTNTBlock {

    public RussianRouletteTNT(Properties properties) {
        super(properties, 0);
    }

    @Override
    public void onExplode(BaseTNT tnt) {
        // 1 to 8 chance to explode
        BlockPos pos = tnt.getPos();

        Level world = tnt.getLevel();
        LivingEntity owner = tnt.getOwner();
        int num = world.random.nextInt(8);
        // JustEnoughTNT.LOGGER.info(num);
        if (num == 0) {
            PrimedTnt primedTnt = new PrimedTnt(tnt.level, pos.getX(), pos.getY(), pos.getZ(), tnt.getOwner());
            primedTnt.setFuse(0);
            tnt.getLevel().addFreshEntity(primedTnt);
            tnt.discard();
            if (owner != null) {
                owner.sendMessage(new TextComponent("[Russian Roulette TNT] You got not verry lucky my friend"),
                        owner.getUUID());
            }
        } else {
            if (owner != null) {

                owner.sendMessage(new TextComponent("[Russian Roulette TNT] You got lucky my firend"), owner.getUUID());
            }
            tnt.discard();
        }

    }

}

package de.wroracer.justenoughtnt.explosions;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;

public class FarmExplosion extends Explosion {
    private final ArrayList<Block> crops = new ArrayList<>();

    public FarmExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
        initCrops();
    }

    private void initCrops() {
        // crops.add(Blocks.WHEAT);
        // crops.add(Blocks.CARROTS);
        // crops.add(Blocks.POTATOES);
        // crops.add(Blocks.BEETROOTS);

        // fill crops with all blocks in the forge:crops tag
        for (Item i : Tags.Items.CROPS.getValues()) {
            // if (i == Items.AIR || i == Items.NETHER_WART)
            //     continue;
            Block block = Block.byItem(i);
            if (block == Blocks.AIR || block == Blocks.NETHER_WART || block == Blocks.CAVE_AIR
                    || block == Blocks.VOID_AIR)
                continue;
            crops.add(Block.byItem(i));

        }
        crops.add(Blocks.WHEAT);
        crops.add(Blocks.MELON_STEM);
        crops.add(Blocks.PUMPKIN_STEM);
    }

    public FarmExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance, double randomness,
                         int perTick) {
        super(world, pos, source, radius, dropChance, randomness, perTick);
        initCrops();
    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = this.getLevel().getBlockState(pos).getBlock();
        Block above = this.getLevel().getBlockState(pos.above()).getBlock();

        boolean isDirt = dirtCheck(block);

        return (isDirt && airCheck(above))
                || block instanceof BaseTNTBlock;
    }

    private boolean dirtCheck(Block block) {
        return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.COARSE_DIRT
                || block == Blocks.PODZOL || block == Blocks.MYCELIUM || block == Blocks.FARMLAND;
    }

    private boolean airCheck(Block block) {
        return block == Blocks.AIR || block == Blocks.CAVE_AIR || block == Blocks.VOID_AIR
                || block == Blocks.TALL_GRASS || block == Blocks.GRASS;

    }

    @Override
    public void destroyBlock(BlockPos pos) {
        // replace all dirt with farmland and all air above it with some crops
        Block block = this.getLevel().getBlockState(pos).getBlock();
        Block above = this.getLevel().getBlockState(pos.above()).getBlock();

        if (dirtCheck(block)) {
            this.getLevel().setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
        } else if (block instanceof BaseTNTBlock tntBlock) {
            tntBlock.wasExplodedByJET(this.getLevel(), this.getPos(), (LivingEntity) this.getSource());
        }
        if (airCheck(above)) {

            Block crop = crops.get((int) Math.floor(Math.random() * crops.size()));

            // JustEnoughTNT.LOGGER.info("Placing " + crop.getRegistryName());

            this.getLevel().setBlockAndUpdate(pos.above(), crop.defaultBlockState());

        }
    }

}

package de.wroracer.justenoughtnt.util;

import de.wroracer.justenoughtnt.block.BaseTNTBlock;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BlockChangeExplosion extends Explosion {

    private HashMap<Block, Block> blockChangeMap = new HashMap<>();

    public BlockChangeExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance,
                                double randomness) {
        super(world, pos, source, radius, dropChance, randomness);
        blockChangeMap = new HashMap<>();
        fillHashMap();
    }

    private void fillHashMap() {
        ArrayList<BlockPos> blockPos = getBlocks();
        ArrayList<Block> blocks = new ArrayList<>();
        //add all blocks to the list
        for (BlockPos pos : blockPos) {
            BlockState state = this.getLevel().getBlockState(pos);
            Block block = state.getBlock();
            if (!blocks.contains(block)) {
                blocks.add(block);
            }
        }
        ArrayList<Block> blocks1 = new ArrayList<>(blocks);
        Collections.shuffle(blocks1);
        for (int i = 0; i < blocks.size(); i++) {
            blockChangeMap.put(blocks.get(i), blocks1.get(i));
        }
        System.out.println(blockChangeMap);
    }

    public BlockChangeExplosion(Level world, BlockPos pos, Entity source, float radius, double dropChance,
                                double randomness, int perTick) {
        super(world, pos, source, radius, dropChance, randomness, perTick);
        blockChangeMap = new HashMap<>();
        fillHashMap();
    }

    @Override
    public void modifyEntities() {
        // do nothing
    }

    @Override
    public boolean shouldDestroy(BlockPos pos) {
        Block block = getLevel().getBlockState(pos).getBlock();
        boolean isDestructable = block == Blocks.BEDROCK || block instanceof AirBlock || (block.getRegistryName() == ModBlocks.BLOCK_CHANGE_TNT.get().getRegistryName());

        return !isDestructable;
    }

    @Override
    public void destroyBlock(BlockPos pos) {
        Level world = this.getLevel();
        Block block = world.getBlockState(pos).getBlock();
        if (block instanceof BaseTNTBlock tntBlock) {
            // it is one of our tnt blocks
            tntBlock.wasExplodedByJET(world, pos, (LivingEntity) this.getSource());
        } else {
            // set the block to air
            // and put it above the player and mine it
            BlockState state = world.getBlockState(pos);
            Block newBlock = this.blockChangeMap.get(state.getBlock());
            if (newBlock != null) {
                //System.out.println("Changing block from " + state.getBlock().getRegistryName() + " to " + newBlock.getRegistryName());
                world.setBlock(pos, newBlock.defaultBlockState(), 3);
            } else {
                //System.out.println("Changing block from " + state.getBlock().getName() + " to air");
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }

            // get the block from the map and replace it
            // with the block above the player
            // world.destroyBlock(pos, false);

        }

    }

}

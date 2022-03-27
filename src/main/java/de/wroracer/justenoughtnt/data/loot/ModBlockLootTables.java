package de.wroracer.justenoughtnt.data.loot;

import java.util.stream.Collectors;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockLootTables extends BlockLoot {
        private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
        private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item()
                        .hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH,
                                        MinMaxBounds.Ints.atLeast(1))));
        private static final LootItemCondition.Builder SHEARS = MatchTool
                        .toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
        private static final LootItemCondition.Builder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
        private static final LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();

        @Override
        protected void addTables() {
                dropSelf(ModBlocks.EXAMPLE_TNT.get());
                dropSelf(ModBlocks.TNT_X5.get());
                dropSelf(ModBlocks.TNT_X10.get());
                dropSelf(ModBlocks.TNT_X20.get());
                dropSelf(ModBlocks.TNT_X50.get());
                dropSelf(ModBlocks.TNT_X100.get());
                dropSelf(ModBlocks.TNT_X500.get());
                dropSelf(ModBlocks.TNT_X1K.get());
                dropSelf(ModBlocks.DRAIN_TNT.get());
                dropSelf(ModBlocks.DRAIN_TNT_X5.get());
                dropSelf(ModBlocks.JUMPING_TNT.get());
                dropSelf(ModBlocks.ONE_JUMP_TNT.get());
                dropSelf(ModBlocks.RUSSIAN_ROULETTE_TNT.get());
                dropSelf(ModBlocks.DUPSTEP_TNT.get());
                dropSelf(ModBlocks.RAIN_TNT.get());
                dropSelf(ModBlocks.ORE_MINER_TNT.get());
                dropSelf(ModBlocks.FLAT_TNT.get());
                dropSelf(ModBlocks.CIRCLE_TNT.get());
                dropSelf(ModBlocks.CUBE_TNT.get());
                dropSelf(ModBlocks.FUSE_TNT.get());
                dropSelf(ModBlocks.BLOCK_CHANGE_TNT.get());

                add(ModBlocks.SULFUR_ORE.get(),
                                createOreDrop(ModBlocks.SULFUR_ORE.get(), ModItems.SULFUR_DUST.get()));
                add(ModBlocks.DEEPSLATE_SULFUR_ORE.get(),
                                createOreDrop(ModBlocks.DEEPSLATE_SULFUR_ORE.get(), ModItems.SULFUR_DUST.get()));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
                return ForgeRegistries.BLOCKS.getValues().stream()
                                .filter(block -> JustEnoughTNT.MOD_ID.equals(block.getRegistryName().getNamespace()))
                                .collect(Collectors.toSet());
        }
}

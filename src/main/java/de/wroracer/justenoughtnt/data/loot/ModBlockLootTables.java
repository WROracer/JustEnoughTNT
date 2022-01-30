package de.wroracer.justenoughtnt.data.loot;

import de.wroracer.justenoughtnt.JustEnoughTNT;
import de.wroracer.justenoughtnt.setup.ModBlocks;
import de.wroracer.justenoughtnt.setup.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLoot {
    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final LootItemCondition.Builder SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Tags.Items.SHEARS));
    private static final LootItemCondition.Builder SILK_TOUCH_OR_SHEARS = SHEARS.or(SILK_TOUCH);
    private static final LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = SILK_TOUCH_OR_SHEARS.invert();

    @Override
    protected void addTables() {
        dropSelf(ModBlocks.EXAMPLE_TNT.get());

        add(ModBlocks.SULFUR_ORE.get(),
                createOreDrop(ModBlocks.SULFUR_ORE.get(),ModItems.SULFUR_DUST.get()));
        add(ModBlocks.DEEPSLATE_SULFUR_ORE.get(),
                createOreDrop(ModBlocks.DEEPSLATE_SULFUR_ORE.get(),ModItems.SULFUR_DUST.get()));
    }



    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> JustEnoughTNT.MOD_ID.equals(block.getRegistryName().getNamespace()))
                .collect(Collectors.toSet());
    }
}

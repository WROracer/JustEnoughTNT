package de.wroracer.justenoughtnt.setup;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ModOres {

        public static void addOres(final BiomeLoadingEvent event) {

                addOre(event, OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SILVER_ORE.get().defaultBlockState(),
                                "silver_ore", 5, 3, 90, -50);

        }

        private static void addOre(BiomeLoadingEvent event, RuleTest rule, BlockState state, String registryName,
                        int veinSize, int amount, int maxHeight, int minHeight) {
                event.getGeneration().addFeature(
                                GenerationStep.Decoration.UNDERGROUND_ORES, registerPlacedFeature(
                                                registryName,
                                                Feature.ORE.configured(
                                                                new OreConfiguration(rule, state, veinSize)),
                                                CountPlacement.of(amount),
                                                InSquarePlacement.spread(),
                                                BiomeFilter.biome(),
                                                HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight),
                                                                VerticalAnchor.absolute(maxHeight))));
        }

        private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(
                        String registryName,
                        ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
                PlacedFeature placed = BuiltinRegistries
                                .register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registryName),
                                                feature)
                                .placed(placementModifiers);
                return PlacementUtils.register(registryName, placed);
        }

}

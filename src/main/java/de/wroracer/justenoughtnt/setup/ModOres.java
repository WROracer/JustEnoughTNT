package de.wroracer.justenoughtnt.setup;


import com.google.common.base.Predicates;
import de.wroracer.justenoughtnt.JustEnoughTNT;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModOres {




    public static void addOres(final BiomeLoadingEvent event){

        addOre(event, OreFeatures.STONE_ORE_REPLACEABLES,ModBlocks.SILVER_ORE.get().defaultBlockState(), "silver_ore",5,3,90,-50);

    }

    private static void addOre(BiomeLoadingEvent event,RuleTest rule,BlockState state,String registryName , int veinSize, int amount, int maxHeight, int minHeight){
        event.getGeneration().addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES, registerPlacedFeature(
                        registryName,
                        Feature.ORE.configured(
                                new OreConfiguration(rule, state, veinSize)),
                        CountPlacement.of(amount),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))
                ));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(String registryName,
                                                                                                              ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        PlacedFeature placed = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registryName), feature)
                .placed(placementModifiers);
        return PlacementUtils.register(registryName, placed);
    }


}

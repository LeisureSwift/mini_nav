package com.leisure.miniuav.world.feature;

import com.leisure.miniuav.init.BlockInit;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> EBONY_PLACES_KEY = createKey("ebony_placed");
    public static final ResourceKey<PlacedFeature> FANTOM_PLACES_KEY = createKey("fantom_placed");
    public static final ResourceKey<PlacedFeature> NETHER_FANTOM_PLACES_KEY = createKey("nether_fantom_placed");
    public static final ResourceKey<PlacedFeature> END_FANTOM_PLACES_KEY = createKey("end_fantom_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        //自定义树木生成
        register(context, EBONY_PLACES_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), BlockInit.ROCK_BLOCK.get()));
        //自定义矿石生成
        register(context, FANTOM_PLACES_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_FANTOM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(16, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, NETHER_FANTOM_PLACES_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FANTOM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, END_FANTOM_PLACES_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_FANTOM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(9, HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));

    }

    //矿石摆放
    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Reference.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}

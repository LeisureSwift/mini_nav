package com.leisure.miniuav.world.feature;

import com.leisure.miniuav.init.BlockInit;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;


public class ModConfiguredFeatures {

    //自定义树木
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FANTOM_ORE_KEY = registerKey("fantom_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FANTOM_ORE_KEY = registerKey("nether_fantom_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_FANTOM_ORE_KEY = registerKey("end_fantom_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //普通矿石
        RuleTest stoneReplaceAbles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        //深层矿石
        RuleTest deepSlateReplaceAbles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        //下界矿石
        RuleTest netherRackReplaceAbles = new BlockMatchTest(Blocks.NETHERRACK);
        //末地矿石
        RuleTest endStoneReplaceAbles = new BlockMatchTest(Blocks.END_STONE);

        //主世界
        List<OreConfiguration.TargetBlockState> overWorldFantomOres = List.of(
                OreConfiguration.target(stoneReplaceAbles, BlockInit.FANTOM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepSlateReplaceAbles, BlockInit.DEEPSLATE_FANTOM_ORE.get().defaultBlockState()));

        //注册树木生成逻辑
        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockInit.ROCK_BLOCK.get()),
                new StraightTrunkPlacer(5, 6, 3),
                BlockStateProvider.simple(BlockInit.ROCK_BLOCK.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        //注册矿石生成逻辑
        register(context, OVERWORLD_FANTOM_ORE_KEY, Feature.ORE, new OreConfiguration(overWorldFantomOres, 9));
        register(context, NETHER_FANTOM_ORE_KEY, Feature.ORE, new OreConfiguration(netherRackReplaceAbles, BlockInit.NETHER_FANTOM_ORE.get().defaultBlockState(), 9));
        register(context, END_FANTOM_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceAbles, BlockInit.END_FANTOM_ORE.get().defaultBlockState(), 9));
    }

    //将这种矿石生成类型进行注册
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Reference.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key,
                                                                                          F feature,
                                                                                          FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

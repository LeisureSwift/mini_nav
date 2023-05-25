package com.leisure.miniuav.world.feature;

import com.google.common.base.Suppliers;
import com.leisure.miniuav.block.BlockRegister;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.OreFeatures;
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
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;


public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> EBONY_KEY = registerKey("ebony");

    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_BLACK_OPAL_ORE_KEY = registerKey("black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> END_BLACK_OPAL_ORE_KEY = registerKey("end_black_opal_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> NETHER_BLACK_OPEL_ORE_KEY = registerKey("nether_black_opel_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stoneReplaceAbles = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepSlateReplaceAbles = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherRackReplaceAbles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endStoneReplaceAbles = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overWorldBlackOpalOres = List.of(OreConfiguration.target(stoneReplaceAbles,
                BlockRegister.FANTOM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepSlateReplaceAbles, BlockRegister.DEEPSLATE_FANTOM_ORE.get().defaultBlockState()));

        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BlockRegister.ROCK_BLOCK.get()),
                new StraightTrunkPlacer(5,6,3),
                BlockStateProvider.simple(BlockRegister.ROCK_BLOCK.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                new TwoLayersFeatureSize(1,0,2)).build());

        register(context, OVERWORLD_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(overWorldBlackOpalOres,9));
        register(context, END_BLACK_OPAL_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceAbles, BlockRegister.ROCK_BLOCK.get().defaultBlockState(), 9));
        register(context, NETHER_BLACK_OPEL_ORE_KEY, Feature.ORE, new OreConfiguration(netherRackReplaceAbles, BlockRegister.ROCK_BLOCK.get().defaultBlockState(), 9));

    }

    //将这种矿石生成类型进行注册
    public static ResourceKey<ConfiguredFeature<?,?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Reference.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                                      ResourceKey<ConfiguredFeature<? ,?>> key,
                                                                                                      F feature,
                                                                                                      FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

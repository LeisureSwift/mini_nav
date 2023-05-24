package com.leisure.miniuav.world.feature;

import com.google.common.base.Suppliers;
import com.leisure.miniuav.block.BlockRegister;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;


public class ModConfiguredFeatures {
//    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
//            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, Reference.MOD_ID);
//
//    //将我们第一步的两种矿石分别填入其中
//    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_FANTOM_ORES = Suppliers.memoize(() -> List.of(
//            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockRegister.FANTOM_ORE.get().defaultBlockState()), //普通岩层
//            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockRegister.DEEPSLATE_FANTOM_ORE.get().defaultBlockState()))); //深板岩层
//
//    //将这种矿石生成类型进行注册
//    public static final RegistryObject<ConfiguredFeature<?, ?>> FANTOM_ORES = CONFIGURED_FEATURES.register("fantom_ore",
//            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_FANTOM_ORES.get(),7)));
//
//    public static void register(IEventBus eventBus) {
//        CONFIGURED_FEATURES.register(eventBus);
//    }
}

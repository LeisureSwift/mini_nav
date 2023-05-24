package com.leisure.miniuav.world.feature;

import com.leisure.miniuav.utils.Reference;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.leisure.miniuav.world.feature.ModOrePlacement.commonOrePlacement;
//https://forums.minecraftforge.net/topic/110798-1182-crash-when-adding-custom-tree-to-biome/
public class ModPlacedFeatures {
//    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
//            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Reference.MOD_ID);
//
//    //矿石摆放
//    public static final RegistryObject<PlacedFeature> FANTOM_ORE_PLACED = PLACED_FEATURES.register("fantom_ore_placed",
//            () -> new PlacedFeature(ModConfiguredFeatures.FANTOM_ORES.getHolder().get(),
//                    commonOrePlacement(7, //每个区块生成多少矿石
//                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-60), VerticalAnchor.aboveBottom(60))))); //-60，60分别指矿石生成高度范围介于[-60,60]
//
//
//
//    public static void register(IEventBus eventBus) {
//        PLACED_FEATURES.register(eventBus);
//    }
}

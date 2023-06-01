package com.leisure.miniuav.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import com.leisure.miniuav.init.ItemInit;
import com.leisure.miniuav.init.BlockInit;

public class UavTab {

    public CreativeModeTab output(CreativeModeTab.Builder builder) {
        return builder.title(Component.translatable("itemGroup.uav_tab"))
                .icon(() -> new ItemStack(ItemInit.LEI.get()))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ItemInit.LEI.get());
                    output.accept(ItemInit.FRUIT.get());
                    output.accept(ItemInit.EFFECTIVE_FRUIT.get());
                    output.accept(ItemInit.FUEL.get());
                    output.accept(ItemInit.TELEPORT_STAFF.get());
                    output.accept(ItemInit.PINK_SWORD.get());
                    output.accept(ItemInit.PINK_PICKAXE.get());
                    output.accept(ItemInit.PINK_AXE.get());
                    output.accept(ItemInit.PINK_SHOVEL.get());
                    output.accept(ItemInit.PINK_HOE.get());
                    output.accept(ItemInit.PINK_HELMET.get());
                    output.accept(ItemInit.PINK_CHESTPLATE.get());
                    output.accept(ItemInit.PINK_LEGGINGS.get());
                    output.accept(ItemInit.PINK_BOOTS.get());
                    output.accept(ItemInit.EXPLOSIVE_ARROW.get());
                    output.accept(BlockInit.ROCK_BLOCK.get());
                    output.accept(BlockInit.EXAMPLE_METAL.get());
                    output.accept(BlockInit.FANTOM_ORE.get());
                    output.accept(BlockInit.DEEPSLATE_FANTOM_ORE.get());
                    output.accept(BlockInit.NETHER_FANTOM_ORE.get());
                    output.accept(BlockInit.END_FANTOM_ORE.get());
                    output.accept(BlockInit.MOB_SLAYER.get());
                }).build();
    }
}

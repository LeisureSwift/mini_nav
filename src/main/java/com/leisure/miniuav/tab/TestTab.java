package com.leisure.miniuav.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import com.leisure.miniuav.init.ItemInit;
import com.leisure.miniuav.init.BlockInit;

public class TestTab {

    public CreativeModeTab output(CreativeModeTab.Builder builder) {
        return builder.title(Component.translatable("itemGroup.uav_tab"))
                .icon(() -> new ItemStack(ItemInit.LEI.get()))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ItemInit.LEI.get());
                    output.accept(BlockInit.ROCK_BLOCK.get());
//                    output.accept(BlockRegister.EXAMPLE_BLOCK.get());
                }).build();
    }
}

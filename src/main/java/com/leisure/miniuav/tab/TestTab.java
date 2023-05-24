package com.leisure.miniuav.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import com.leisure.miniuav.item.ItemRegister;
import com.leisure.miniuav.block.BlockRegister;

public class TestTab {

    public CreativeModeTab output(CreativeModeTab.Builder builder) {
        return builder.title(Component.translatable("itemGroup.uav_tab"))
                .icon(() -> new ItemStack(ItemRegister.LEI.get()))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ItemRegister.LEI.get());
                    output.accept(BlockRegister.ROCK_BLOCK.get());
//                    output.accept(BlockRegister.EXAMPLE_BLOCK.get());
                }).build();
    }
}

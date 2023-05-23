package com.leisure.miniuav.tab;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import com.leisure.miniuav.item.ItemRegister;

public class TestTab {

    public CreativeModeTab output(CreativeModeTab.Builder builder) {
        return builder.title(Component.translatable("itemGroup."))
                .icon(() -> new ItemStack(ItemRegister.LEI.get()))
                .displayItems((featureFlags, output, hasOp) ->
                        output.accept(ItemRegister.LEI.get())
                ).build();
    }
}

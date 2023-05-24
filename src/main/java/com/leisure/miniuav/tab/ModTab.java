package com.leisure.miniuav.tab;

import com.leisure.miniuav.utils.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModTab {

    public static CreativeModeTab TEST_TAB;

    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {
        TEST_TAB = event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "uav_tab"), builder -> new TestTab().output(builder));
    }
}

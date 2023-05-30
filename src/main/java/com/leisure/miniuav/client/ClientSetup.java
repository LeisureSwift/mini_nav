package com.leisure.miniuav.client;

import com.leisure.miniuav.init.EntityInit;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import com.leisure.miniuav.client.render.ExplosiveArrowRenderer;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityInit.EXPLOSIVE_ARROW.get(), ExplosiveArrowRenderer::new);
    }
}

package com.leisure.miniuav.init;

import com.leisure.miniuav.utils.Reference;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.leisure.miniuav.entity.enchants.BridgeEnchantment;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Reference.MOD_ID);

    public static final RegistryObject<Enchantment> BRIDGE = ENCHANTMENTS.register("bridge", BridgeEnchantment::new);
}
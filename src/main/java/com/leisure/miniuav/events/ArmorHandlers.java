package com.leisure.miniuav.events;

import com.leisure.miniuav.utils.IDamageHandlingArmor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorHandlers {
    /**
     * LivingDamageEvent监听能够穿过盔甲防御伤害到穿戴者的事件
     * 如果想对所有攻击生效，需要使用LivingAttackEvent
     * @param event
     */
    @SubscribeEvent
    public static void armorAttackHandler(LivingDamageEvent event){
        for (ItemStack armor : event.getEntity().getArmorSlots()){
            if (armor.getItem() instanceof IDamageHandlingArmor){
                float newDamage = ((IDamageHandlingArmor)armor.getItem()).onDamaged(event.getEntity(), armor.getEquipmentSlot(), event.getSource(), event.getAmount());
                event.setAmount(newDamage);
            }
        }
    }
}

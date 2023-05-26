package com.leisure.miniuav.utils;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public interface IDamageHandlingArmor {

    //受到伤害时触发事件
    default float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount){
        return amount;
    }
}

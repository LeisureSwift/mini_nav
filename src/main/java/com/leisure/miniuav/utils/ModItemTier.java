package com.leisure.miniuav.utils;

import com.leisure.miniuav.item.ItemRegister;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * 工具枚举类
 */
public enum ModItemTier implements Tier {
    PINK(3, 3000, 10.0F, 5.0F, 5, () -> {
        return Ingredient.of(ItemRegister.LEI.get());
    }),
    EXAMPLE(1, 1, 1.0F, 1.0F, 1, () -> {
        return Ingredient.of(Items.STICK);
    });

    //挖掘等级 0:wood，1:stone，2:iron，3:diamond，4:netherite
    private final int level;
    //耐久度
    private final int uses;
    //挖掘速度
    private final float speed;
    //基础伤害，会在注册时根据具体的工具类型附加额外伤害
    private final float damage;
    //附魔值
    private final int enchantmentValue;
    //可通过铁站用于修补该工具的物品
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModItemTier(int level, int durability, float miningSpeed, float damage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.level = level;
        this.uses = durability;
        this.speed = miningSpeed;
        this.damage = damage;
        this.enchantmentValue = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
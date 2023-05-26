package com.leisure.miniuav.utils;

import com.leisure.miniuav.item.ItemRegister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

/**
 * 盔甲金属类
 */
public enum ModArmorMaterial implements ArmorMaterial {
    PINK(Reference.MOD_ID + ":pink", 20, new int[]{4, 7, 9, 4}, 50, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.1F, () -> {
        return Ingredient.of(ItemRegister.LEI.get());
    });

    //各部件的比值 靴子，腿甲，胸甲，头盔
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    //名称 必须以modid:开头
    private final String name;
    //整体耐久值 乘比值后为各部位耐久值
    private final int durabilityMultiplier;
    //各部位防御值
    private final int[] slotProtections;
    //附魔值
    private final int enchantmentValue;
    //装备时音效事件
    private final SoundEvent sound;
    //坚硬度 在材料为diamond、netherite时提供遭受强大攻击时的额外防御力
    private final float toughness;
    //击退抵抗值 在材料为netherite时生效 当所有部位该值加和为1时即不受击退效果
    private final float knockbackResistance;
    //可通过铁站用于修补该盔甲的物品
    private final LazyLoadedValue<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durability;
        this.slotProtections = protection;
        this.enchantmentValue = enchantability;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot slot) {
        return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.slotProtections[slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}

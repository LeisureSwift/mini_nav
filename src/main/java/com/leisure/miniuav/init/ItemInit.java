package com.leisure.miniuav.init;

import com.leisure.miniuav.entity.item.FuelItem;
import com.leisure.miniuav.entity.item.TeleportStaff;
import com.leisure.miniuav.utils.ModArmorMaterial;
import com.leisure.miniuav.utils.ModItemTier;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    //我们的第一个物品
    public static final RegistryObject<Item> LEI = ITEMS.register("lei",
            () -> new Item(new Item.Properties()));

    //食品示例 food
    // nutrition 恢复饥饿值
    public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));

    //食用效果示例 effect 有50%几率获得持续10s的火焰抵抗1效果
    //MobEffectInstance(MobEffect p_19518_, int p_19519_, int p_19520_)
    //p_19518_ 效果
    //p_19519_ 持续时间(tick) 1s = 20 ticks
    //p_19520_ 效果等级 0 = level 1
    //effect(java.util.function.Supplier<MobEffectInstance> effectIn, float probability)
    //probability 成功生效的概率 0-1
    public static final RegistryObject<Item> EFFECTIVE_FRUIT = ITEMS.register("effective_fruit",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 0.5F).build())));

    //燃料示例 自定义燃料实体 新增属性燃烧时间
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties(), 3200));

    //触发事件示例 自定义传送器实体 耐久度durability
    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().durability(50)));

    //注册使用自定义金属生成的基础工具 每种工具对应不同的Item类
    //第二个参数 附加伤害
    //第三个参数 附加攻击速度 加上默认4 可能为负数实际取绝对值
    //可以自定义工具类
    public static final RegistryObject<Item> PINK_SWORD = ITEMS.register("pink_sword",
            () -> new SwordItem(ModItemTier.PINK, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> PINK_PICKAXE = ITEMS.register("pink_pickaxe",
            () -> new PickaxeItem(ModItemTier.PINK, 1, -1.0F, new Item.Properties()));

    public static final RegistryObject<Item> PINK_AXE = ITEMS.register("pink_axe",
            () -> new AxeItem(ModItemTier.PINK, 6, -3.4F, new Item.Properties()));

    public static final RegistryObject<Item> PINK_SHOVEL = ITEMS.register("pink_shovel",
            () -> new ShovelItem(ModItemTier.PINK, 1, -1.0F, new Item.Properties()));

    public static final RegistryObject<Item> PINK_HOE = ITEMS.register("pink_hoe",
            () -> new HoeItem(ModItemTier.PINK, 0, -1.0F, new Item.Properties()));

    //注册使用自定义盔甲
    public static final RegistryObject<Item> PINK_HELMET = ITEMS.register("pink_helmet",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.HEAD, new Item.Properties()));

    public static final RegistryObject<Item> PINK_CHESTPLATE = ITEMS.register("pink_chestplate",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.CHEST, new Item.Properties()));

    public static final RegistryObject<Item> PINK_LEGGINGS = ITEMS.register("pink_leggings",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.LEGS, new Item.Properties()));

    public static final RegistryObject<Item> PINK_BOOTS = ITEMS.register("pink_boots",
            () -> new ArmorItem(ModArmorMaterial.PINK, EquipmentSlot.FEET, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}

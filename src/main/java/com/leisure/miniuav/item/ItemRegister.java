package com.leisure.miniuav.item;

import com.leisure.miniuav.entity.FuelItem;
import com.leisure.miniuav.entity.TeleportStaff;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    //我们的第一个物品
    public static final RegistryObject<Item> LEI = ITEMS.register("lei",
            () -> new Item(new Item.Properties()));

    //食品示例
    public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));

    //食用效果示例 effect
    public static final RegistryObject<Item> EFFECTIVE_FRUIT = ITEMS.register("effective_fruit",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 0.5F).build())));

    //燃料示例
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties(), 3200));

    //触发事件示例 传送器 耐久度durability
    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff",
            () -> new TeleportStaff(new Item.Properties().durability(50)));

}

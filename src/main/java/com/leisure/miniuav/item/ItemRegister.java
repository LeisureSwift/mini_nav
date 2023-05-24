package com.leisure.miniuav.item;

import com.leisure.miniuav.utils.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    //我们的第一个物品
    public static final RegistryObject<Item> LEI = ITEMS.register("lei",
            () -> new Item(new Item.Properties()));
}

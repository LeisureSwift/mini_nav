package com.leisure.miniuav.entity.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class FuelItem extends Item {
    //燃烧时间(tick)
    private final int burnTicks;
    public FuelItem(Properties properties, int burnTimeInTicks) {
        super(properties);
        this.burnTicks = burnTimeInTicks;
    }
    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTicks;
    }
}

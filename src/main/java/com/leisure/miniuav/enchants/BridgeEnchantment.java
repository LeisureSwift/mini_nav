package com.leisure.miniuav.enchants;

import com.leisure.miniuav.init.EnchantmentInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BridgeEnchantment extends Enchantment {
    // Enchantment.Rarity 稀有度，被刷新到的概率 COMMON, UNCOMMON, RARE or VERY_RARE
    // EnchantmentCategory 附魔类型，可附魔的物品类型
    // EquipmentSlot 可被附魔的装备槽
    public BridgeEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    /**
     * 可附魔等级
     * @return
     */
    @Override
    public int getMaxLevel() {
        return 3;
    }

    /**
     * 与其他附魔的不可兼容性
     * @param other
     * @return
     */
    @Override
    protected boolean checkCompatibility(Enchantment other) {
        return super.checkCompatibility(other) && other != Enchantments.FROST_WALKER;
    }

    /**
     * 手持或穿戴含该附魔item时攻击其他生物时触发，含多件物品时多次触发
     * @param attacker
     * @param target
     * @param level
     */
    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        target.setRemainingFireTicks(40);
    }

    /**
     * 手持或穿戴含该附魔item时被攻击时触发，含多件物品时多次触发
     * @param target
     * @param attacker
     * @param p_151367_3_
     */
    @Override
    public void doPostHurt(LivingEntity target, Entity attacker, int p_151367_3_) {
        target.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100));
    }

    /**
     * 其他可重写方法
     * isTreasureOnly: like mending and frost walker. Makes it not show up in the enchantment table or trades.
     * isTradeable: will it show up in librarian trades?
     * isAllowedOnBooks: will the enchantment table be able to put this on books?
     * isCurse: should it be considered a curse? True makes the text on an item red instead of grey. Makes the grindstone not able to remove it.
     */

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class BridgeBuildingHandler {
        @SubscribeEvent
        public static void buildBridge(TickEvent.PlayerTickEvent event){
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.BRIDGE.get(), event.player);

            // your code here
            if (level > 0 && event.player.isShiftKeyDown()){
                BlockState state = event.player.level.getBlockState(event.player.blockPosition().below());
                if (!state.isAir()) return;
                event.player.level.setBlockAndUpdate(event.player.blockPosition().below(), Blocks.SLIME_BLOCK.defaultBlockState());
            }
        }
    }
}

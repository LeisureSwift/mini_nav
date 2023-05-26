package com.leisure.miniuav.entity.item;

import com.leisure.miniuav.item.ItemRegister;
import com.leisure.miniuav.utils.IDamageHandlingArmor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.world.entity.EquipmentSlot.FEET;
import static net.minecraft.world.entity.EquipmentSlot.LEGS;
import static net.minecraft.world.item.Items.CHEST;

/**
 * 自定义盔甲示例
 */
public class FlamingArmorItem extends ArmorItem implements IDamageHandlingArmor {
    public FlamingArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    /**
     * 穿戴事件
     * 实例为穿戴时产生10s的火焰抵抗，但因为穿戴时每个tick都会触发该事件，所以相当于是永恒火焰抵抗效果
     *
     * @param stack
     * @param world
     * @param player
     */
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        //判断是否穿戴全套
        boolean fullSet = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemRegister.PINK_HELMET.get() &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ItemRegister.PINK_CHESTPLATE.get() &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ItemRegister.PINK_LEGGINGS.get() &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == ItemRegister.PINK_BOOTS.get();
        if (fullSet) {
            // do something cool here
        }
        if (!world.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200));
        }
    }

    /**
     * 受到伤害时触发事件
     * 示例为如果伤害来源是生物实体，对该生物造成4s着火状态，并且将穿戴者所受伤害的一半以着火的形式返还给伤害来源，并减免穿戴者受到的一半伤害
     * 否则直接受到伤害，如坠落伤害
     *
     * @param entity
     * @param slot
     * @param source
     * @param amount
     * @return
     */
    @Override
    public float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount) {
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity) {
            attacker.hurt(DamageSource.ON_FIRE, amount / 2);
            attacker.setSecondsOnFire(4);
            return amount / 2;
        } else {
            return amount;
        }
    }

    /**
     * 使Piglins忽视穿戴该盔甲的人
     *
     * @param stack
     * @param wearer The entity wearing this ItemStack
     * @return
     */
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return true;
    }
}

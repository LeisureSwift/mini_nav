package com.leisure.miniuav.entity;

import com.leisure.miniuav.utils.KeyboardHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class TeleportStaff extends Item {
    public TeleportStaff(Properties properties) {
        super(properties);
    }
    //右键单击事件
    //示例写的是手持该物品右击 玩家向前闪现
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
//        BlockHitResult ray = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);
//        BlockPos lookPos = ray.getBlockPos();

        BlockHitResult ray = rayTrace(world, player, ClipContext.Fluid.NONE);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
        player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());

        //添加触发cd
        player.getCooldowns().addCooldown(this, 60);
        //重置跌落高度 减少掉落伤害
        player.fallDistance = 0F;
        //添加触发音效
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        //每次使用减少耐久度
        ItemStack stack = player.getItemInHand(hand);
        stack.setDamageValue(stack.getDamageValue() + 1);
        if (stack.getDamageValue() >= stack.getMaxDamage()) stack.setCount(0);
        return super.use(world, player, hand);
    }

    /**
     * 鼠标悬置于该物品上时触发
     * 示例为当鼠标悬置且摁住shift时显示提示信息
     * @param stack
     * @param worldIn
     * @param tooltip
     * @param flagIn
     */
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
//        tooltip.add(Component.literal("teleports you where you're looking"));
        if (KeyboardHelper.isHoldingShift()){
            tooltip.add(Component.literal("teleports you where you're looking"));
        }
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    /**
     * 其他触发事件
     * 右键点击方块事件
     * useOn: called when right clicked targeting a block
     * 右键点击活物实体事件
     * interactLivingEntity: called when right clicked targeting an entity
     * 手持物品打击货物实体事件
     * hurtEnemy: called when the player hits an entity while holding the item
     * onCraftedBy: called when taken out of a crafting slot.
     * inventoryTick: called once every tick (20 times per second) while it is in someone's inventory.
     * isValidRepairItem: can the second item be used to repair the first item in an anvil
     */

    /**
     * Practice 课后练习
     * Make an item that replaces any block you right click with dirt
     * hint: world.setBlock(pos, Blocks.VANILLA_NAME.defaultBlockState());
     * Make an item that gives poison to any entity you right click
     * hint: entity.addEffect(new MobEffectInstance(MobEffects.VANILLA_NAME, duration, amplifier));
     * Make an item that propels the player in the direction they're looking
     * hint: player.setDeltaMovement(x, y, z);
     * Make an item that places a torch automatically while in the inventory of a player in darkness
     * hint: Math.max(world.getBrightness(LightType.BLOCK, pos), world.getBrightness(LightType.SKY, pos)) > 7
     */


    /**
     * 重写getPlayerPOVHitResult方法 增加传送距离range
     * @param world
     * @param player
     * @param fluidMode
     * @return
     */
    protected static BlockHitResult rayTrace(Level world, Player player, ClipContext.Fluid fluidMode) {
        //传送距离
        double range = 15;

        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vector3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vector3d1 = vector3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return world.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, player));
    }
}

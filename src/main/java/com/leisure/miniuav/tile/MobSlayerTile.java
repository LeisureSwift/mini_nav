package com.leisure.miniuav.tile;

import com.leisure.miniuav.init.TileEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MobSlayerTile extends BlockEntity {

    int timer = 0;
    boolean isActive = true;
    final int RANGE = 5;

    public MobSlayerTile(BlockPos pos, BlockState state) {
        super(TileEntityInit.MOB_SLAYER.get(), pos, state);
    }

    /**
     * 每tick触发一次的方法
     * 示例为当方块为激活状态时，每隔1s(20 ticks)调用一次范围伤害方法
     * @param level
     * @param pos
     * @param state
     * @param be
     * @param <T>
     */
    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        MobSlayerTile tile = (MobSlayerTile) be;

        if (!level.isClientSide() && tile.isActive){
            tile.timer++;
            if (tile.timer > 20){
                tile.timer = 0;

                // only do this once per second
                tile.hurtMobs();
            }
        }
    }

    /**
     * 对方块指定范围三维空间内的非玩家生物造成伤害
     */
    private void hurtMobs() {
        BlockPos topCorner = this.worldPosition.offset(RANGE, RANGE, RANGE);
        BlockPos bottomCorner = this.worldPosition.offset(-RANGE, -RANGE, -RANGE);
        AABB box = new AABB(topCorner, bottomCorner);

        //第一个参数为白名单
        List<Entity> entities = this.level.getEntities(null, box);
        for (Entity target : entities){
            if (target instanceof LivingEntity && !(target instanceof Player)){
                target.hurt(DamageSource.MAGIC, 2);
            }
        }
    }

    /**
     * 右击触发事件
     * @param state
     * @param world
     * @param pos
     * @param player
     * @param hand
     * @param hit
     * @return
     */
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide() && hand == InteractionHand.MAIN_HAND){
            BlockEntity tile = world.getBlockEntity(pos);
            if (tile instanceof MobSlayerTile){
                ((MobSlayerTile) tile).toggle();

                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    /**
     * 切换激活状态
     */
    public void toggle(){
        this.isActive = !this.isActive;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("active", this.isActive);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.isActive = nbt.getBoolean("active");
    }

//    @Override
//    public ClientboundBlockEntityDataPacket getUpdatePacket(){
//        CompoundTag nbtTag = new CompoundTag();
//        // save data to nbt
//        return ClientboundBlockEntityDataPacket.create(this.worldPosition, -1, nbtTag);
//    }
//
//    @Override
//    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt){
//        CompoundTag tag = pkt.getTag();
//        // read data
//    }
}
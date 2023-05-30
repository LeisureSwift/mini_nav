package com.leisure.miniuav.entity.projectile;

import com.leisure.miniuav.init.ItemInit;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveArrowEntity extends AbstractArrow {
    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, double x, double y, double z, Level world) {
        super(entityType, x, y, z, world);
    }

    public ExplosiveArrowEntity(EntityType<ExplosiveArrowEntity> entityType, LivingEntity shooter, Level world) {
        super(entityType, shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemInit.EXPLOSIVE_ARROW.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult ray) {
        super.onHitEntity(ray);
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Level.ExplosionInteraction.BLOCK);
    }

    @Override
    protected void onHitBlock(BlockHitResult ray) {
        super.onHitBlock(ray);
        BlockState theBlockYouHit = this.level.getBlockState(ray.getBlockPos());
    }

    @Override
    protected void tickDespawn() {
        if (this.inGroundTime > 60){
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 4.0f, true, Level.ExplosionInteraction.BLOCK);
            this.discard();
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


}

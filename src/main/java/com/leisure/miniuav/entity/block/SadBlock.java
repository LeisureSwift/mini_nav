package com.leisure.miniuav.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.IPlantable;

public class SadBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public SadBlock(Block.Properties properties) {
        super(properties);
        //设置放置时默认面向玩家方向为北面
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /**
     * 反方向放置
     *
     * @param context
     * @return
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /**
     * 右击事件 会触发两次 一次为副手一次为主手
     * 示例为当手持火药右击方块时，方块爆炸
     *
     * @param state
     * @param world
     * @param pos
     * @param player
     * @param hand   当前手 InteractionHand.MAIN_HAND  InteractionHand.*OFF_HAND*
     * @param hit
     * @return
     */
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);

        if (!world.isClientSide() && held.getItem() == Items.GUNPOWDER) {
            //explode(@Nullable Entity p_255682_, double p_255803_, double p_256403_, double p_256538_, float p_255674_, boolean p_256634_, Level.ExplosionInteraction p_256111_)
            //p_255682_ 爆炸方法需要指定一个实体来为本次爆炸负责，以便描述死亡信息或其他用 实体也可以为null
            //p_255803_ p_256403_ p_256538_ 指定(x,y,z)爆炸坐标
            //p_255674_ 爆炸半径4
            //p_256634_ 是否生成火焰
            //p_256111_ 爆炸级别
            world.explode(player, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Level.ExplosionInteraction.BLOCK);
            held.shrink(1);
            return InteractionResult.CONSUME;
            /**
             * 四种执行结果
             * SUCCESS: Use this when your item/block/entity has met all the requirements for the interaction, and you have completed everything that needs to be done. This will prevent any further action from being taken with the interaction.
             * CONSUME: You can use this when you meet the same conditions as SUCCESS, but additionally are consuming an item/block as part of the interaction. This InteractionResult isn't explicitly checked as of 1.15.2, and so using this is mostly optional.
             * PASS: Use this when you do not meet any of the requirements for the interaction (not holding the right item, etc.). This signals for the game to attempt to interact with the other hand, and also try to use the item's interaction methods and other similar methods.
             * FAIL: Use this when you have met the minimum requirements for the interaction (holding the right block, etc.), but fail to meet further criteria. This should be used when you want to indicate that the player intended to interact with your object, but failed to meet further criteria. This will prevent any further action from being taken with the interaction.
             */
        }
        //当方块的resistance过高是爆炸不会发生
        return super.use(state, world, pos, player, hand, hit);
    }

    /**
     * 该物品被爆炸摧毁时触发的事件
     *
     * @param world
     * @param pos
     * @param explosion
     */
    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) {
        //被爆炸摧毁后在方块所在地引起一场爆炸 连续爆炸
        world.explode(null, pos.getX(), pos.getY(), pos.getZ(), 4.0F, true, Level.ExplosionInteraction.BLOCK);
        super.wasExploded(world, pos, explosion);
    }

    /**
     * 是否可以种植指定作物
     *
     * @param state     The Current state
     * @param world     The current level
     * @param pos
     * @param facing    The direction relative to the given position the plant wants to be, typically its UP
     * @param plantable The plant that wants to check
     * @return
     */
    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.relative(facing)).getBlock();

        if (plant == Blocks.CACTUS) {
            //可以种仙人掌
            return true;
        } else {
            //其他都不可以
            return super.canSustainPlant(state, world, pos, facing, plantable);
        }
    }

    /**
     * 接收时间变化 可以通过Block.Properties().randomTicks()代替
     *
     * @param state
     * @return
     */
    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    /**
     * 时间变化触发方法
     * 当时间变化时，如果上面一个方块是空气，将会替换为仙人掌
     *
     * @param state
     * @param world
     * @param pos
     * @param rand
     */
    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand) {
        BlockState above = world.getBlockState(pos.above());
        if (above.isAir()) {
            world.setBlockAndUpdate(pos.above(), Blocks.CACTUS.defaultBlockState());
        }
    }

    /**
     * 部分其他方法
     * playerDestroy (used by beehives to release angry bees when you break them)
     * onRemove: called when the block is destroyed for any reason
     * handleRain: 1/16 chance to be called each tick while its raining (used by cauldrons to fill with water)
     * fallOn: called when an entity falls on the block. Deals the fall damage by calling Entity.causeFallDamage(distance, damageMultiplier). (used by farm land to break the crop)
     * onPlace
     * getExpDrop returns the number of experience points to give when broken (like some vanilla ores)
     */
}

package com.leisure.miniuav.init;

import com.leisure.miniuav.entity.blockentity.MobSlayerBlock;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> ROCK_BLOCK = registerBlock("example_rock",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    //strength    影响破坏时间和抵抗爆炸的能力
    //sound       交互音效
    //requiresCorrectToolForDrops  是否需要指定工具开采才能生成战利品
    //lightLevel  发光能力 [1,16]
    //friction    摩檫力 如冰
    //speedFactor ？ 如灵魂沙
    //jumpFactor  跳跃系数 如蜂蜜
    public static final RegistryObject<Block> EXAMPLE_METAL = registerBlock("example_metal",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(5f, 6f)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().lightLevel((state) -> 15)));

    //普通矿石示例，UniformInt.of(a,b)意思是该矿石挖掘后奖励多少经验，范围在[a,b]
    public static final RegistryObject<Block> FANTOM_ORE = registerBlock("fantom_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3, 10)));

    //深板岩矿石示例  1.19版本新增深板岩层矿石
    public static final RegistryObject<Block> DEEPSLATE_FANTOM_ORE = registerBlock("deepslate_fantom_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f).requiresCorrectToolForDrops()));

    //下界矿石示例
    public static final RegistryObject<Block> NETHER_FANTOM_ORE = registerBlock("nether_fantom_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f).requiresCorrectToolForDrops()));

    //末地矿石示例
    public static final RegistryObject<Block> END_FANTOM_ORE = registerBlock("end_fantom_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f).requiresCorrectToolForDrops()));

    //方块实体示例 杀死周围的怪物
    public static final RegistryObject<Block> MOB_SLAYER = registerBlock("mob_slayer",
            () -> new MobSlayerBlock(Block.Properties.copy(Blocks.IRON_BLOCK)));

    //方块的几个注册方法
    //只注册方块
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //注册BlockItem
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

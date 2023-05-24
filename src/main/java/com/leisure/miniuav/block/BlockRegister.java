package com.leisure.miniuav.block;

import com.leisure.miniuav.item.ItemRegister;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    public static final RegistryObject<Block> ROCK_BLOCK = BLOCKS.register("rock",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));

    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_PURPLE).strength(5f, 6f)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops()));

    //普通矿石，UniformInt.of(a,b)意思是该矿石挖掘后奖励多少经验，范围在[a,b]
    public static final RegistryObject<Block> FANTOM_ORE = registerBlock("fantom_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3, 10)));

    //深板岩矿石  1.19版本新增深板岩层矿石
    public static final RegistryObject<Block> DEEPSLATE_FANTOM_ORE = registerBlock("deepslate_fantom_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(7f).requiresCorrectToolForDrops()));

    //方块的几个注册方法
    //只注册方块
    private static <T extends Block> RegistryObject<T> registerBlock(final String name,
                                                                     final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }
    //既注册方块又注册物品
    private static <T extends Block> RegistryObject<T> register(final String name,
                                                                     final Supplier<? extends T> block) {
        RegistryObject<T> obj = registerBlock(name, block);
        ItemRegister.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
        return obj;
    }

}

package com.leisure.miniuav.init;

import com.leisure.miniuav.entity.tile.MobSlayerTile;
import com.leisure.miniuav.utils.Reference;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TileEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<BlockEntityType<MobSlayerTile>> MOB_SLAYER = TILE_ENTITY_TYPES.register("mob_slayer",
            () -> BlockEntityType.Builder.of(MobSlayerTile::new, BlockInit.MOB_SLAYER.get()).build(null));
}

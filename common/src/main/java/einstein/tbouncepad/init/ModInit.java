package einstein.tbouncepad.init;

import einstein.tbouncepad.BouncePadBlock;
import einstein.tbouncepad.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;
import java.util.function.Supplier;

import static einstein.tbouncepad.TinkersBouncePad.loc;

public class ModInit {

    public static final Supplier<Block> BOUNCE_PAD = register("bounce_pad", BouncePadBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn((state, getter, pos, entityType) -> false));
    public static final Supplier<Block> SKYSLIME_BOUNCE_PAD = register("skyslime_bounce_pad", BouncePadBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BOUNCE_PAD.get()).mapColor(MapColor.DIAMOND));
    public static final Supplier<Block> ENDERSLIME_BOUNCE_PAD = register("enderslime_bounce_pad", BouncePadBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BOUNCE_PAD.get()).mapColor(MapColor.COLOR_PURPLE));
    public static final Supplier<Block> ICHOR_BOUNCE_PAD = register("ichor_bounce_pad", BouncePadBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(BOUNCE_PAD.get()).mapColor(MapColor.COLOR_ORANGE));

    public static void init() {
    }

    private static <T extends Block> Supplier<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> blockProperties) {
        ResourceLocation id = loc(name);
        Supplier<T> instance = Services.REGISTRY.registerBlock(name, () -> block.apply(blockProperties.get()
                .setId(ResourceKey.create(Registries.BLOCK, id))));
        Services.REGISTRY.registerItem(name, () -> new BlockItem(instance.get(), new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, id)).useBlockDescriptionPrefix()));
        return instance;
    }
}

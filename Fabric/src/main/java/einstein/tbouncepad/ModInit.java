package einstein.tbouncepad;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModInit {

    public static final Block BOUNCE_PAD = register("bounce_pad", new BouncePad(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn(Blocks::never)));
    public static final Block SKYSLIME_BOUNCE_PAD = register("skyslime_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)));
    public static final Block ENDERSLIME_BOUNCE_PAD = register("enderslime_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)));
    public static final Block ICHOR_BOUNCE_PAD = register("ichor_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)));

    private static Block register(String name, Block block) {
        Block instance = Registry.register(BuiltInRegistries.BLOCK, TinkersBouncePad.loc(name), block);
        Registry.register(BuiltInRegistries.ITEM, TinkersBouncePad.loc(name), new BlockItem(instance, new Item.Properties()));
        return instance;
    }

    public static void register() {
        TinkersBouncePad.LOGGER.debug("Loading items and blocks");
    }
}

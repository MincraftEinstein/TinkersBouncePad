package einstein.tbouncepad;

import einstein.tbouncepad.loader.Services;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class ModInit {

    public static final Block BOUNCE_PAD = register("bounce_pad", new BouncePad(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn(Blocks::never)), true);
    public static final Block SKYSLIME_BOUNCE_PAD = register("skyslime_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));
    public static final Block ENDERSLIME_BOUNCE_PAD = register("enderslime_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));
    public static final Block ICHOR_BOUNCE_PAD = register("ichor_bounce_pad", new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD)), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));

    private static Block register(String name, Block block, boolean hasTab) {
        final Block instance = Registry.register(Registry.BLOCK, TinkersBouncePad.loc(name), block);
        final Item.Properties props = new Item.Properties();

        if (hasTab) {
            props.tab(CreativeModeTab.TAB_TRANSPORTATION);
        }

        Registry.register(Registry.ITEM, TinkersBouncePad.loc(name), new BlockItem(instance, props));
        return instance;
    }

    public static void register() {
        TinkersBouncePad.LOGGER.debug("Loading items and blocks");
    }
}

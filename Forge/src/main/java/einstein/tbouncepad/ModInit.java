package einstein.tbouncepad;

import einstein.tbouncepad.loader.Services;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersBouncePad.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinkersBouncePad.MOD_ID);

    public static final RegistryObject<Block> BOUNCE_PAD = register("bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn(Blocks::never)), true);
    public static final RegistryObject<Block> SKYSLIME_BOUNCE_PAD = register("skyslime_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));
    public static final RegistryObject<Block> ENDERSLIME_BOUNCE_PAD = register("enderslime_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));
    public static final RegistryObject<Block> ICHOR_BOUNCE_PAD = register("ichor_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())), Services.LOADER.isModLoaded(TinkersBouncePad.TCON_MOD_ID));

    private static RegistryObject<Block> register(final String name, final Supplier<Block> block, boolean hasTab) {
        final RegistryObject<Block> instance = BLOCKS.register(name, block);
        final Item.Properties props = new Item.Properties();

        if (hasTab) {
            props.tab(CreativeModeTab.TAB_TRANSPORTATION);
        }

        ITEMS.register(name, () -> new BlockItem(instance.get(), props));
        return instance;
    }
}

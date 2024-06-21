package einstein.tbouncepad.platform;

import einstein.tbouncepad.platform.services.RegistryHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static einstein.tbouncepad.TinkersBouncePad.MOD_ID;

public class NeoForgeRegistryHelper implements RegistryHelper {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MOD_ID);

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> type) {
        Supplier<T> block = BLOCKS.register(name, type);
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}

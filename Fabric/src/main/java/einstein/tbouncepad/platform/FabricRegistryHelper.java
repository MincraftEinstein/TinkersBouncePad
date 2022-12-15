package einstein.tbouncepad.platform;

import einstein.tbouncepad.TinkersBouncePad;
import einstein.tbouncepad.platform.services.RegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class FabricRegistryHelper implements RegistryHelper {

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> type) {
        T block = Registry.register(BuiltInRegistries.BLOCK, TinkersBouncePad.loc(name), type.get());
        Registry.register(BuiltInRegistries.ITEM, TinkersBouncePad.loc(name), new BlockItem(block, new Item.Properties()));
        return () -> block;
    }
}

package einstein.tbouncepad.platform.services;

import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public interface RegistryHelper {

    <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> type);
}

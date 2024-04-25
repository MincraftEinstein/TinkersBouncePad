package einstein.tbouncepad;

import einstein.tbouncepad.platform.Services;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class ModInit {

    public static final Supplier<Block> BOUNCE_PAD = Services.REGISTRY.registerBlock("bounce_pad", () -> new BouncePadBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn((state, getter, pos, entityType) -> false)));
    public static final Supplier<Block> SKYSLIME_BOUNCE_PAD = Services.REGISTRY.registerBlock("skyslime_bounce_pad", () -> new BouncePadBlock(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())));
    public static final Supplier<Block> ENDERSLIME_BOUNCE_PAD = Services.REGISTRY.registerBlock("enderslime_bounce_pad", () -> new BouncePadBlock(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())));
    public static final Supplier<Block> ICHOR_BOUNCE_PAD = Services.REGISTRY.registerBlock("ichor_bounce_pad", () -> new BouncePadBlock(BlockBehaviour.Properties.copy(BOUNCE_PAD.get())));

    public static void init() {
    }
}

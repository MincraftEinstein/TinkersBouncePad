package einstein.tbouncepad;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TinkersBouncePad.MOD_ID)
public class TinkersBouncePadForge {

    public TinkersBouncePadForge() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TinkersBouncePad.init();
        ModInit.ITEMS.register(modEventBus);
        ModInit.BLOCKS.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TinkersBouncePad.buildConfigs());
    }
}
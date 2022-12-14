package einstein.tbouncepad;

import einstein.tbouncepad.platform.Services;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.CreativeModeTabEvent;
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
        modEventBus.addListener(this::onBuildContents);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TinkersBouncePad.buildConfigs());
    }

    void onBuildContents(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModInit.BOUNCE_PAD);

            if (Services.PLATFORM.isModLoaded(TinkersBouncePad.TCON_MOD_ID)) {
                event.accept(ModInit.SKYSLIME_BOUNCE_PAD);
                event.accept(ModInit.ENDERSLIME_BOUNCE_PAD);
                event.accept(ModInit.ICHOR_BOUNCE_PAD);
            }
        }
    }
}
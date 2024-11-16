package einstein.tbouncepad;

import einstein.tbouncepad.init.ModInit;
import einstein.tbouncepad.platform.NeoForgeRegistryHelper;
import einstein.tbouncepad.platform.Services;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(TinkersBouncePad.MOD_ID)
public class TinkersBouncePadNeoForge {

    public TinkersBouncePadNeoForge(IEventBus eventBus) {
        TinkersBouncePad.init();
        NeoForgeRegistryHelper.ITEMS.register(eventBus);
        NeoForgeRegistryHelper.BLOCKS.register(eventBus);
        eventBus.addListener(this::onBuildContents);
    }

    void onBuildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            event.accept(ModInit.BOUNCE_PAD.get());

            if (Services.PLATFORM.isModLoaded(TinkersBouncePad.TCON_MOD_ID)) {
                event.accept(ModInit.SKYSLIME_BOUNCE_PAD.get());
                event.accept(ModInit.ENDERSLIME_BOUNCE_PAD.get());
                event.accept(ModInit.ICHOR_BOUNCE_PAD.get());
            }
        }
    }
}

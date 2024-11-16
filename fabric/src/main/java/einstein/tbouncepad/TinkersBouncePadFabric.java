package einstein.tbouncepad;

import einstein.tbouncepad.init.ModInit;
import einstein.tbouncepad.platform.Services;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class TinkersBouncePadFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        TinkersBouncePad.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.accept(ModInit.BOUNCE_PAD.get());

            if (Services.PLATFORM.isModLoaded(TinkersBouncePad.TCON_MOD_ID)) {
                entries.accept(ModInit.SKYSLIME_BOUNCE_PAD.get());
                entries.accept(ModInit.ENDERSLIME_BOUNCE_PAD.get());
                entries.accept(ModInit.ICHOR_BOUNCE_PAD.get());
            }
        });
    }
}

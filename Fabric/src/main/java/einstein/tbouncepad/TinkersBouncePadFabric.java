package einstein.tbouncepad;

import einstein.tbouncepad.platform.Services;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class TinkersBouncePadFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        TinkersBouncePad.init();
        ModInit.register();
        ModLoadingContext.registerConfig(TinkersBouncePad.MOD_ID, ModConfig.Type.CLIENT, TinkersBouncePad.buildConfigs());

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.accept(ModInit.BOUNCE_PAD);

            if (Services.PLATFORM.isModLoaded(TinkersBouncePad.TCON_MOD_ID)) {
                entries.accept(ModInit.SKYSLIME_BOUNCE_PAD);
                entries.accept(ModInit.ENDERSLIME_BOUNCE_PAD);
                entries.accept(ModInit.ICHOR_BOUNCE_PAD);
            }
        });
    }
}

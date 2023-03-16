package einstein.tbouncepad;

import einstein.tbouncepad.platform.Services;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.config.ModConfig;

public class TinkersBouncePadFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        TinkersBouncePad.init();
        ForgeConfigRegistry.INSTANCE.register(TinkersBouncePad.MOD_ID, ModConfig.Type.CLIENT, TinkersBouncePad.buildConfigs());

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

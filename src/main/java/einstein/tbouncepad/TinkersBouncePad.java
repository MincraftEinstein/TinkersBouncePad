package einstein.tbouncepad;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TinkersBouncePad.MODID)
public class TinkersBouncePad {
    public static final String MODID = "tbouncepad";
    public static final String TCON_MODID = "tconstruct";
    
    public TinkersBouncePad() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	ModInit.ITEMS.register(modEventBus);
    	ModInit.BLOCKS.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModInit.CLIENTSPEC);
	}
}

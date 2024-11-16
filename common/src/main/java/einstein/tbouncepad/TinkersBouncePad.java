package einstein.tbouncepad;

import einstein.tbouncepad.init.ModConfigs;
import einstein.tbouncepad.init.ModInit;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TinkersBouncePad {

    public static final String MOD_ID = "tbouncepad";
    public static final String TCON_MOD_ID = "tconstruct";
    public static final String MOD_NAME = "Tinkers Bounce Pad";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static final ModConfigs CONFIGS = ConfigApiJava.registerAndLoadConfig(ModConfigs::new, RegisterType.CLIENT);

    public static void init() {
        ModInit.init();
    }

    public static ResourceLocation loc(String str) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, str);
    }
}
package einstein.tbouncepad;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TinkersBouncePad {

    public static final String MOD_ID = "tbouncepad";
    public static final String TCON_MOD_ID = "tconstruct";
    public static final String MOD_NAME = "Tinkers Bounce Pad";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
    public static ForgeConfigSpec.BooleanValue BOUNCE_SOUND;

    public static void init() {
        ModInit.init();
    }

    public static ForgeConfigSpec buildConfigs() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        BOUNCE_SOUND = builder.comment("If true then a slime sound will be played when an entity bounces on the bounce pad").define("bounceSound", true);
        return builder.build();
    }

    public static ResourceLocation loc(String str) {
        return new ResourceLocation(MOD_ID, str);
    }
}
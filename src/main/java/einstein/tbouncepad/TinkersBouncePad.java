package einstein.tbouncepad;

import einstein.einsteins_library.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod(TinkersBouncePad.MODID)
public class TinkersBouncePad
{
    public static final String MODID = "tbouncepad";
    public static final String TCON_MODID = "tconstruct";
    
    public TinkersBouncePad() {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModInit.CLIENTSPEC);
	}
    
    @EventBusSubscriber(modid = TinkersBouncePad.MODID, bus = Bus.MOD)
    public static class ModInit
    {
        public static final Block BOUNCE_PAD = RegistryHandler.registerBlock(TinkersBouncePad.MODID, "bounce_pad", new BouncePad(Block.Properties.create(Material.CLAY, MaterialColor.GRASS).hardnessAndResistance(0.3F).harvestTool(ToolType.SHOVEL).sound(SoundType.SLIME).setAllowsSpawn(Blocks::neverAllowSpawn)), ItemGroup.TRANSPORTATION);
        public static final Block SKYSLIME_BOUNCE_PAD = RegistryHandler.registerBlock(TinkersBouncePad.MODID, TinkersBouncePad.TCON_MODID, "skyslime_bounce_pad", new BouncePad(Block.Properties.from(ModInit.BOUNCE_PAD)), ItemGroup.TRANSPORTATION);
        public static final Block ENDERSLIME_BOUNCE_PAD = RegistryHandler.registerBlock(TinkersBouncePad.MODID, TinkersBouncePad.TCON_MODID, "enderslime_bounce_pad", new BouncePad(Block.Properties.from(ModInit.BOUNCE_PAD)), ItemGroup.TRANSPORTATION);
        public static final Block ICHOR_BOUNCE_PAD = RegistryHandler.registerBlock(TinkersBouncePad.MODID, TinkersBouncePad.TCON_MODID, "ichor_bounce_pad", new BouncePad(Block.Properties.from(ModInit.BOUNCE_PAD)), ItemGroup.TRANSPORTATION);
        
        public static ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec.BooleanValue BOUNCE_SOUND = CLIENT_BUILDER.comment("If true then a slime sound will be played when an entity bounces on the bounce pad").define("bounceSound", true);
        private static final ForgeConfigSpec CLIENTSPEC = CLIENT_BUILDER.build();
    }
}

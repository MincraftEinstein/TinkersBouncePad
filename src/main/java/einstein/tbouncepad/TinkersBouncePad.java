package einstein.tbouncepad;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(TinkersBouncePad.MODID)
public class TinkersBouncePad
{
    public static final String MODID = "tbouncepad";
    public static final String TCON_MODID = "tconstruct";
    
    public TinkersBouncePad() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ModInit.CLIENTSPEC);
    	ModInit.ITEMS.register(modEventBus);
    	ModInit.BLOCKS.register(modEventBus);
	}
    
    public static class ModInit
    {
    	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    	
        public static final RegistryObject<Block> BOUNCE_PAD = register("bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).strength(0.3F).sound(SoundType.SLIME_BLOCK).isValidSpawn(Blocks::never)), true);
        public static final RegistryObject<Block> SKYSLIME_BOUNCE_PAD = register("skyslime_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(ModInit.BOUNCE_PAD.get())), ModList.get().isLoaded(TCON_MODID));
        public static final RegistryObject<Block> ENDERSLIME_BOUNCE_PAD = register("enderslime_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(ModInit.BOUNCE_PAD.get())), ModList.get().isLoaded(TCON_MODID));
        public static final RegistryObject<Block> ICHOR_BOUNCE_PAD = register("ichor_bounce_pad", () -> new BouncePad(BlockBehaviour.Properties.copy(ModInit.BOUNCE_PAD.get())), ModList.get().isLoaded(TCON_MODID));
        
        public static ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec.BooleanValue BOUNCE_SOUND = CLIENT_BUILDER.comment("If true then a slime sound will be played when an entity bounces on the bounce pad").define("bounceSound", true);
        private static final ForgeConfigSpec CLIENTSPEC = CLIENT_BUILDER.build();
        
        private static RegistryObject<Block> register(final String name, final Supplier<Block> block, boolean hasTab) {
        	final RegistryObject<Block> instance = BLOCKS.register(name, block);
        	final Item.Properties props = new Item.Properties();
        	
        	if (hasTab) {
        		props.tab(CreativeModeTab.TAB_TRANSPORTATION);
        	}
        	
        	ITEMS.register(name, () -> new BlockItem(instance.get(), props));
        	return instance;
        }
    }
}

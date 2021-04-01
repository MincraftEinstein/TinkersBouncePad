package einstein.tbouncepad;

import einstein.einsteins_library.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;

@Mod(TinkersBouncePad.MODID)
public class TinkersBouncePad
{
    public static final String MODID = "tbouncepad";
    
    public TinkersBouncePad() {
    }
    
    @Mod.EventBusSubscriber(modid = TinkersBouncePad.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModBlocks
    {
        public static final Block BOUNCE_PAD = RegistryHandler.registerBlock(TinkersBouncePad.MODID, "bounce_pad", new BouncePad(Block.Properties.create(Material.CLAY, MaterialColor.GRASS).hardnessAndResistance(0.3F).harvestTool(ToolType.SHOVEL).sound(SoundType.SLIME).setAllowsSpawn(Blocks::neverAllowSpawn)), ItemGroup.TRANSPORTATION);
    }
}

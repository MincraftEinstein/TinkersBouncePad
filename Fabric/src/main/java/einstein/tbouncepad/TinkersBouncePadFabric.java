package einstein.tbouncepad;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class TinkersBouncePadFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        TinkersBouncePad.init();
        ModInit.register();
        ModLoadingContext.registerConfig(TinkersBouncePad.MOD_ID, ModConfig.Type.CLIENT, TinkersBouncePad.buildConfigs());
    }
}

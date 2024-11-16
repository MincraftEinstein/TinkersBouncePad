package einstein.tbouncepad.init;

import einstein.tbouncepad.TinkersBouncePad;
import me.fzzyhmstrs.fzzy_config.annotations.ConvertFrom;
import me.fzzyhmstrs.fzzy_config.annotations.Translation;
import me.fzzyhmstrs.fzzy_config.config.Config;

@ConvertFrom(fileName = "tbouncepad-client.toml")
@Translation(prefix = "config." + TinkersBouncePad.MOD_ID)
public class ModConfigs extends Config {

    public boolean bounceSound = true;

    public ModConfigs() {
        super(TinkersBouncePad.loc("main"));
    }
}

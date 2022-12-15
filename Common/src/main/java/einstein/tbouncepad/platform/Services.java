package einstein.tbouncepad.platform;

import einstein.tbouncepad.TinkersBouncePad;
import einstein.tbouncepad.platform.services.IPlatformHelper;
import einstein.tbouncepad.platform.services.RegistryHelper;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final RegistryHelper REGISTRY = load(RegistryHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TinkersBouncePad.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}

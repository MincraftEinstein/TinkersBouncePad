package einstein.tbouncepad.loader;

import einstein.tbouncepad.loader.services.LoaderHelper;

import java.util.ServiceLoader;

public class Services {

    public static final LoaderHelper LOADER = load(LoaderHelper.class);

    public static <T> T load(Class<T> clazz) {
        return ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
    }
}

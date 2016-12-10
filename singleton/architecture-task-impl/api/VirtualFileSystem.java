package api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import spi.ServiceProvider;

/**
 * @author denis
 *
 */
public final class VirtualFileSystem {

    private VirtualFileSystem() {
    }

    private static final VirtualFileSystem INSTANCE = new VirtualFileSystem();

    public static VirtualFileSystem getInstance() {
        return INSTANCE;
    }

    public VirtualFile getFile(String id) {
        Optional<ServiceProvider> provider = ApiClassFactory.PROVIDERS.stream().filter(service -> service.ownsId(id))
                .findFirst();
        if (provider.isPresent()) {
            return ApiClassFactory.INSTANCE.create(provider.get(), id);
        }
        return null;
    }

    public List<String> getFileIds() {
        return ApiClassFactory.PROVIDERS.stream().flatMap(provider -> provider.getIds().stream())
                .collect(Collectors.toList());
    }

    public String getPath(VirtualFile file) {
        AbstractVirtualFile virtualFile = file;
        return virtualFile.getProvider().getPath(virtualFile.getId());
    }

    static class AbstractVirtualFile {

        protected AbstractVirtualFile(ServiceProvider provider, String id) {
            myProvider = provider;
            myId = id;
        }

        protected ServiceProvider getProvider() {
            return myProvider;
        }

        protected String getId() {
            return myId;
        }

        private final String myId;

        private final ServiceProvider myProvider;
    }

    public static abstract class ApiClassFactory {

        protected static ApiClassFactory INSTANCE;

        private static final Collection<ServiceProvider> PROVIDERS = loadProviders();

        static {
            loadImpl();
        }

        protected abstract VirtualFile create(ServiceProvider provider, String id);

        private static void loadImpl() {
            try {
                Class.forName(VirtualFile.class.getName() + "$ApiClassFactoryImpl");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        private static Collection<ServiceProvider> loadProviders() {
            Iterator<ServiceProvider> iterator = ServiceLoader.load(ServiceProvider.class).iterator();
            Collection<ServiceProvider> providers = new ArrayList<>();
            for (; iterator.hasNext();) {
                providers.add(iterator.next());
            }
            return providers;
        }
    }
}
package api;

import api.VirtualFileSystem.ApiClassFactory;
import spi.ServiceProvider;

/**
 * @author denis
 *
 */
public final class VirtualFile {

    private VirtualFile(ServiceProvider provider, String id) {
        myProvider = provider;
        myId = id;
    }

    public final byte[] getBinaryContent() {
        return getProvider().getData(getId());
    }

    private ServiceProvider getProvider() {
        return myProvider;
    }

    private String getId() {
        return myId;
    }

    private static class ApiClassFactoryImpl extends ApiClassFactory {

        static {
            INSTANCE = new ApiClassFactoryImpl();
        }

        @Override
        protected VirtualFile create(ServiceProvider provider, String id) {
            return new VirtualFile(provider, id);
        }

    }

    private final ServiceProvider myProvider;
    private final String myId;
}
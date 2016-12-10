package api;

import api.VirtualFileSystem.AbstractVirtualFile;
import api.VirtualFileSystem.ApiClassFactory;
import spi.ServiceProvider;

/**
 * @author denis
 *
 */
public final class VirtualFile extends AbstractVirtualFile {

    private VirtualFile(ServiceProvider provider, String id) {
        super(provider, id);
    }

    public final byte[] getBinaryContent() {
        return getProvider().getData(getId());
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

}
package spi;

import java.util.Collection;

/**
 * @author denis
 *
 */
public interface ServiceProvider {

    byte[] getData(String id);

    Collection<String> getIds();

    boolean ownsId(String id);

    String getPath(String id);
}

package test;

import java.util.Optional;

/**
 * @author denis
 *
 */
public interface ModelAccess {

    Model getModel();

    Optional<Component> getRoot();

    Optional<Component> getComponent(Uid uid);
}

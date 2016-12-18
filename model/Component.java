package test;

/**
 * @author denis
 *
 */
public interface Component {

    Uid getId();

    String getProperty(String name);

    void setProperty(String name, String value);

    // Возвращает {@code true} если елемент уже не в модели. Бесполезные методы
    boolean isOrphaned();

    // Другое имя для метода выше.
    // boolean isRemoved();
}

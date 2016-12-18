package test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author denis
 *
 */
public class Model {

    public void invokeRead(ModelOperation<ModelAccess> operation) {
        myLock.readLock().lock();
        try {
            operation.accept(myAccess);
        } finally {
            myLock.readLock().unlock();
        }
    }

    public void invokeWrite(ModelOperation<ModelAccess> operation) {
        myLock.writeLock().lock();
        try {
            operation.accept(myAccess);
        } finally {
            myLock.writeLock().unlock();
        }
    }

    public <T> T lookup(Class<T> clazz) {
        return null;
    }

    public static void main(String[] args) {
        /*
         * Создание каким-то образом ModelSource на основании некот. информации,
         * например файла на диске (File, Input-OutputStream). Вопрос : почему
         * просто не делать это сразу по файлу? Потому что это позволяет держать
         * доступ к "подложке" абстрактным. Вместо файла можно будет
         * использовать что то дргуое ( например IFile из Eclipse API , что -то
         * другое из API другого IDE, InteliJ IDEA). Если в это класс вынести
         * методы, кот. слушают изменения в "подложке". То не нужно будет
         * дублировать код, кот. меняет модель в зависимости от изменений. Нужно
         * только перечитать содержимое файла и забрать его из ModelSource вне
         * зависимости от того как в конечном итоге это имплементировано.
         */
        ModelSource source = null;
        // Возможность получить модель (один раз созданную тем же factory) по
        // ModelSource.
        // Factory здесь отвечает также за кеширование созданных моделей.
        // (WeakHashMap может использоваться).
        Model model = ModelFactory.getInstance().getModel(source);

        Uid[] uids = new Uid[1];

        model.invokeRead(new ModelOperation<ModelAccess>() {

            @Override
            public void accept(ModelAccess access) {
                Optional<Composite> root = access.getRoot();
                if (root.isPresent()) {
                    // сделать что-нибудь, например прочитать его детей
                    List<Component> children = root.get().getChildren();
                    /*
                     * Забрать отсюда какой-нибудь элемент, напрмер потому что
                     * он выбран пользователем чере UI ( select).
                     */
                    uids[0] = children.get(1).getId();
                }
            }
        });

        model.invokeWrite(new ModelOperation<ModelAccess>() {

            @Override
            public void accept(ModelAccess access) {
                Optional<Composite> root = access.getRoot();
                if (root.isPresent()) {
                    // Найти все Label, под корнем и удалить их, например
                    root.get().getComponents(Label.class).stream().forEach(root.get()::remove);
                }
            }
        });
        /*
         * Здесь можно было бы подумать над альтернативным подходом : убрать
         * мутирующие методы из Composite и перенести их в ModelAcces , причём
         * другой, разный для чтения и записи: только то, что передаётся для
         * записи даёт мутировать модель
         */

        // Выполнить мутирующую операцию над выбранным элементом
        model.invokeWrite(new ModelOperation<ModelAccess>() {

            @Override
            public void accept(ModelAccess access) {
                Optional<Component> component = access.getComponent(uids[0]);
                if (component.isPresent()) {
                    ((Label) component.get()).setCaption("new caption");
                    // или через общие методы setProprty()
                    component.get().setProperty("caption", "new caption");
                }
            }
        });

    }

    private static class UidImpl implements Uid {
        private Component getComponent() {
            return myComponent;
        }

        private Component myComponent;
    }

    private ModelAccess myAccess;

    private final ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();
}

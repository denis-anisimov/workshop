package test;

/**
 * @author denis
 *
 */
public abstract class AbstractCompositeInitial<T extends AbstractComponent> extends CustomComponent {

    @Override
    public void attach() {
        super.attach();
        if (getCompositionRoot() == null) {
            setCompositionRoot(createComponent());
        }
    }

    public void setCaption(String caption) {
        getCompositionRoot().setCaption(caption);
    }

    protected T getCompositionRoot() {
        return (T) super.getCompositionRoot();
    }

    protected abstract T createComponent();
}

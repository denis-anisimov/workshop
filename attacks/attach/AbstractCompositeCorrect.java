package test;

/**
 * @author denis
 *
 */
public abstract class AbstractCompositeCorrect<T extends AbstractComponent> extends CustomComponent {

    @Override
    public void attach() {
        super.attach();
        if (getCompositionRoot() == null) {
            setCompositionRoot(createComponent());
            setCaption(myCaption);
        }
    }

    public void setCaption(String caption) {
        if (getCompositionRoot() != null) {
            getCompositionRoot().setCaption(caption);
        } else {
            myCaption = caption;
        }
    }

    protected T getCompositionRoot() {
        return (T) super.getCompositionRoot();
    }

    protected abstract T createComponent();

    private String myCaption;
}

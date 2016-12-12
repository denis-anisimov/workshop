package test;

/**
 * @author denis
 *
 */
public class SWT {

    public void doSomethingInUi() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(() -> {
            // Update UI
        });
    }
}

class PlatformUI {
    public static IWorkbench getWorkbench() {
        if (Workbench.getInstance() == null) {
            // app forgot to call createAndRunWorkbench beforehand
            throw new IllegalStateException(WorkbenchMessages.getString("PlatformUI.NoWorkbench")); //$NON-NLS-1$
        }
        return Workbench.getInstance();
    }
}

final class Workbench extends EventManager implements IWorkbench {

    private Display display;

    public Display getDisplay() {
        return display;
    }
}
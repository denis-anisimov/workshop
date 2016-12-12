package test;

/**
 * @author denis
 *
 */
public class ApiClass {

    public <T> T lookup(Class<? super T> clazz) {
        return null /*
                     * complex implementation which will require its own
                     * architecture
                     */;
    }

    public static void main(String[] args) {
        ApiClass api = new ApiClass();

        /*
         * The first example: just store some data into the instance which you
         * are not able to store into ApiClass directly because you can't
         * control the class.
         */
        AssociatedData data = api.lookup(AssociatedData.class);
        if (data != null) {
            // do something with it
        }

        /*
         * The second example: registered "service" (most likely once using
         * declarative way).
         */
        DeveloperService service = api.lookup(DeveloperService.class);
        if (service != null) { /*
                                * might be not needed: in case of declarative
                                * registration code may assume it's always there
                                */

            /*
             * Here: CustomServiceImplementation instance will be returned and
             * its method doSomething() called.
             */
            service.doSomething();
        }
    }

}

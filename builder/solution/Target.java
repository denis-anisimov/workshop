package test;

/**
 * @author denis
 *
 */
public class Target implements Cloneable {

    private Target(int id) {
        myId = id;
    }

    private Target() {
        myId = -1;
    }

    public int getId() {
        return myId;
    }

    private void setId(int id) {
        myId = id;
    }

    @Override
    protected Target clone() {
        try {
            return (Target) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class TargetBuilder {

        public TargetBuilder() {
            myTarget = new Target();
        }

        public TargetBuilder setId(int id) {
            myTarget.setId(id);
            return this;
        }

        public Target build() {
            return myTarget.clone();
        }

        private final Target myTarget;
    }

    private int myId;

}

package vsu.shirnin.reflection;

public class SomeBean {
    /**
     * first field with one interface
     */
    @AutoInjectable
    private FirstInterface field1;
    /**
     * second field with other interface
     */
    @AutoInjectable
    private SecondInterface field2;
    private int a;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}

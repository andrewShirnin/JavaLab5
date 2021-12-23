package vsu.shirnin.reflection;

public class Main {
    public static void main(String... args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        SomeBean bean = (new Injector<SomeBean>()).Inject(new SomeBean());
        bean.foo();
    }
}

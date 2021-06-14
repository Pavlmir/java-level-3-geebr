package lesson7.MyUnitTests;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        // RunTests.run(new Sample());
        try {
            RunTests.start("lesson7.MyUnitTests.Sample");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

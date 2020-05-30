package visibilitychecker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class Checker {
    private String _packageName;
    private List<String> _classNames;

    public Checker(String packageName, List<String> classNames) {
        _packageName = packageName;
        _classNames = classNames;
    }

    public void check(){
        Class cls;
        Field[] fields;
        Method[] methods;

        for (String className: _classNames) {
            System.out.println("For" + className + ":");
            try {
                cls = Class.forName(_packageName + "." + className);
                fields = cls.getDeclaredFields();
                methods = cls.getDeclaredMethods();


                System.out.println("Fields:");
                for (Field field: fields) {
                    System.out.println(field);
                }

                System.out.println("Methods:");
                for (Method method: methods) {
                    System.out.println(method);
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
            System.out.println();
        }
    }

}

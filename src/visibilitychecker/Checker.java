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

    public void check(boolean publicVis, boolean privateVis, boolean protectedVis, boolean packageVis) {
        Class cls;
        Field[] fields;
        Method[] methods;

        for (String className: _classNames) {
            System.out.println(_packageName + "." + className + ":");
            try {
                cls = Class.forName(_packageName + "." + className);
                fields = cls.getDeclaredFields();
                methods = cls.getDeclaredMethods();


                System.out.println("    Fields:");
                for (Field field: fields) {
                    print(publicVis, privateVis, protectedVis, packageVis, field.toString());
                }

                System.out.println("    Methods:");
                for (Method method: methods) {
                    print(publicVis, privateVis, protectedVis, packageVis, method.toString());
                }
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
            System.out.println();
        }
    }

    private void print(boolean publicVis, boolean privateVis, boolean protectedVis, boolean packageVis, String str) {
        if (publicVis && str.contains("public")) {
            System.out.println("        " + str);
        }
        if (privateVis && str.contains("private")) {
            System.out.println("        " + str);
        }
        if (protectedVis && str.contains("protected")) {
            System.out.println("        " + str);
        }
        if (packageVis && !str.contains("public") && !str.contains("private") && !str.contains("protected")) {
            System.out.println("        " + str);
        }

    }

}

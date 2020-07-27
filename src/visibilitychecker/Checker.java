package visibilitychecker;

import javax.swing.*;
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

    public void check(boolean publicVis, boolean privateVis, boolean protectedVis, boolean packageVis, boolean fieldsSelect, boolean methodsSelect, JTextArea textArea) {
        Class cls;
        Field[] fields;
        Method[] methods;

        for (String className: _classNames) {
            textArea.append(_packageName + "." + className + ":\n");
            try {
                cls = Class.forName(_packageName + "." + className);
                fields = cls.getDeclaredFields();
                methods = cls.getDeclaredMethods();

                if (fieldsSelect) {
                    textArea.append("    Fields:" + "\n");
                    for (Field field: fields) {
                        print(publicVis, privateVis, protectedVis, packageVis, field.toString(), textArea);
                    }
                }

                if (methodsSelect) {
                    textArea.append("    Methods:" + "\n");
                    for (Method method : methods) {
                        print(publicVis, privateVis, protectedVis, packageVis, method.toString(), textArea);
                    }
                }
            } catch (ClassNotFoundException e) {
                textArea.append(e.toString() + "\n");
            }
            textArea.append("\n");
        }
    }

    private void print(boolean publicVis, boolean privateVis, boolean protectedVis, boolean packageVis, String str, JTextArea textArea) {
        if (publicVis && str.contains("public ")) {
            textArea.append("        " + str + "\n");
        }
        if (privateVis && str.contains("private ")) {
            textArea.append("        " + str + "\n");
        }
        if (protectedVis && str.contains("protected ")) {
            textArea.append("        " + str + "\n");
        }
        if (packageVis && !str.contains("public ") && !str.contains("private ") && !str.contains("protected ")) {
            textArea.append("        " + str + "\n");
        }
    }
}

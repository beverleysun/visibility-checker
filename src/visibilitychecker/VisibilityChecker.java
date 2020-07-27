package visibilitychecker;

import javax.swing.*;
import java.util.List;

public class VisibilityChecker extends JFrame {

    public static void main(String[] args) {
        VisibilityChecker visChecker = new VisibilityChecker();
        visChecker.drawWindow();

//        // Get package name of java files for checking
//        PackageName packageName = new PackageName();
//        String packageForChecking = packageName.getPackageForChecking();
//
//        // Get all class names to check
//        List<String> classNames = new Directory(packageForChecking).getClassNames();
//        System.out.println(classNames);
//
//        // Check all classes
//        Checker checker = new Checker(packageForChecking, classNames);
//        checker.check();
    }

    public void drawWindow() {
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

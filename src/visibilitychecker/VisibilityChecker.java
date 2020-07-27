package visibilitychecker;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.List;

public class VisibilityChecker extends JFrame {

    public static void main(String[] args) {
        VisibilityChecker visChecker = new VisibilityChecker();
        visChecker.setUpGUI();
        visChecker.drawWindow();



    }

    public void execute() {
        // Get package name of java files for checking
        PackageName packageName = new PackageName();
        String packageForChecking = packageName.getPackageForChecking();

        // Get all class names to check
        List<String> classNames = new Directory(packageForChecking).getClassNames();
        System.out.println(classNames);

        // Check all classes
        Checker checker = new Checker(packageForChecking, classNames);
        checker.check();

    }

    public void drawWindow() {
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Visibility Checker");
        setVisible(true);
    }

    public void setUpGUI() {
        JPanel mainPanel = new JPanel();
        JPanel operationPanel = new JPanel();

        Border operationBorder = BorderFactory.createTitledBorder("Visibility to check for:");

        operationPanel.setBorder(operationBorder);
        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.PAGE_AXIS));

        JCheckBox publicVisBox = new JCheckBox("public");
        JCheckBox privateVisBox = new JCheckBox("private");
        JCheckBox protectedVisBox = new JCheckBox("protected");
        JCheckBox packageVisBox = new JCheckBox("package private");


        JButton executeButton = new JButton("Run");


        operationPanel.add(publicVisBox);
        operationPanel.add(privateVisBox);
        operationPanel.add(protectedVisBox);
        operationPanel.add(packageVisBox);
        mainPanel.add(operationPanel);
        mainPanel.add(executeButton);
        add(mainPanel);
    }



}

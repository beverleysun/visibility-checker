package visibilitychecker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisibilityChecker extends JFrame {

    private JCheckBox _publicVisBox, _privateVisBox, _protectedVisBox, _packageVisBox;
    private boolean _publicVisSelected, _privateVisSelected, _protectedVisSelected, _packageVisSelected;


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
        checker.check(_publicVisSelected, _privateVisSelected, _protectedVisSelected, _packageVisSelected);

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

        _publicVisBox = new JCheckBox("public");
        _privateVisBox = new JCheckBox("private");
        _protectedVisBox = new JCheckBox("protected");
        _packageVisBox = new JCheckBox("package private");


        JButton executeButton = new JButton("Run");
        executeButton.addActionListener(new ButtonListener());

        operationPanel.add(_publicVisBox);
        operationPanel.add(_privateVisBox);
        operationPanel.add(_protectedVisBox);
        operationPanel.add(_packageVisBox);
        mainPanel.add(operationPanel);
        mainPanel.add(executeButton);
        add(mainPanel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            _publicVisSelected = _publicVisBox.isSelected();
            _privateVisSelected = _privateVisBox.isSelected();
            _protectedVisSelected = _protectedVisBox.isSelected();
            _packageVisSelected = _packageVisBox.isSelected();
            execute();
        }
    }
}

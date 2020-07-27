package visibilitychecker;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisibilityChecker extends JFrame {

    private JCheckBox _publicVisBox, _privateVisBox, _protectedVisBox, _packageVisBox;
    private JCheckBox _methodsBox, _fieldsBox;
    private boolean _publicVisSelected, _privateVisSelected, _protectedVisSelected, _packageVisSelected;
    private boolean _methodsSelected, _fieldsSelected;


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
        System.out.println("Classes to check: ");
        for (String className : classNames){
            System.out.println("    " + className);
        }
        System.out.println();

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
        JPanel visibilityPanel = new JPanel();
        JPanel methodsFieldsPanel = new JPanel();

        operationPanel.setLayout(new GridLayout());

        Border operationBorder = BorderFactory.createTitledBorder("Visibility To Check For:");
        Border methodsFieldsBorder = BorderFactory.createTitledBorder("Methods and Fields:");

        visibilityPanel.setBorder(operationBorder);
        visibilityPanel.setLayout(new BoxLayout(visibilityPanel, BoxLayout.PAGE_AXIS));

        methodsFieldsPanel.setBorder(methodsFieldsBorder);
        methodsFieldsPanel.setLayout(new BoxLayout(methodsFieldsPanel, BoxLayout.PAGE_AXIS));

        _fieldsBox = new JCheckBox("Methods");
        _methodsBox = new JCheckBox("Fields");

        _publicVisBox = new JCheckBox("Public");
        _privateVisBox = new JCheckBox("Private");
        _protectedVisBox = new JCheckBox("Protected");
        _packageVisBox = new JCheckBox("Package Private");

        JButton executeButton = new JButton("Run");
        executeButton.addActionListener(new ButtonListener());

        methodsFieldsPanel.add(_fieldsBox);
        methodsFieldsPanel.add(_methodsBox);

        visibilityPanel.add(_publicVisBox);
        visibilityPanel.add(_privateVisBox);
        visibilityPanel.add(_protectedVisBox);
        visibilityPanel.add(_packageVisBox);

        operationPanel.add(methodsFieldsPanel);
        operationPanel.add(visibilityPanel);
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

package visibilitychecker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisibilityChecker extends JFrame {

    private JCheckBox _publicVisBox, _privateVisBox, _protectedVisBox, _packageVisBox;
    private JCheckBox _methodsBox, _fieldsBox;
    private JTextArea _resultTextArea;
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

        _resultTextArea.append("Classes to check: " + "\n");
        for (String className : classNames){
            _resultTextArea.append("        " + className + "\n");
        }
        _resultTextArea.append("\n");

        // Check all classes
        Checker checker = new Checker(packageForChecking, classNames);
        checker.check(_publicVisSelected, _privateVisSelected, _protectedVisSelected, _packageVisSelected, _fieldsSelected, _methodsSelected, _resultTextArea);
    }

    public void drawWindow() {
        int _windowSizeWidth = 600;
        int _windowSizeHeight = 400;
        String _title = "Visibility Checker";


        setSize(_windowSizeWidth, _windowSizeHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle(_title);
        setVisible(true);
        setResizable(false);
    }

    public void setUpGUI() {
        // Initialise panels
        JPanel mainPanel = new JPanel();
        JPanel operationPanel = new JPanel();
        JPanel visibilityPanel = new JPanel();
        JPanel methodsFieldsPanel = new JPanel();
        JPanel executePanel = new JPanel();
        JPanel resultPanel = new JPanel();

        // Initialise borders for panels
        Border operationBorder = BorderFactory.createTitledBorder("Visibility To Check For:");
        Border methodsFieldsBorder = BorderFactory.createTitledBorder("Methods and Fields:");
        Border resultBorder = BorderFactory.createTitledBorder("Result:");

        visibilityPanel.setBorder(operationBorder);
        visibilityPanel.setLayout(new BoxLayout(visibilityPanel, BoxLayout.PAGE_AXIS));

        methodsFieldsPanel.setBorder(methodsFieldsBorder);
        methodsFieldsPanel.setLayout(new BoxLayout(methodsFieldsPanel, BoxLayout.PAGE_AXIS));

        resultPanel.setBorder(resultBorder);
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.PAGE_AXIS));

        // Create components
        _resultTextArea = new JTextArea();
        _resultTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(_resultTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        _fieldsBox = new JCheckBox("Methods");
        _methodsBox = new JCheckBox("Fields");

        _publicVisBox = new JCheckBox("Public");
        _privateVisBox = new JCheckBox("Private");
        _protectedVisBox = new JCheckBox("Protected");
        _packageVisBox = new JCheckBox("Package Private");

        JButton executeButton = new JButton("Run");
        executeButton.addActionListener(new ButtonListener());

        // Setting layouts
        operationPanel.setLayout(new GridLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        operationPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 11));
        executePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));

        // Adding components to panels
        methodsFieldsPanel.add(_fieldsBox);
        methodsFieldsPanel.add(_methodsBox);

        visibilityPanel.add(_publicVisBox);
        visibilityPanel.add(_privateVisBox);
        visibilityPanel.add(_protectedVisBox);
        visibilityPanel.add(_packageVisBox);

        operationPanel.add(methodsFieldsPanel);
        operationPanel.add(visibilityPanel);
        executePanel.add(executeButton);

        resultPanel.add(scroll);

        mainPanel.add(operationPanel);
        mainPanel.add(executePanel);
        mainPanel.add(resultPanel);

        add(mainPanel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            _fieldsSelected = _fieldsBox.isSelected();
            _methodsSelected = _methodsBox.isSelected();

            _publicVisSelected = _publicVisBox.isSelected();
            _privateVisSelected = _privateVisBox.isSelected();
            _protectedVisSelected = _protectedVisBox.isSelected();
            _packageVisSelected = _packageVisBox.isSelected();

            //reset text
            _resultTextArea.setText(null);

            execute();
            _resultTextArea.update(_resultTextArea.getGraphics());
        }
    }
}

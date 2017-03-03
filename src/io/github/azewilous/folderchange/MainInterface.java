package io.github.azewilous.folderchange;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
/**
 * Created on 2/28/2017.
 */
public class MainInterface {

    private File checkedImg = new File(getResourcesFolder() + "//checked.png");
    private File uncheckedImg = new File(getResourcesFolder() + "//unchecked.png");

    public MainInterface() throws URISyntaxException {}

    private JTextField field1;
    private JTextField field2;
    private JCheckBox box;

    public void createFrame() {
        JFrame frame = new JFrame("User Path Edit");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.add(createPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 47, 47));
        panel.setLayout(null);

        panel.add(createChangeFrom());
        panel.add(createChangeTo());
        panel.add(createCheckBox());
        panel.add(createCompleteButton());

        panel.setVisible(true);

        return panel;
    }

    private JTextField createChangeFrom() {
        field1 = new JTextField(16) {
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(-25, -30);
            }
        };
        field1.setSize(120, 30);
        field1.setBackground(new Color(107, 111, 109));

        field1.setFont(new Font("Castellar", Font.ITALIC, 12));
        field1.setSelectedTextColor(new Color(47, 47, 47));
        field1.setCaretColor(new Color(133, 67, 40));
        field1.setBorder(null);
        field1.setToolTipText("Enter the path to the folder in which you would like to change");
        field1.setHorizontalAlignment(JTextField.CENTER);

        field1.setLocation(50, 45);
        field1.setVisible(true);

        field1.setFocusable(true);

        return field1;
    }

    private JTextField createChangeTo() {
        field2 = new JTextField(16) {
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(-100, -30);
            }
        };
        field2.setSize(120, 30);
        field2.setBackground(new Color(107, 111, 109));

        field2.setFont(new Font("Castellar", Font.ITALIC, 12));
        field2.setSelectedTextColor(new Color(47, 47, 47));
        field2.setCaretColor(new Color(133, 67, 40));
        field2.setBorder(null);
        field2.setToolTipText("Enter a new name you would like for the folder");
        field2.setHorizontalAlignment(JTextField.CENTER);

        field2.setLocation(220, 45);
        field2.setVisible(true);

        return field2;
    }

    private JCheckBox createCheckBox() {
        box = new JCheckBox() {
            public Point getToolTipLocation(MouseEvent e) {
                return new Point(0, -50);
            }
        };
        box.setToolTipText("The creator of this program is not liable for any data harmed during the execution of " +
                "this software");
        box.setSize(42, 42);
        box.setLocation(70, 150);
        box.setOpaque(true);
        box.setBorder(null);
        box.setContentAreaFilled(false);
        box.setBorderPaintedFlat(false);

        if (uncheckedImg.exists()) {
            box.setIcon(new ImageIcon(uncheckedImg.getPath(), "unchecked"));
        }
        if (checkedImg.exists()) {
            box.setSelectedIcon(new ImageIcon(checkedImg.getPath(), "checked"));
        }

        box.setVisible(true);

        return box;
    }

    private JButton createCompleteButton() {
        JButton button = new JButton();
        button.setSize(150, 50);

        button.setLocation(200, 145);
        button.setBackground(new Color(107, 111, 109));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);

        button.setText("Complete");

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (box.isSelected()) {
                    if (!FolderHandler.isRunning()) {
                        FolderHandler.handleFolderNameChange(field1.getText(), field2.getText());
                    }
                } else {
                    System.out.println("You must check the box to run this program.");
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button.setBackground(new Color(button.getBackground().getRed() / 4, button.getBackground().getGreen()
                        / 4, button.getBackground().getBlue() / 4));
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button.setBackground(new Color(button.getBackground().getRed() * 4, button.getBackground().getGreen()
                        * 4, button.getBackground().getBlue() * 4));
            }
        });

        return button;
    }

    private File getResourcesFolder() throws URISyntaxException {
        return new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "resources");
    }

    public void editUIManager() {
        UIManager.put("ToolTip.background", new Color(90, 90, 90));
        UIManager.put("ToolTip.foreground", new Color(89, 66, 56));
        Border border = BorderFactory.createLineBorder(new Color(198, 203, 201));
        UIManager.put("ToolTip.Border", border);
        ToolTipManager.sharedInstance().setDismissDelay(10000);
    }

}

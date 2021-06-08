import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The main window, which has a menu and surface
 *
 * @param menuBar     An implementation of a menu bar.
 * @param filMenu     menu items for call submenu which have submenu items
 * @param createMenu  menu items for call submenu which have submenu items
 * @param helpMenu    menu items for call submenu which have submenu items
 * @param loadItem    menu items for call function which load item
 * @param saveItem    menu items for call function which save item
 * @param createRec   menu items for call function which creat rectangle
 * @param createElip  menu items for call function which creat elipse
 * @param createTrian menu items for call function which creat rectangle
 * @param instruction menu items for call function which open instruction of
 *                    porgramm
 * @param clearItem   menu items for call function which clear sufrace
 * @param panel       stores the panel on which we draw figures
 */
public class MyFrame extends JFrame implements ActionListener {
    JMenuBar menuBar;
    JMenu filMenu, createMenu, helpMenu;
    JMenuItem loadItem, saveItem, createRec, createElip, createTrian, instruction, clearItem;
    static MyPanel panel;

    MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        setLocationRelativeTo(null);
        this.setTitle("MiniPaint");

        this.setBackground(Color.decode("#2c2c2c"));
        repaint();

        menuBar = new JMenuBar();

        filMenu = new JMenu("File");
        createMenu = new JMenu("Create");
        helpMenu = new JMenu("Help");

        menuBar.add(filMenu);
        menuBar.add(createMenu);
        menuBar.add(helpMenu);

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        clearItem = new JMenuItem("Clear");
        filMenu.add(loadItem);
        filMenu.add(saveItem);
        filMenu.add(clearItem);
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        clearItem.addActionListener(this);

        createRec = new JMenuItem("Create Rectangle");
        createElip = new JMenuItem("Create Circle");
        createTrian = new JMenuItem("Create Triangle");
        createMenu.add(createRec);
        createMenu.add(createElip);
        createMenu.add(createTrian);
        createRec.addActionListener(this);
        createElip.addActionListener(this);
        createTrian.addActionListener(this);

        instruction = new JMenuItem("Instruction");
        helpMenu.add(instruction);
        instruction.addActionListener(this);

        this.setJMenuBar(menuBar);
        this.setVisible(true);

        panel = new MyPanel(this);
        this.add(panel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            /**
             * call function which save item
             */
            case "Save":
                JFileChooser fileChooserSave = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
                int ifSelect = fileChooserSave.showSaveDialog(null);
                if (ifSelect == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileOutputStream FielOut = new FileOutputStream(
                                fileChooserSave.getSelectedFile().getAbsolutePath());
                        ObjectOutput ObjectOut = new ObjectOutputStream(FielOut);
                        ObjectOut.writeObject(panel.listaFigur);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                repaint();

                break;
            /**
             * call function which load item
             */
            case "Load":
                JFileChooser fileChooserLoad = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
                int isSelect = fileChooserLoad.showOpenDialog(null);
                if (isSelect == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileInputStream FileIn = new FileInputStream(
                                fileChooserLoad.getSelectedFile().getAbsolutePath());
                        ObjectInputStream ObjetIn = new ObjectInputStream(FileIn);
                        panel.listaFigur.clear();
                        panel.listaFigur = (ArrayList<Figura>) ObjetIn.readObject();
                    } catch (IOException | ClassNotFoundException ioException) {
                        ioException.printStackTrace();

                    }
                }
                repaint();
                break;
            /**
             * call function which clear surface
             */
            case "Clear":
                panel.listaFigur.clear();
                repaint();
                break;
            /**
             * call function which Create Rectangle
             */
            case "Create Rectangle":
                panel.deleteListener();
                panel.addMouseListener(panel.cretedRectan);
                panel.addMouseMotionListener(panel.cretedRectan);
                break;
            /**
             * call function which Create Circle
             */
            case "Create Circle":
                panel.deleteListener();
                panel.addMouseListener(panel.createElipse);
                panel.addMouseMotionListener(panel.createElipse);
                break;
            /**
             * call function which Create Triangle
             */
            case "Create Triangle":
                panel.deleteListener();
                panel.addMouseListener(panel.createTriangle);
                panel.addMouseMotionListener(panel.createTriangle);
                break;
            /**
             * call function which open instruction of porgramm
             */
            case "Instruction":
                Instruction instruction = new Instruction();
                instruction.setVisible(true);
                break;
            default:
                break;
        }

    }

}

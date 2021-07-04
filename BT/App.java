
/**
 * @author Kacper Zawojski
 * @param args stuff what you input in terminal
 * @return creatin a frame
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * App it's main surface to display all stuf
 * 
 * @param menuPanel    this is menuPanle
 * @param IntRoot      this is root of int tree root
 * @param DoubleRoot   this is root of double tree root
 * @param StringRoot   this is root of string tree root
 * @param finding      the varible which we want to fing or add or delete
 * @param addButton    button to add node
 * @param removeButton remove to add node
 * @param trybInt      choose type of tree
 * @param trybDouble   choose type of tree
 * @param trybString   choose type of tree
 * @return creatin a frame
 */

public class App extends Frame implements ActionListener {
    JPanel view;
    Panel menuPanel;
    MyTree<Integer> IntRoot;
    MyTree<Double> DoubleRoot;
    MyTree<String> StringRoot;
    TextArea finding;
    Button addButton, removeButton;
    ButtonGroup tryb = new ButtonGroup();
    JRadioButton trybInt, trybDouble, trybString;
    int skala = -1;
    double TransX = 0, TransY = 0;

    /**
     * Construcotr
     */
    App() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(600, 400);
        view = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }
        };
        menuPanel = new Panel();
        menuPanel.setLayout(new GridLayout(5, 1));
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.WEST);
        IntRoot = new MyTree<>();
        DoubleRoot = new MyTree<>();
        StringRoot = new MyTree<>();
        add(view, BorderLayout.CENTER);
        finding = new TextArea();
        menuPanel.add(finding);

        /**
         * Here we checke our typed node
         */
        finding.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    ButtonModel selection = tryb.getSelection();
                    if (trybInt.getModel().equals(selection)) {
                        if (IntRoot.contains(Integer.parseInt(finding.getText()))) {
                            finding.setBackground(Color.GREEN);
                        } else {
                            finding.setBackground(Color.RED);
                        }
                    } else if (trybDouble.getModel().equals(selection)) {
                        if (DoubleRoot.contains(Double.parseDouble(finding.getText()))) {
                            finding.setBackground(Color.GREEN);
                        } else {
                            finding.setBackground(Color.RED);
                        }
                    } else {
                        if (StringRoot.contains(finding.getText())) {
                            finding.setBackground(Color.GREEN);
                        } else {
                            finding.setBackground(Color.RED);
                        }
                    }

                } catch (NumberFormatException ex) {
                    finding.setBackground(Color.WHITE);
                }
            }
        });
        Panel forButtons = new Panel();
        forButtons.setLayout(new GridLayout(1, 2));
        addButton = new Button("Add Value");
        removeButton = new Button("Remove Value");
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        forButtons.add(addButton);
        forButtons.add(removeButton);
        menuPanel.add(forButtons);
        trybInt = new JRadioButton("Integer Tree");
        trybDouble = new JRadioButton("Double Tree");
        trybString = new JRadioButton("String Tree");
        tryb.add(trybInt);
        tryb.add(trybDouble);
        tryb.add(trybString);
        trybInt.addActionListener(this);
        trybDouble.addActionListener(this);
        trybString.addActionListener(this);
        trybInt.setSelected(true);
        menuPanel.add(trybInt);
        menuPanel.add(trybDouble);
        menuPanel.add(trybString);
        view.addMouseWheelListener(new Scaler());
        mover x = new mover();
        view.addMouseMotionListener(x);
        view.addMouseListener(x);
        setVisible(true);
    }

    /**
     * Draw our tree depends of that what we add
     */
    void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(view.getWidth() / 2, 30);
        double scaler = Math.pow(2, skala);
        g2d.scale(scaler, scaler);
        g2d.translate(TransX, TransY);
        Font MF = g2d.getFont();
        MF = MF.deriveFont((float) (14 / scaler));
        g2d.setFont(MF);
        g2d.setStroke(new BasicStroke((float) (3 / scaler)));
        ButtonModel selection = tryb.getSelection();
        if (trybInt.getModel().equals(selection)) {
            IntRoot.view(g2d, 0, 0, view.getWidth(), view.getHeight() / 3, 0, 0);
        } else if (trybDouble.getModel().equals(selection)) {
            DoubleRoot.view(g2d, 0, 0, view.getWidth(), view.getHeight() / 3, 0, 0);
        } else {
            StringRoot.view(g2d, 0, 0, view.getWidth(), view.getHeight() / 3, 0, 0);

        }
    }

    /**
     * When you use mouse scroll "change size of tree"
     */
    class Scaler implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            skala -= e.getWheelRotation();
            view.repaint();
        }
    }

    /**
     * Change tree position
     */
    class mover extends MouseAdapter {
        int x, y;

        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            TransX += (e.getX() - x) / Math.pow(2, skala);
            TransY += (e.getY() - y) / Math.pow(2, skala);
            x = e.getX();
            y = e.getY();
            view.repaint();
        }
    }

    /**
     * Adding and remoce nodes
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Add Value")) {
                ButtonModel selection = tryb.getSelection();
                if (trybInt.getModel().equals(selection)) {
                    IntRoot.add(Integer.parseInt(finding.getText()));
                } else if (trybDouble.getModel().equals(selection)) {
                    DoubleRoot.add(Double.parseDouble(finding.getText()));
                } else {
                    StringRoot.add(finding.getText());

                }
            } else if (e.getActionCommand().equals("Remove Value")) {
                ButtonModel selection = tryb.getSelection();
                if (trybInt.getModel().equals(selection)) {
                    IntRoot.remove(Integer.parseInt(finding.getText()));
                } else if (trybDouble.getModel().equals(selection)) {
                    DoubleRoot.remove(Double.parseDouble(finding.getText()));
                } else {
                    StringRoot.remove(finding.getText());
                }

            }
            view.repaint();
        } catch (NumberFormatException ignored) {
        }
        try {
            ButtonModel selection = tryb.getSelection();
            if (trybInt.getModel().equals(selection)) {
                if (IntRoot.contains(Integer.parseInt(finding.getText()))) {
                    finding.setBackground(Color.GREEN);
                } else {
                    finding.setBackground(Color.RED);
                }
            } else if (trybDouble.getModel().equals(selection)) {
                if (DoubleRoot.contains(Double.parseDouble(finding.getText()))) {
                    finding.setBackground(Color.GREEN);
                } else {
                    finding.setBackground(Color.RED);
                }
            } else {
                if (StringRoot.contains(finding.getText())) {
                    finding.setBackground(Color.GREEN);
                } else {
                    finding.setBackground(Color.RED);
                }
            }
        } catch (NumberFormatException ex) {
            finding.setBackground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        new App();
    }
}

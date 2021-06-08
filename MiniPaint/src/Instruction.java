
import javax.swing.JEditorPane;
import javax.swing.JFrame;

import java.awt.*;

import java.awt.Color;

/**
 * Instructions on how to use the porgram.
 *
 */
public class Instruction extends JFrame {

    Instruction() {
        this.setSize(800, 900);
        setLocationRelativeTo(null);
        this.setLayout(new GridLayout());
        this.setTitle("Instruction");

        JEditorPane textArea = new JEditorPane("text/html", "");
        textArea.setText(
                "<h1 style='text-align: center;'>Instruction of this program, called MiniPaint.</h1></p><br><br>"
                        + "<p style='font-size:12px;'><b>Creat Rectangle</b> - Choose in menu Creat and then Creat Rectangle. Next click(letf mouse button) somewhere and drag with the mouse and release.</p><br><br>"
                        + "<p style='font-size:12px;'><b>Creat Circle</b> - Choose in menu Creat and then Creat Circle. Next click(letf mouse button) somewhere and drag with the mouse and release.</p><br>"
                        + "<p style='font-size:12px; back'><p style='font-size:12px;'><b>Creat Triangle</b> - Choose in menu Creat and then Creat Triangle. Next click three points on panel and traingle schould creat.</p><br><br></p>"
                        + "<p style='font-size:12px;'><b>Save and Load</b> - Choose in menu File and then Save/ Load and choose folder and   save/load file. The best option is save in txt format. Example.txt</p><br><br>"
                        + "<p style='font-size:12px;'><b>Clear surface</b> - Choose in menu File and then click Clear.</p><br><br>"
                        + "<p style='font-size:12px;'><b>Change color</b> - First click(left mouse button) on figure wchih you want to change color and than click right mouse button.</p><br><br>"
                        + "<p style='font-size:12px;'><b>Scaling figure</b> - First click(left mouse button) on figure wchih you want to scale and then use mouse scroll.</p><br><br>"
                        + "<p style='font-size:12px;'><b>Change position of figure</b> - First click(left mouse button) on figure wchih   you want to drag and then press left mouse button and drag with the mouse and   release.</p><br><br>"
                        + "<p style='font-size:12px;'><b>Mirroring </b> - scroll to make it smaller, when it reduces to zero it will start to mirror. <span style='color:red;'>Only in Triangle!<span></p><br><br>");
        this.add(textArea, BorderLayout.CENTER);

    }
}

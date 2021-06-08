import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.Random;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

/**
 * Represents an employee.
 * 
 * @param isActiveBtn check tahis is btn active
 * @param Btnox       where is the button in ox
 * @param Btnoy       where is the button in oy
 */
public class CreatBtn extends JButton {
    public Boolean isActiveBtn = true;
    public int Btnox;
    public int Btnoy;

    /**
     * Creates an button.
     * 
     * @param r Shade of red.
     * @param g Shade of green.
     * @param b Shade of blue.
     */
    CreatBtn(int nr, int x, int y) {
        this.Btnox = x;
        this.Btnoy = y;
        float r = MyGridLayout.rand.nextFloat();
        float g = MyGridLayout.rand.nextFloat();
        float b = MyGridLayout.rand.nextFloat();
        Border emptyBorder = BorderFactory.createEmptyBorder();
        this.setBorder(emptyBorder);
        this.setBackground(new java.awt.Color(r, g, b));

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                isActiveBtn = !isActiveBtn;
                WhatIfActive(isActiveBtn);
            }
        });
    }

    /**
     * Do somethnik when is active.
     */
    public void WhatIfActive(boolean isActiveBtn) {
        if (isActiveBtn == false) {
            setBorder(new LineBorder(Color.BLACK));

            MyGridLayout.thread[Btnox][Btnoy].isActive = !MyGridLayout.thread[Btnox][Btnoy].isActive;
        } else {
            Border emptyBorder = BorderFactory.createEmptyBorder();
            setBorder(emptyBorder);

            MyGridLayout.thread[Btnox][Btnoy].isActive = !MyGridLayout.thread[Btnox][Btnoy].isActive;

        }
    }

}

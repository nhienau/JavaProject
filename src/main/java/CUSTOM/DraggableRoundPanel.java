/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CUSTOM;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class DraggableRoundPanel extends JPanel {
    private int arcWidth = 20;
    private int arcHeight = 20;
    private Point initialClick;
 
    public DraggableRoundPanel() {
        super();
 
        //Thêm listener để cho phép kéo thả
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Lấy vị trí hiện tại của panel
                int thisX = getLocation().x;
                int thisY = getLocation().y;
 
                // Tính toán vị trí mới
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int x = thisX + xMoved;
                int y = thisY + yMoved;
 
                // Di chuyển panel đến vị trí mới
                setLocation(x, y);
            }
        });
    }
 
    public DraggableRoundPanel(LayoutManager layout) {
        super(layout);
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(arcWidth, arcHeight);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }
 
    @Override
    public void setOpaque(boolean isOpaque) {
    }
}

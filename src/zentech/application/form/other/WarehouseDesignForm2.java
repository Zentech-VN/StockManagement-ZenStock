/*
 * Warehouse Design Form - Improved Version
 */
package zentech.application.form.other;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Duc Pham Ngoc
 */
public class WarehouseDesignForm2 extends javax.swing.JPanel {
    
    private int mouseOffsetX, mouseOffsetY;
    private Map<String, ImageIcon> iconMap;
    private JLabel draggedLabel = null;
    private boolean isDragging = false;
    
    public WarehouseDesignForm2() {
        initComponents();
        setupIcons();
        setupPanelIcons();
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        
        // Setup canvas để nhận drop
        setupCanvasDropTarget();
    }
    
    private void setupIcons() {
        iconMap = new HashMap<>();
        
        // Tạo các icon đơn giản bằng BufferedImage
        iconMap.put("storage", createIcon("Khu lưu trữ", Color.LIGHT_GRAY, 60, 40));
        iconMap.put("shelf", createIcon("Kệ hàng", Color.ORANGE, 50, 80));
        iconMap.put("forklift", createIcon("Xe nâng", Color.BLUE, 40, 30));
        iconMap.put("conveyor", createIcon("Băng tải", Color.GREEN, 80, 20));
        iconMap.put("dock", createIcon("Bến tải", Color.RED, 60, 60));
        iconMap.put("office", createIcon("Văn phòng", Color.YELLOW, 50, 50));
    }
    
    private ImageIcon createIcon(String text, Color color, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Vẽ hình chữ nhật với màu
        g2.setColor(color);
        g2.fillRect(2, 2, width-4, height-4);
        g2.setColor(Color.BLACK);
        g2.drawRect(2, 2, width-4, height-4);
        
        // Vẽ text
        g2.setFont(new Font("Arial", Font.BOLD, 8));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        
        if (textWidth < width && textHeight < height) {
            int x = (width - textWidth) / 2;
            int y = (height - textHeight) / 2 + fm.getAscent();
            g2.drawString(text, x, y);
        }
        
        g2.dispose();
        return new ImageIcon(img);
    }
    
    private void setupPanelIcons() {
        jPanel1.removeAll();
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        String[] iconKeys = {"storage", "shelf", "forklift", "conveyor", "dock", "office"};
        String[] labels = {"Khu lưu trữ", "Kệ hàng", "Xe nâng", "Băng tải", "Bến tải", "Văn phòng"};
        
        for (int i = 0; i < iconKeys.length; i++) {
            gbc.gridy = i;
            gbc.gridx = 0;
            
            JLabel iconLabel = new JLabel(iconMap.get(iconKeys[i]));
            iconLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            iconLabel.setToolTipText(labels[i]);
            iconLabel.putClientProperty("iconKey", iconKeys[i]);
            
            // Thêm mouse listener cho drag
            addDragListenerToIcon(iconLabel);
            
            JPanel iconPanel = new JPanel(new BorderLayout());
            iconPanel.add(iconLabel, BorderLayout.CENTER);
            iconPanel.add(new JLabel(labels[i], JLabel.CENTER), BorderLayout.SOUTH);
            
            jPanel1.add(iconPanel, gbc);
        }
        
        jPanel1.revalidate();
        jPanel1.repaint();
    }
    
    private void addDragListenerToIcon(JLabel iconLabel) {
        iconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseOffsetX = e.getX();
                mouseOffsetY = e.getY();
                isDragging = true;
                
                // Tạo label clone để drag
                String iconKey = (String) iconLabel.getClientProperty("iconKey");
                ImageIcon icon = iconMap.get(iconKey);
                draggedLabel = new JLabel(icon);
                draggedLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
                
                // Thêm vào main panel để có thể di chuyển qua canvas
                WarehouseDesignForm2.this.add(draggedLabel, 0);
                Point panelPoint = SwingUtilities.convertPoint(iconLabel.getParent(), 
                    iconLabel.getLocation(), WarehouseDesignForm2.this);
                draggedLabel.setLocation(panelPoint.x + e.getX() - mouseOffsetX, 
                                       panelPoint.y + e.getY() - mouseOffsetY);
                draggedLabel.setVisible(true);
                WarehouseDesignForm2.this.repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDragging && draggedLabel != null) {
                    // Kiểm tra xem có drop vào canvas không
                    Point canvasPoint = SwingUtilities.convertPoint(WarehouseDesignForm2.this, 
                        draggedLabel.getLocation(), canvas1);
                    
                    if (canvas1.getBounds().contains(canvasPoint.x, canvasPoint.y)) {
                        // Drop vào canvas - tạo phần tử mới trên canvas
                        addElementToCanvas(draggedLabel.getIcon(), canvasPoint.x, canvasPoint.y);
                    }
                    
                    // Xóa drag label tạm thời
                    WarehouseDesignForm2.this.remove(draggedLabel);
                    WarehouseDesignForm2.this.repaint();
                    draggedLabel = null;
                    isDragging = false;
                }
            }
        });
        
        iconLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging && draggedLabel != null) {
                    Point panelPoint = SwingUtilities.convertPoint(iconLabel.getParent(), 
                        iconLabel.getLocation(), WarehouseDesignForm2.this);
                    draggedLabel.setLocation(panelPoint.x + e.getX() - mouseOffsetX, 
                                           panelPoint.y + e.getY() - mouseOffsetY);
                    WarehouseDesignForm2.this.repaint();
                }
            }
        });
    }
    
    private void setupCanvasDropTarget() {
        canvas1.setLayout(null); // Absolute positioning
    }
    
    private void addElementToCanvas(Icon icon, int x, int y) {
        JLabel element = new JLabel(icon);
        element.setSize(icon.getIconWidth(), icon.getIconHeight());
        element.setLocation(x, y);
        element.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        element.setOpaque(true);
        element.setBackground(Color.WHITE);
        
        // Tạo popup menu cho phần tử trên canvas
        JPopupMenu popup = new JPopupMenu();
        JMenuItem rotateItem = new JMenuItem("Xoay hình");
        JMenuItem deleteItem = new JMenuItem("Xóa");
        popup.add(rotateItem);
        popup.add(deleteItem);
        
        // Xử lý xoay
        rotateItem.addActionListener(ae -> {
            rotateElement(element);
        });
        
        // Xử lý xóa
        deleteItem.addActionListener(ae -> {
            canvas1.remove(element);
            canvas1.revalidate();
            canvas1.repaint();
        });
        
        // Mouse listeners cho phần tử trên canvas
        element.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popup.show(element, e.getX(), e.getY());
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mouseOffsetX = e.getX();
                    mouseOffsetY = e.getY();
                    element.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                element.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
        });
        
        element.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = element.getX() + e.getX() - mouseOffsetX;
                int newY = element.getY() + e.getY() - mouseOffsetY;
                
                // Giới hạn trong canvas
                if (newX < 0) newX = 0;
                if (newY < 0) newY = 0;
                if (newX + element.getWidth() > canvas1.getWidth()) {
                    newX = canvas1.getWidth() - element.getWidth();
                }
                if (newY + element.getHeight() > canvas1.getHeight()) {
                    newY = canvas1.getHeight() - element.getHeight();
                }
                
                element.setLocation(newX, newY);
            }
        });
        
        canvas1.add(element);
        canvas1.revalidate();
        canvas1.repaint();
    }
    
    private void rotateElement(JLabel element) {
        Icon ic = element.getIcon();
        if (ic instanceof ImageIcon) {
            ImageIcon imgIcon = (ImageIcon) ic;
            Image img = imgIcon.getImage();
            int w = imgIcon.getIconWidth();
            int h = imgIcon.getIconHeight();
            
            BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = buf.createGraphics();
            g2.drawImage(img, 0, 0, null);
            g2.dispose();
            
            BufferedImage rot = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = rot.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.translate(h/2.0, w/2.0);
            g2d.rotate(Math.toRadians(90));
            g2d.translate(-w/2.0, -h/2.0);
            g2d.drawImage(buf, 0, 0, null);
            g2d.dispose();
            
            element.setIcon(new ImageIcon(rot));
            element.setSize(rot.getWidth(), rot.getHeight());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        canvas1 = new javax.swing.JPanel(); // Changed from Canvas to JPanel for better component support

        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 400));
        jPanel1.setBorder(BorderFactory.createTitledBorder("Chọn biểu tượng"));

        canvas1.setBackground(new java.awt.Color(255, 255, 255));
        canvas1.setBorder(BorderFactory.createTitledBorder("Khu vực thiết kế"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JPanel canvas1; // Changed from Canvas to JPanel
    private javax.swing.JPanel jPanel1;
    // End of variables declaration
}
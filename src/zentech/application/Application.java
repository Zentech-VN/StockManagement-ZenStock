package zentech.application;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import entity.Employee;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import entity.Activity;
import java.time.LocalDateTime;

import zentech.application.form2.MainForm;
import raven.toast.Notifications;

public class Application extends javax.swing.JFrame {

    private static Application app;
    private MainForm mainForm;

    private static Login l;
    private static Employee acccurent;
    private String currentUser;

    public static Application getAppInstance(){
        return app;
    }

    public void setCurrentUser(String user){
        this.currentUser = user;
    }

    public String getCurrentUser(){
        return this.currentUser;
    }

    public Application(Employee acc, long startTime) {
        this.acccurent = acc;
        app = this;
        initComponents();
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        mainForm = new MainForm(this.acccurent);
        setContentPane(mainForm);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Notifications.getInstance().setJFrame(this);
       
         
        System.out.println("Loading time: " + (System.currentTimeMillis() - startTime) + " ms");

    }

    public static void showForm(Component component) {
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
    }

    public static void login() {
        FlatAnimatedLafChange.showSnapshot();
        app.setContentPane(app.mainForm);
        app.mainForm.applyComponentOrientation(app.getComponentOrientation());
        setSelectedMenu(0, 0);
        app.mainForm.hideMenu();
        SwingUtilities.updateComponentTreeUI(app.mainForm);
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void logout() {
        SwingUtilities.invokeLater(() -> {
            String user = app.getCurrentUser();
            app.dispose();
            l = new Login();
            l.setLocationRelativeTo(null);
            l.setVisible(true);
        });
    }

    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }

    // Method để lấy thông tin user hiện tại
    public static Employee getCurrentUserLog() {
        return acccurent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("zentech.theme");
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        FlatMacDarkLaf.setup();
//        java.awt.EventQueue.invokeLater(() -> {
//            Login l = new Login();
//            l.setVisible(true);
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

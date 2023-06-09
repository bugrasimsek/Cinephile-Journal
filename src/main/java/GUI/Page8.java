
package GUI;

import System.SystemClass;


public class Page8 extends javax.swing.JFrame {

    /**
     * Creates new form Page8
     */
    public Page8() {
        initComponents();
        this.welcomeLabel.setText(SystemClass.userList.get(0).getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoutBT = new javax.swing.JLabel();
        exitBT = new javax.swing.JLabel();
        abousUsBT = new javax.swing.JLabel();
        whatShouldIWatchListBT = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        toWatchListBT = new javax.swing.JLabel();
        watchedListBT = new javax.swing.JLabel();
        page = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logoutBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logoutBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutBTMouseClicked(evt);
            }
        });
        getContentPane().add(logoutBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 476, 70, 20));

        exitBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitBTMouseClicked(evt);
            }
        });
        getContentPane().add(exitBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 270, 40));

        abousUsBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        abousUsBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abousUsBTMouseClicked(evt);
            }
        });
        getContentPane().add(abousUsBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 270, 40));

        whatShouldIWatchListBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        whatShouldIWatchListBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                whatShouldIWatchListBTMouseClicked(evt);
            }
        });
        getContentPane().add(whatShouldIWatchListBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 270, 40));

        welcomeLabel.setFont(new java.awt.Font("Montserrat Medium", 2, 24)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 230, 30));

        toWatchListBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        toWatchListBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toWatchListBTMouseClicked(evt);
            }
        });
        getContentPane().add(toWatchListBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 270, 40));

        watchedListBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        watchedListBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                watchedListBTMouseClicked(evt);
            }
        });
        getContentPane().add(watchedListBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 270, 40));

        page.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        page.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/8.png"))); // NOI18N
        page.setOpaque(true);
        getContentPane().add(page, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutBTMouseClicked
        Page1 page1=new Page1();
        page1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutBTMouseClicked

    private void exitBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitBTMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitBTMouseClicked

    private void abousUsBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abousUsBTMouseClicked
        this.setVisible(true);
    }//GEN-LAST:event_abousUsBTMouseClicked

    private void whatShouldIWatchListBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_whatShouldIWatchListBTMouseClicked
        Page7 page5=new Page7();
        page5.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_whatShouldIWatchListBTMouseClicked

    private void toWatchListBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toWatchListBTMouseClicked
        Page4 page4 = new Page4();
       page4.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_toWatchListBTMouseClicked

    private void watchedListBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_watchedListBTMouseClicked
        Page3 page3=new Page3();
        page3.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_watchedListBTMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Page8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Page8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Page8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Page8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Page8().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abousUsBT;
    private javax.swing.JLabel exitBT;
    private javax.swing.JLabel logoutBT;
    private javax.swing.JLabel page;
    private javax.swing.JLabel toWatchListBT;
    private javax.swing.JLabel watchedListBT;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel whatShouldIWatchListBT;
    // End of variables declaration//GEN-END:variables
}

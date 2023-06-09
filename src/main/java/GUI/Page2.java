
package GUI;

import LoginSignUp.Login;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class Page2 extends javax.swing.JFrame {

    public Page2() {
        initComponents();

        clickheretosignup.setText("Not registered? Click here to sign up.");

        usernameTextField.setOpaque(false);
        usernameTextField.setBackground(new java.awt.Color(255, 255, 255, 0));
        usernameTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        passwordField.setOpaque(false);
        passwordField.setBackground(new java.awt.Color(255, 255, 255, 0));
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        //Placeholder
        usernameTextField.setText("Username");
        usernameTextField.setForeground(new Color(102, 102, 102));
        usernameTextField.setFont(new Font("Monsterrat", Font.ITALIC, 14));

        passwordField.setText("Password");
        passwordField.setForeground(new Color(102, 102, 102));
        passwordField.setFont(new Font("Monsterrat", Font.ITALIC, 14));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitEnterBT = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        loginBT = new javax.swing.JLabel();
        clickheretosignup = new javax.swing.JLabel();
        page = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitEnterBT.setToolTipText("");
        exitEnterBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitEnterBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitEnterBTMouseClicked(evt);
            }
        });
        getContentPane().add(exitEnterBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 30, 30));

        usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextFieldMouseClicked(evt);
            }
        });
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyTyped(evt);
            }
        });
        getContentPane().add(usernameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 210, -1));

        passwordField.setText("jPasswordField1");
        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldMouseClicked(evt);
            }
        });
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 210, -1));

        loginBT.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        loginBT.setForeground(new java.awt.Color(255, 255, 255));
        loginBT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBTMouseClicked(evt);
            }
        });
        getContentPane().add(loginBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 390, 90, 30));

        clickheretosignup.setFont(new java.awt.Font("Montserrat Medium", 0, 10)); // NOI18N
        clickheretosignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clickheretosignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickheretosignupMouseClicked(evt);
            }
        });
        getContentPane().add(clickheretosignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 270, 40));

        page.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        page.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/2.png"))); // NOI18N
        page.setOpaque(true);
        getContentPane().add(page, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitEnterBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitEnterBTMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitEnterBTMouseClicked

    private void loginBTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBTMouseClicked
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        Page3 page3 = null;
        boolean didLogin = false;
        try {
            didLogin = Login.login(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (didLogin) {
            page3 = new Page3();
            page3.setVisible(true);
            this.dispose();
        }
        
        else if(!didLogin){
            clickheretosignup.setForeground(new Color(204, 0, 51));
            clickheretosignup.setFont(new Font("Montserrat Medium", Font.BOLD, 10));
            clickheretosignup.setText("Username or password incorrect, please signup!");
        }

        //bu ekranı dispose'la, mainFrame'i new'le, hatta bunu yapan login() fonk'u yazılabilir
    }//GEN-LAST:event_loginBTMouseClicked

    private void clickheretosignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickheretosignupMouseClicked
        Page1 page1 = new Page1();
        page1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_clickheretosignupMouseClicked

    private void usernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameTextFieldMouseClicked
        if (usernameTextField.getText().equals("Username")) {
            usernameTextField.setText("");
            usernameTextField.setForeground(Color.BLACK);
            usernameTextField.setFont(new Font("Monsterrat", Font.PLAIN, 14));
        }
    }//GEN-LAST:event_usernameTextFieldMouseClicked

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed

    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void usernameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyTyped
        if (usernameTextField.getText().equals("Username")) {
            usernameTextField.setText("");
            usernameTextField.setForeground(Color.BLACK);
            usernameTextField.setFont(new Font("Monsterrat", Font.PLAIN, 14));
        }
    }//GEN-LAST:event_usernameTextFieldKeyTyped

    private void passwordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldMouseClicked
        if (String.valueOf(passwordField.getPassword()).equals("Password")) {
            passwordField.setText("");
            passwordField.setForeground(Color.BLACK);
            passwordField.setFont(new Font("Monsterrat", Font.PLAIN, 14));
        }
    }//GEN-LAST:event_passwordFieldMouseClicked

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if (String.valueOf(passwordField.getPassword()).equals("Password")) {
            passwordField.setText("");
            passwordField.setForeground(Color.BLACK);
            passwordField.setFont(new Font("Monsterrat", Font.PLAIN, 14));
        }
    }//GEN-LAST:event_passwordFieldKeyPressed

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
            java.util.logging.Logger.getLogger(Page2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Page2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Page2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Page2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Page2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clickheretosignup;
    private javax.swing.JLabel exitEnterBT;
    private javax.swing.JLabel loginBT;
    private javax.swing.JLabel page;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}

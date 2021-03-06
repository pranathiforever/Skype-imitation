/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skype;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
//import skype.Home.responseLine;

/**
 *
 * @author listeruser
 */
public class Login extends javax.swing.JFrame {
    
    BufferedReader inputLine;
	Socket ClientSocket;
	boolean closed=false;
	static PrintStream out;
	static DataInputStream in;
        
	DataInputStream is;
	static Socket echoSocket;
	public static String skypeid;
        public static String result1;
	

    /**
     * Creates new form SkypeFrame
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CreateNew = new javax.swing.JButton();
        Signin = new javax.swing.JButton();
        IdLabel = new javax.swing.JLabel();
        PwdLabel = new javax.swing.JLabel();
        WelcomeLabel = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ErrorLabel = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(756, 472));

        CreateNew.setBackground(new java.awt.Color(255, 255, 255));
        CreateNew.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        CreateNew.setForeground(new java.awt.Color(68, 163, 203));
        CreateNew.setText(" Create new account");
        CreateNew.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        CreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateNewActionPerformed(evt);
            }
        });

        Signin.setBackground(new java.awt.Color(255, 255, 255));
        Signin.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        Signin.setForeground(new java.awt.Color(68, 163, 203));
        Signin.setText("Sign in");
        Signin.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SigninActionPerformed(evt);
            }
        });

        IdLabel.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        IdLabel.setForeground(new java.awt.Color(254, 247, 247));
        IdLabel.setText("Skype Name");

        PwdLabel.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        PwdLabel.setForeground(new java.awt.Color(255, 255, 255));
        PwdLabel.setText("Password");

        WelcomeLabel.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        WelcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeLabel.setText("Welcome to Skype++");

        jTextField6.setFont(new java.awt.Font("Ubuntu Light", 0, 16)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField6.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo_mod.png"))); // NOI18N

        ErrorLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        ErrorLabel.setForeground(new java.awt.Color(200, 59, 59));

        jPasswordField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Main_bg.jpeg"))); // NOI18N
        background.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(Signin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CreateNew)
                .addGap(95, 95, 95))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(WelcomeLabel)
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addComponent(IdLabel)
                        .addComponent(PwdLabel)
                        .addComponent(jPasswordField2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(background)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(WelcomeLabel)
                        .addGap(30, 30, 30)
                        .addComponent(IdLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(PwdLabel)
                        .addGap(2, 2, 2)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Signin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(ErrorLabel)
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(background)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateNewActionPerformed
        // TODO add your handling code here:\
        boolean flag=true;
       
        if(flag){
            try {
                this.dispose();
                CreateAcc l=new CreateAcc();
                l.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_CreateNewActionPerformed

    private void SigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SigninActionPerformed
        try {
            boolean flag=false;
            String skypename = jTextField6.getText();
            skypeid=skypename;
            char[] password = jPasswordField2.getPassword();
            String pass = new String(password);
            
            out.println(skypename+" "+pass+" 2fff2C");
            String result = in.readLine();
            
            
            if(result.contains("sucess"))
            {
                flag = true;
                ErrorLabel.setText("");
            }
            else{
                ErrorLabel.setText("Wrong credentials. Try again"); 
            }
            if(flag){
                this.dispose();
                 out.println(skypeid+" 4fff2C");
                result1 = in.readLine();
                
                Home l=new Home(out,in);
                l.setVisible(true);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SigninActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
         String hostName = args[0];
            int portNumber = Integer.parseInt(args[1]);
            echoSocket = new Socket(hostName, portNumber);
             out =
                    new PrintStream(echoSocket.getOutputStream(), true);
                 in =
                    new DataInputStream(echoSocket.getInputStream());
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
           
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateNew;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JLabel IdLabel;
    private javax.swing.JLabel PwdLabel;
    private javax.swing.JButton Signin;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}

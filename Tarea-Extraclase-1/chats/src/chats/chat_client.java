package chats;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class chat_client extends javax.swing.JFrame {

    static Socket socket;
    static DataInputStream dinput;
    static DataOutputStream doutput;
    static int port;
    static String str_port = String.valueOf(port);
    
    
    public chat_client() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        message_area = new javax.swing.JTextArea();
        message_text = new javax.swing.JTextField();
        message_send = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        port_numL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        message_area.setColumns(20);
        message_area.setRows(5);
        jScrollPane1.setViewportView(message_area);

        message_text.setText("Message");
        message_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                message_textActionPerformed(evt);
            }
        });

        message_send.setText("Send");
        message_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                message_sendActionPerformed(evt);
            }
        });

        jLabel1.setText("Client Chat");

        port_numL.setText("Port: " + str_port);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(message_text, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(message_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(port_numL)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(port_numL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(message_text, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(message_send, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void message_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_sendActionPerformed
        try{
            String messageout = "";
            messageout = message_text.getText().trim();
            if (messageout.isEmpty()){
                throw new InvalidTextException();
                
            }else{
                doutput.writeUTF(messageout);
                message_area.setText(message_area.getText().trim()+"\n Client:\t"+messageout);
                message_text.setText("");
            }
  
            }catch(InvalidTextException e){
                System.out.println(e.getMessage());
        } catch (Exception e) {
            ///Exceptions
        }
    }//GEN-LAST:event_message_sendActionPerformed

    private void message_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_textActionPerformed
        
    }//GEN-LAST:event_message_textActionPerformed

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un puerto: ");
        str_port = sc.nextLine();
        System.out.println("El puerto ingresado es: " + str_port);
        port = Integer.valueOf(str_port);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_client().setVisible(true);
            }
        });
        
        
        
        try{
            
            socket = new Socket("127.0.0.1", port);
            dinput = new DataInputStream(socket.getInputStream());
            doutput = new DataOutputStream(socket.getOutputStream());
            String messagin = "";
            while(!messagin.equals("exit")){
                messagin = dinput.readUTF();
                message_area.setText(message_area.getText().trim()+"\n Server:\t"+messagin);
            }
        }catch(SocketException e){
            //Exceptions
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea message_area;
    private javax.swing.JButton message_send;
    private javax.swing.JTextField message_text;
    private javax.swing.JLabel port_numL;
    // End of variables declaration//GEN-END:variables
}

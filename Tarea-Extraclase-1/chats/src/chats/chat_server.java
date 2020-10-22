package chats;

import static chats.chat_client.messageout_lowercase;
import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.List;
import java.util.Arrays;


public class chat_server extends javax.swing.JFrame {

    static ServerSocket ss;
    static Socket socket;
    static DataInputStream dinput;
    static DataOutputStream doutput;
    
    static Random rand = new Random();
    static int port = rand.nextInt(65535-1080+1)+1080;
    static String str_port = String.valueOf(port);
    static String messageout_lowercase = "";
    String[] curseWords = {"nigga","nigger","fuck","shit","bitch","faggot"};
    List<String> curseWordsList = Arrays.asList(curseWords);
    
            
    
    public chat_server() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        message_area = new javax.swing.JTextArea();
        message_text = new javax.swing.JTextField();
        message_send = new javax.swing.JButton();
        server_label = new javax.swing.JLabel();
        port_numL = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

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

        server_label.setText("Server Chat");

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
                        .addComponent(server_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(port_numL)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(server_label)
                    .addComponent(port_numL, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(message_text)
                    .addComponent(message_send, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void message_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_sendActionPerformed
        try{
            
            String messageout = "";
            messageout = message_text.getText().trim();
            messageout_lowercase = messageout.toLowerCase();

            if (messageout.isEmpty()){
                throw new InvalidTextException_Server("Blank message was attempted to be sent.");
            
            }else if (messageout.length() > 64){
                throw new InvalidTextException_Server("The message exceeds the maximum amount of characters.");
            
            }else if (curseWordsList.stream().anyMatch(messageout_lowercase::contains)){
                throw new InvalidTextException_Server("The message contains a prohibited word");
            
            }else{
                doutput.writeUTF(messageout);
                message_area.setText(message_area.getText().trim()+"\n Server:\t"+messageout);
                message_text.setText("");
                }
            
            
            }catch (InvalidTextException_Server e){
                System.out.println(e.getMessage());
        }catch(Exception e){
            //Exceptions
        }
    }//GEN-LAST:event_message_sendActionPerformed

    private void message_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_message_textActionPerformed

    }//GEN-LAST:event_message_textActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chat_server().setVisible(true);
            }
        });
        
        String messagein = "";

        try{
            
            ss = new ServerSocket(port);
            socket = ss.accept();
            
            dinput = new DataInputStream(socket.getInputStream());
            doutput = new DataOutputStream(socket.getOutputStream());
            
            while(!messagein.equals("exit")){
                messagein = dinput.readUTF();
                message_area.setText(message_area.getText().trim()+"\n Client:\t"+messagein);
                 
            }
            
        }catch(Exception e){
            //Exceptions
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private static javax.swing.JTextArea message_area;
    private javax.swing.JButton message_send;
    private javax.swing.JTextField message_text;
    private javax.swing.JLabel port_numL;
    private javax.swing.JLabel server_label;
    // End of variables declaration//GEN-END:variables
}

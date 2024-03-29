/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.natoneers.client;

/**
 *
 * @author jermukuokkanen
 */


import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author jermukuokkanen
 */
public class GUIServer extends javax.swing.JFrame implements WindowListener {

    private static final long serialVersionUID = 1L;
    private ServerSetUp server;

    int posX = 0, posY = 0;
    
    InetAddress ipAddr;

    /**
     * Creates new form GUIServer
     */
    public GUIServer() {
        initComponents();

        server = null;

        //appendRoom("Chat room.\n");
        chat.setText("Chat Room \n");
        //appendEvent("Events log.\n");
        event.setText("Event Log \n");

        // need to be informed when the user click the close button on the frame
        mulaiJam();

        
        
        
        
    }
    
    public String getIP(){
        try {
            ipAddr = InetAddress.getLocalHost();
            System.out.println(ipAddr.getHostAddress());
            
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return ipAddr.getHostAddress()+"";
    }
    
    public void startingServer(){
        // if running we have to stop
        if (server != null) {
            server.stop();
            server = null;
            tPortNumber.setEditable(true);
            stopStart.setText("START");
            return;
        }
        // OK start the server	
        int port;
        try {
            port = Integer.parseInt(tPortNumber.getText().trim());
        } catch (Exception er) {
            appendEvent("Invalid port number");
            return;
        }
        // ceate a new ServerSetUp
        server = new ServerSetUp(port, this);
        // and start it as a thread
        new ServerRunning().start();
        stopStart.setText("STOP");
        tPortNumber.setEditable(false);
    }

    

    public Icon setIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    // append message to the two JTextArea
    // position at the end
    void appendRoom(String str) {
        chat.append(str);
        chat.setCaretPosition(chat.getText().length() - 1);
    }

    void appendEvent(String str) {
        event.append(str);
        event.setCaretPosition(event.getText().length() - 1);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        InputPanel = new javax.swing.JPanel();
        tPortNumber = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        stopStart = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        event = new javax.swing.JTextArea();
        userLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NATOneers CLue-Less CHAT SERVER AND LOG");

        InputPanel.setBackground(new java.awt.Color(204, 204, 204));
        InputPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                InputPanelMousePressed(evt);
            }
        });

        tPortNumber.setBackground(new java.awt.Color(204, 204, 204));
        tPortNumber.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        tPortNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tPortNumber.setText("59001");
        tPortNumber.setAlignmentX(0.0F);
        tPortNumber.setAlignmentY(0.0F);
        tPortNumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        tPortNumber.setPreferredSize(new java.awt.Dimension(0, 25));
        tPortNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tPortNumberKeyPressed(evt);
            }
        });

        userLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        userLabel.setText("PORT NUMBER");

        timeLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        timeLabel.setText("TIME");
        timeLabel.setAlignmentY(0.0F);
        timeLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));

        stopStart.setBackground(new java.awt.Color(0, 0, 0));
        stopStart.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        stopStart.setText("START");
        stopStart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        stopStart.setContentAreaFilled(false);
        stopStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stopStartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stopStartMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                stopStartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                stopStartMouseReleased(evt);
            }
        });
        stopStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopStartActionPerformed(evt);
            }
        });

        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane1.setViewportView(chat);

        event.setColumns(20);
        event.setRows(5);
        jScrollPane2.setViewportView(event);

        userLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        userLabel1.setText("Chat History");

        javax.swing.GroupLayout InputPanelLayout = new javax.swing.GroupLayout(InputPanel);
        InputPanel.setLayout(InputPanelLayout);
        InputPanelLayout.setHorizontalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InputPanelLayout.createSequentialGroup()
                        .addComponent(userLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InputPanelLayout.createSequentialGroup()
                        .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InputPanelLayout.createSequentialGroup()
                                .addComponent(userLabel)
                                .addGap(192, 192, 192)
                                .addComponent(tPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(stopStart, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InputPanelLayout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))))
        );
        InputPanelLayout.setVerticalGroup(
            InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InputPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(tPortNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopStart, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timeLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(InputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>                        

    private void tPortNumberKeyPressed(java.awt.event.KeyEvent evt) {                                       
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            startingServer();
        }//No
    }                                      

    private void stopStartMouseEntered(java.awt.event.MouseEvent evt) {                                       
        stopStart.setBackground(userLabel.getForeground());
        stopStart.setForeground(InputPanel.getBackground());
        stopStart.setOpaque(true);
    }                                      

    private void stopStartMouseExited(java.awt.event.MouseEvent evt) {                                      
        stopStart.setBackground(InputPanel.getBackground());
        stopStart.setForeground(userLabel.getForeground());
    }                                     

    private void stopStartMousePressed(java.awt.event.MouseEvent evt) {                                       
        stopStart.setBackground(Color.GRAY);
        stopStart.setOpaque(true);
    }                                      

    private void stopStartMouseReleased(java.awt.event.MouseEvent evt) {                                        
        stopStart.setBackground(InputPanel.getBackground());
        stopStart.setForeground(userLabel.getForeground());
    }                                       

    private void stopStartActionPerformed(java.awt.event.ActionEvent evt) {                                          
        startingServer();
    }                                         

    private void InputPanelMousePressed(java.awt.event.MouseEvent evt) {                                        

    }                                       

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
            java.util.logging.Logger.getLogger(GUIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIServer().setVisible(true);

            }
        });
    }
    

    public void mulaiJam() {
        Date skrg = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMM yyyy");
        SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
        timeLabel.setText(jam.format(skrg)+" | "+tgl.format(skrg));
        //timeLabel.setText(tgl.format(skrg));
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        // if my ServerSetUp exist
        if (server != null) {
            try {
                server.stop();			// ask the server to close the conection
            } catch (Exception eClose) {
            }
            server = null;
        }
        // dispose the frame
        dispose();
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
	 * A thread to run the ServerSetUp
     */
    class ServerRunning extends Thread {
        private SimpleDateFormat sdf;
        
        public void run() {
            server.start();         // should execute until if fails
            // the server failed
            stopStart.setText("START");
            tPortNumber.setEditable(true);
            
            String time = sdf.format(new Date()) + " ";
            appendEvent(time + " Server Destroyed and Closed\n");
            server = null;
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel InputPanel;
    private javax.swing.JTextArea chat;
    private javax.swing.JTextArea event;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton stopStart;
    private javax.swing.JTextField tPortNumber;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel userLabel1;
    // End of variables declaration                   
}

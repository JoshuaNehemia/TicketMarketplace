/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FormUI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import tmwebservice.PaymentMethod;
import tmwebservice.Ticket;
import tmwebservice.User;

/**
 *
 * @author Franly
 */
public class FormUserTicket extends javax.swing.JFrame {

    /**
     * Creates new form FormUserTicket
     */
    User currentUser;
    FormListOfTicket1 parentForm;
    List<Ticket> listOfTickets;
    List<PaymentMethod> listOfPM;
    Ticket tiketDipilih;
    
    public void refreshTables(){
        txtListTicketDibeli.setText("");
        listOfTickets = getTicketsByUsername(currentUser.getUsername());
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (Ticket t : listOfTickets) {
            System.out.println("tanggal eventnya == " +t.getEvent().getStartTime());
            String startTimeFormatted = formatTanggal(t.getEvent().getStartTime());
            Object[] row = new Object[] {
                t.getEvent().getName(),
                t.getEventClass(),
                startTimeFormatted,
                t.getStatus(),
                t.isIsClaimed() ? "Sudah" : "Belum"
            };
            model.addRow(row);
        }
    }
    
    public FormUserTicket(User currentUser, FormListOfTicket1 parentForm) {
        initComponents();
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);

        txtListTicketDibeli.setText("");
        this.currentUser = currentUser;
        this.parentForm = parentForm;
        refreshTables();
        listOfPM = getPaymentMethods();



        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0 && selectedRow < listOfTickets.size()) {
                    tiketDipilih = listOfTickets.get(selectedRow);
                    String startTimeFormatted = formatTanggal(tiketDipilih.getEvent().getStartTime());

                    String paidTimeText = (tiketDipilih.getPaidTime() == null || tiketDipilih.getPaidTime().isEmpty())
                                            ? "Belum melakukan pembayaran"
                                            : formatTanggal(tiketDipilih.getPaidTime());


                    String detail = "==============================\n" +
                                    "Event         : " + tiketDipilih.getEvent().getName() + "\n" +
                                    "Kelas         : " + tiketDipilih.getEventClass() + "\n" +
                                    "Tanggal Event         : " + startTimeFormatted+ "\n" +
                                    "Metode Pembayaran  : " + getPaymentMethodNameById(tiketDipilih.getPaymentMethod()) + "\n" +
                                    "Status Pembayaran  : " + tiketDipilih.getStatus() + "\n" +
                                    "Klaim         : " + (tiketDipilih.isIsClaimed() ? "Sudah" : "Belum") + "\n" +
                                    "Tanggal Bayar     : " + paidTimeText + "\n" +
                                    "Venue         : " + tiketDipilih.getEvent().getVenue().getName() + "\n" +
                                    "Alamat Venue  : " + tiketDipilih.getEvent().getVenue().getAddress() + "\n" +
                                    "Kota          : " + tiketDipilih.getEvent().getVenue().getCity().getName() + "\n\n";

                    txtListTicketDibeli.setText(detail);

                     // Cek apakah tombol harus di-enable
                    boolean isEventSudahLewat = false;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
                        LocalDateTime startEvent = LocalDateTime.parse(tiketDipilih.getEvent().getStartTime(), formatter);
                        isEventSudahLewat = LocalDateTime.now().isAfter(startEvent);
                    } catch (Exception e) {
                        System.err.println("Format tanggal salah: " + e.getMessage());
                    }

                    // Tombol Bayar
                    jButton2.setEnabled(!isEventSudahLewat && tiketDipilih.getStatus().equalsIgnoreCase("UNPAID"));

                    // Tombol Cancel
                    jButton1.setEnabled(!isEventSudahLewat && tiketDipilih.getStatus().equalsIgnoreCase("UNPAID"));
                    
                    // Tombol Refund
                    jButton3.setEnabled(!isEventSudahLewat && tiketDipilih.getStatus().equalsIgnoreCase("PAID") && !tiketDipilih.isIsClaimed());
                }
            }
        });
    }

    private String getPaymentMethodNameById(int id) {
        for (PaymentMethod pm : listOfPM) {
            if (pm.getId() == id) {
                return pm.getName();
            }
        }
        return "Tidak ditemukan";
    }
    
    private String formatTanggal(String rawDateTime) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
            LocalDateTime dateTime = LocalDateTime.parse(rawDateTime, inputFormatter);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy - HH:mm", new Locale("id", "ID"));
            return dateTime.format(outputFormatter);
        } catch (Exception e) {
            return rawDateTime; 
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtListTicketDibeli = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setText("BATAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtListTicketDibeli.setColumns(20);
        txtListTicketDibeli.setRows(5);
        jScrollPane1.setViewportView(txtListTicketDibeli);

        jLabel1.setText("TIKET ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event", "Kelas", "Tanggal Event", "Status Pembayaran", "Klaim"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setMinWidth(200);
        }

        jButton2.setBackground(new java.awt.Color(102, 255, 102));
        jButton2.setText("BAYAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Klik kolom tiket untuk menampilkan detail tiket");

        jButton3.setBackground(new java.awt.Color(255, 255, 153));
        jButton3.setText("REFUND");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("DETIL TIKET");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("BUYER");

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(364, 364, 364))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        parentForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int result = payTicket(tiketDipilih.getId());

        if (result > 0) {
            refreshTables();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Pembayaran berhasil",
                "Sukses",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Pembayaran gagal",
                "Gagal",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int result = cancelTicketOrder(tiketDipilih);

        if (result > 0) {
            refreshTables();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Berhasil membatalkan pesanan",
                "Sukses",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Gagal membatalkan pesanan",
                "Gagal",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         int result = refundTicket(tiketDipilih.getId());

        if (result > 0) {
            refreshTables();
            javax.swing.JOptionPane.showMessageDialog(this,
                "Berhasil refund tiket",
                "Sukses",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Gagal refund tiket",
                "Gagal",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        FormListOfTicket1 home = new FormListOfTicket1(currentUser);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MousePressed

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
            java.util.logging.Logger.getLogger(FormUserTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUserTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUserTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUserTicket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormUserTicket().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormUserTicket().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormUserTicket().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormUserTicket().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtListTicketDibeli;
    // End of variables declaration//GEN-END:variables

    private static java.util.List<tmwebservice.Ticket> getTicketsByUsername(java.lang.String username) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.getTicketsByUsername(username);
    }


    private static java.util.List<tmwebservice.PaymentMethod> getPaymentMethods() {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.getPaymentMethods();
    }

    private static int payTicket(java.lang.String ticketId) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.payTicket(ticketId);
    }

    private static int cancelTicketOrder(tmwebservice.Ticket ticket) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.cancelTicketOrder(ticket);
    }

    private static int refundTicket(java.lang.String ticketId) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.refundTicket(ticketId);
    }

}

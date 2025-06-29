/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package FormUI;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tmwebservice.Event;
import tmwebservice.EventClass;
import tmwebservice.User;

// … import lain …



/**
 *
 * @author Lenovo
 */
public class FormListOfTicket1 extends javax.swing.JFrame {

    /**
     * Creates new form FormListOfTicket1
     */
    
    int showIndexFrom;
    User currentUser;
    List<Event> listOfEvent;
    public FormListOfTicket1(User currentUser) {
        initComponents();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.currentUser = currentUser;
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;

        loadEvents("all");
        System.out.println(listOfEvent.size());
        refreshTicketsUI();
        if(listOfEvent.size()>0)jLabel5.setText("Tersedia");
        jLabel5.setForeground(Color.GREEN);
    }
    
     private void loadEvents(String filter) {
        listOfEvent = getAllEvents(filter);
    }
     private void refreshTicketsUI(){
         if(listOfEvent.size()%3==1){
            jPanel3.setVisible(false);
            jPanel5.setVisible(false);
            PanelDetailTicket2.setVisible(false);
            PanelDetailTicket3.setVisible(false);
         }else if(listOfEvent.size()%3==2)
         {
            jPanel5.setVisible(false);
            PanelDetailTicket3.setVisible(false);
         }else if(listOfEvent.size()==0){
            jPanel3.setVisible(false);
            jPanel5.setVisible(false);
            PanelDetailTicket2.setVisible(false);
            PanelDetailTicket3.setVisible(false);
            btnDetailsTicket1.setEnabled(false);
            return;
         }
        // Card 1
        if (showIndexFrom < listOfEvent.size()&&listOfEvent.size()%3>=0&&listOfEvent.size()>0) {
           Event event1 = listOfEvent.get(showIndexFrom);
           jLabel1.setText(event1.getName());
           jLabel2.setText(event1.getStartTime());

           List<EventClass> eventClasses1 = event1.getEventClasses();
           boolean tersedia = false;
           if (eventClasses1 != null && !eventClasses1.isEmpty()) {
                EventClass cheapestClass = eventClasses1.get(0);
                for (EventClass ec : eventClasses1) {
                     if (ec.getPrice() < cheapestClass.getPrice()) {
                         cheapestClass = ec;
                     }
                     if (ec.getAvailableStock() > 0) {
                         tersedia = true;
                     }
                 }
                if (tersedia) {
                    jLabel5.setText("Tersedia");
                    jLabel5.setForeground(Color.GREEN);
                } else {
                    jLabel5.setText("Tidak Tersedia");
                    jLabel5.setForeground(Color.RED);

                }
               for (EventClass ec : eventClasses1) {
                   if (ec.getPrice() < cheapestClass.getPrice()) {
                       cheapestClass = ec;
                   }
               }
               jLabel4.setText("Rp: " + cheapestClass.getPrice());
           } else {
               jLabel4.setText("Belum ada kelas tiket");
           }
       }
       // Card 2
       if (showIndexFrom+1 < listOfEvent.size()&&listOfEvent.size()%3>=0&&listOfEvent.size()>0) {
           Event event2 = listOfEvent.get(showIndexFrom+1);
           jLabel10.setText(event2.getName());
           jLabel11.setText(event2.getStartTime());

           List<EventClass> eventClasses1 = event2.getEventClasses();
           boolean tersedia = false;
           if (eventClasses1 != null && !eventClasses1.isEmpty()) {
                EventClass cheapestClass = eventClasses1.get(0);
                for (EventClass ec : eventClasses1) {
                     if (ec.getPrice() < cheapestClass.getPrice()) {
                         cheapestClass = ec;
                     }
                     if (ec.getAvailableStock() > 0) {
                         tersedia = true;
                     }
                 }
                if (tersedia) {
                    jLabel14.setText("Tersedia");
                    jLabel14.setForeground(Color.GREEN);
                } else {
                    jLabel14.setText("Tidak Tersedia");
                    jLabel14.setForeground(Color.RED);
                }
               for (EventClass ec : eventClasses1) {
                   if (ec.getPrice() < cheapestClass.getPrice()) {
                       cheapestClass = ec;
                   }
               }
               jLabel13.setText("Rp: " + cheapestClass.getPrice());
           } else {
               jLabel13.setText("Belum ada kelas tiket");
           }
       }
       // Card 3
        if (showIndexFrom+2 < listOfEvent.size()&&listOfEvent.size()%3>=0&&listOfEvent.size()>0) {
           Event event3 = listOfEvent.get(showIndexFrom+2);
           jLabel16.setText(event3.getName());
           jLabel17.setText(event3.getStartTime());

           List<EventClass> eventClasses1 = event3.getEventClasses();
           boolean tersedia = false;
           if (eventClasses1 != null && !eventClasses1.isEmpty()) {
                EventClass cheapestClass = eventClasses1.get(0);
                for (EventClass ec : eventClasses1) {
                     if (ec.getPrice() < cheapestClass.getPrice()) {
                         cheapestClass = ec;
                     }
                     if (ec.getAvailableStock() > 0) {
                         tersedia = true;
                     }
                 }
                if (tersedia) {
                    jLabel20.setText("Tersedia");
                    jLabel20.setForeground(Color.GREEN);
                } else {
                    jLabel20.setText("Tidak Tersedia");
                    jLabel20.setForeground(Color.RED);
                }
               for (EventClass ec : eventClasses1) {
                   if (ec.getPrice() < cheapestClass.getPrice()) {
                       cheapestClass = ec;
                   }
               }
               jLabel19.setText("Rp: " + cheapestClass.getPrice());
           } else {
               jLabel19.setText("Belum ada kelas tiket");
           }
       }
     }

    /**
     * Assign event data to UI labels for three ticket boxes.
     */
//    private void refreshTicketsUI() {
//        JLabel[] nameLabels  = { jLabel1,  jLabel10, jLabel16 };
//        JLabel[] priceLabels = { jLabel4,  jLabel13, jLabel19 };
//        JLabel[] dateLabels  = { jLabel2,  jLabel11, jLabel17 };
//
//        DateTimeFormatter fmt = DateTimeFormatter
//            .ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
//
//        for (int i = 0; i < 3; i++) {
//            int idx = showIndexFrom + i;
//            Event ev = service.repo.ListEvent.get(idx);
//            JLabel lblName  = nameLabels[i];
//            JLabel lblPrice = priceLabels[i];
//            JLabel lblDate  = dateLabels[i];
//
//            if (ev == null || ev.getSeller() == null) {
//                lblName.setText("Tidak ada event");
//                lblPrice.setText("IDR -");
//                lblDate.setText("-");
//            } else {
//                lblName.setText(ev.getName());
//
//                double minPrice = Math.min(
//                    ev.getEventClasses().getFirst().getPrice(),
//                    ev.getEventClasses().getLast().getPrice()
//                );
//                lblPrice.setText("IDR " + minPrice);
//
//                String tanggal = ev.getStartDateTime().format(fmt);
//                lblDate.setText(tanggal);
//            }
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        btnTerdekat = new javax.swing.JButton();
        btnTerbaru = new javax.swing.JButton();
        btnHargaTerendah = new javax.swing.JButton();
        btnHargaTertinggi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        PanelDetailTicket1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnDetailsTicket1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        PanelDetailTicket2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnDetailsTicket2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        PanelDetailTicket3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnDetailsTicket3 = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnTiketDibeli = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setText("Urutkan :");

        btnTerdekat.setText("Terdekat");
        btnTerdekat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerdekatActionPerformed(evt);
            }
        });

        btnTerbaru.setText("Terbaru");
        btnTerbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerbaruActionPerformed(evt);
            }
        });

        btnHargaTerendah.setText("Harga Terendah");
        btnHargaTerendah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHargaTerendahActionPerformed(evt);
            }
        });

        btnHargaTertinggi.setText("Harga Tertinggi");
        btnHargaTertinggi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHargaTertinggiActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel6.setText("GAMBAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel6)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        PanelDetailTicket1.setBackground(new java.awt.Color(255, 255, 255));
        PanelDetailTicket1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tidak ada event");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setText("-");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 51));
        jLabel4.setText("-");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 153, 0));
        jLabel5.setText("-");

        btnDetailsTicket1.setBackground(new java.awt.Color(153, 102, 255));
        btnDetailsTicket1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnDetailsTicket1.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailsTicket1.setText("Details");
        btnDetailsTicket1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsTicket1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDetailTicket1Layout = new javax.swing.GroupLayout(PanelDetailTicket1);
        PanelDetailTicket1.setLayout(PanelDetailTicket1Layout);
        PanelDetailTicket1Layout.setHorizontalGroup(
            PanelDetailTicket1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailTicket1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDetailTicket1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDetailsTicket1))
                    .addGroup(PanelDetailTicket1Layout.createSequentialGroup()
                        .addGroup(PanelDetailTicket1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelDetailTicket1Layout.setVerticalGroup(
            PanelDetailTicket1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(PanelDetailTicket1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDetailsTicket1)
                    .addGroup(PanelDetailTicket1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(8, 8, 8)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel9.setText("GAMBAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel9)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel9)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        PanelDetailTicket2.setBackground(new java.awt.Color(255, 255, 255));
        PanelDetailTicket2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Judul Event");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel11.setText("11 Agustus 2025");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 102, 51));
        jLabel13.setText("IDR 500.000");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 153, 0));
        jLabel14.setText("-");

        btnDetailsTicket2.setBackground(new java.awt.Color(153, 102, 255));
        btnDetailsTicket2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnDetailsTicket2.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailsTicket2.setText("Details");
        btnDetailsTicket2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsTicket2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDetailTicket2Layout = new javax.swing.GroupLayout(PanelDetailTicket2);
        PanelDetailTicket2.setLayout(PanelDetailTicket2Layout);
        PanelDetailTicket2Layout.setHorizontalGroup(
            PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDetailTicket2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetailsTicket2))
                    .addGroup(PanelDetailTicket2Layout.createSequentialGroup()
                        .addGroup(PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelDetailTicket2Layout.setVerticalGroup(
            PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDetailTicket2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDetailsTicket2)
                        .addComponent(jLabel14))
                    .addGroup(PanelDetailTicket2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(40, 40, 40)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel15.setText("GAMBAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel15)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel15)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        PanelDetailTicket3.setBackground(new java.awt.Color(255, 255, 255));
        PanelDetailTicket3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Judul Event");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel17.setText("11 Agustus 2025");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 102, 51));
        jLabel19.setText("IDR 500.000");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 153, 0));
        jLabel20.setText("-");

        btnDetailsTicket3.setBackground(new java.awt.Color(153, 102, 255));
        btnDetailsTicket3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnDetailsTicket3.setForeground(new java.awt.Color(255, 255, 255));
        btnDetailsTicket3.setText("Details");
        btnDetailsTicket3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsTicket3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDetailTicket3Layout = new javax.swing.GroupLayout(PanelDetailTicket3);
        PanelDetailTicket3.setLayout(PanelDetailTicket3Layout);
        PanelDetailTicket3Layout.setHorizontalGroup(
            PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDetailTicket3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDetailsTicket3))
                    .addGroup(PanelDetailTicket3Layout.createSequentialGroup()
                        .addGroup(PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelDetailTicket3Layout.setVerticalGroup(
            PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDetailTicket3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDetailTicket3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDetailsTicket3)
                        .addComponent(jLabel20))
                    .addGroup(PanelDetailTicket3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(40, 40, 40)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnPrev.setText("Prev");
        btnPrev.setPreferredSize(new java.awt.Dimension(70, 35));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.setPreferredSize(new java.awt.Dimension(70, 35));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("1");

        jPanel4.setBackground(new java.awt.Color(51, 0, 51));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Kopi Jawa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnTiketDibeli.setText("Tiket dibeli");
        btnTiketDibeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiketDibeliActionPerformed(evt);
            }
        });

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        menuHome.setText("Refresh");
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuHomeMousePressed(evt);
            }
        });
        jMenuBar1.add(menuHome);

        jMenu1.setText("Profile");
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
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTerdekat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTerbaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHargaTerendah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHargaTertinggi))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PanelDetailTicket1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(btnTiketDibeli)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelDetailTicket2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PanelDetailTicket3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnTerdekat)
                    .addComponent(btnTerbaru)
                    .addComponent(btnHargaTerendah)
                    .addComponent(btnHargaTertinggi))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelDetailTicket1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelDetailTicket2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelDetailTicket3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21)
                            .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTiketDibeli)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetailsTicket1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsTicket1ActionPerformed
        // TODO add your handling code here:
        
        Event selected = listOfEvent.get(showIndexFrom);
        FormUserTicketDetail detailevent = new FormUserTicketDetail(this, selected,currentUser);
        detailevent.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_btnDetailsTicket1ActionPerformed

    private void btnTiketDibeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiketDibeliActionPerformed
        // TODO add your handling code here:
        FormUserTicket form = new FormUserTicket(currentUser, this);
        form.setVisible(true);
        
        this.setVisible(false);
    }//GEN-LAST:event_btnTiketDibeliActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int currentValue = Integer.parseInt(jLabel21.getText());
        
        int totalPage = (int) Math.ceil(listOfEvent.size() / 3.0);
        if (currentValue < totalPage) {
            jLabel21.setText(String.valueOf(currentValue + 1));

            int page = Integer.parseInt(jLabel21.getText());
            showIndexFrom = 3 * page - 3;

            refreshTicketsUI();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        int currentValue = Integer.parseInt(jLabel21.getText());
        if(currentValue==1) return;
        jLabel21.setText(String.valueOf(currentValue - 1));
        
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;

        refreshTicketsUI();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnDetailsTicket2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsTicket2ActionPerformed
//        
         Event selected = listOfEvent.get(showIndexFrom+1);
        FormUserTicketDetail detailevent = new FormUserTicketDetail(this, selected,currentUser);
        detailevent.setVisible(true);

        this.setVisible(false);

    }//GEN-LAST:event_btnDetailsTicket2ActionPerformed

    private void btnDetailsTicket3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsTicket3ActionPerformed
//         
         Event selected = listOfEvent.get(showIndexFrom+2);
        FormUserTicketDetail detailevent = new FormUserTicketDetail(this, selected,currentUser);
        detailevent.setVisible(true);

        this.setVisible(false);

    }//GEN-LAST:event_btnDetailsTicket3ActionPerformed

    private void menuHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMousePressed
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;

        loadEvents("all");
        refreshTicketsUI();
        if(listOfEvent.size()>0)jLabel5.setText("Tersedia sekarang");
    }//GEN-LAST:event_menuHomeMousePressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        System.out.println("to profile");
        FormUserProfile home = new FormUserProfile(currentUser);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu1MousePressed

    private void btnTerdekatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerdekatActionPerformed
        System.out.println("terdekat");
        jLabel21.setText("1");
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;
        loadEvents("terdekat");
        refreshTicketsUI();
    }//GEN-LAST:event_btnTerdekatActionPerformed

    private void btnTerbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerbaruActionPerformed
        jLabel21.setText("1");
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;
        loadEvents("terbaru");
        refreshTicketsUI();
    }//GEN-LAST:event_btnTerbaruActionPerformed

    private void btnHargaTerendahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHargaTerendahActionPerformed
        jLabel21.setText("1");
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;
        loadEvents("harga terdekat");
        refreshTicketsUI();
    }//GEN-LAST:event_btnHargaTerendahActionPerformed

    private void btnHargaTertinggiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHargaTertinggiActionPerformed
        jLabel21.setText("1");
        int page = Integer.parseInt(jLabel21.getText());
        showIndexFrom = 3 * page - 3;
        loadEvents("harga tertinggi");
        refreshTicketsUI();
    }//GEN-LAST:event_btnHargaTertinggiActionPerformed

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
            java.util.logging.Logger.getLogger(FormListOfTicket1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListOfTicket1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListOfTicket1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListOfTicket1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormListOfTicket1().setVisible(true);
//            }
//        });
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormListOfTicket1().setVisible(true);
//            }
//        });
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormListOfTicket1().setVisible(true);
//            }
//        });
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormListOfTicket1().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDetailTicket1;
    private javax.swing.JPanel PanelDetailTicket2;
    private javax.swing.JPanel PanelDetailTicket3;
    private javax.swing.JButton btnDetailsTicket1;
    private javax.swing.JButton btnDetailsTicket2;
    private javax.swing.JButton btnDetailsTicket3;
    private javax.swing.JButton btnHargaTerendah;
    private javax.swing.JButton btnHargaTertinggi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnTerbaru;
    private javax.swing.JButton btnTerdekat;
    private javax.swing.JButton btnTiketDibeli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JMenu menuHome;
    // End of variables declaration//GEN-END:variables

    private static java.util.List<tmwebservice.Event> getAllEvents(java.lang.String filter) {
        tmwebservice.TMWebService_Service service = new tmwebservice.TMWebService_Service();
        tmwebservice.TMWebService port = service.getTMWebServicePort();
        return port.getAllEvents(filter);
    }


}

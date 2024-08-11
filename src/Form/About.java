package Form;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class About extends javax.swing.JPanel {

    public About() {
        initComponents();
        linkIyan();
        linkNisa();
        
        Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(254,216,179));
        lbAbout1.setBorder(bottomBorder);
        lbAbout2.setBorder(bottomBorder);
        lbAbout3.setBorder(bottomBorder);
        lbAbout4.setBorder(bottomBorder);
        lbAbout5.setBorder(bottomBorder);
        lbAbout6.setBorder(bottomBorder);
        lbAbout7.setBorder(bottomBorder);
        lbAbout8.setBorder(bottomBorder);
        
        Border bottomBorderJudul = BorderFactory.createMatteBorder(0, 0, 4, 0, new java.awt.Color(98,48,0));
        labellJudul.setBorder(bottomBorderJudul);
        labellJudulIyan.setBorder(bottomBorderJudul);
        labellJudulNisa.setBorder(bottomBorderJudul);
        
        hoverLbAbout1();
        hoverLbAbout2();
        hoverLbAbout3();
        hoverLbAbout4();
        hoverLbAbout5();
        hoverLbAbout6();
        hoverLbAbout7();
        hoverLbAbout8();
        
        ImageIcon imageIyan = new ImageIcon("src/example/Me-125px.jpg");
        ImageIcon imageIyan2 = new ImageIcon("src/example/Me-300px.jpg");
        ImageIcon imageNisa = new ImageIcon("src/example/Honey-125px.jpg");
        ImageIcon imageNisa2 = new ImageIcon("src/example/Honey-300px.jpg");
        ImageIcon imageEbi = new ImageIcon("src/example/Eby-125px.jpg");
        ImageIcon imageHelmi = new ImageIcon("src/example/Helmi-125px.jpg");
        ImageIcon imageJose = new ImageIcon("src/example/Jose-125px.jpg");
        ImageIcon imageDapin = new ImageIcon("src/example/Dhafin-125px.jpg");
        ImageIcon imageDinda = new ImageIcon("src/example/dinda-125px.jpg");
        ImageIcon imageApis = new ImageIcon("src/example/Apis-125px.jpg");
        labelIyan.setIcon(imageIyan);
        Label1.setIcon(imageIyan2);
        labelNisa.setIcon(imageNisa);
        Label2.setIcon(imageNisa2);
        labelEbi.setIcon(imageEbi);
        labelHelmi.setIcon(imageHelmi);
        labelJose.setIcon(imageJose);
        labelDapin.setIcon(imageDapin);
        labelDinda.setIcon(imageDinda);
        labelApis.setIcon(imageApis);
    }

    private void linkIyan() {
        linkGmailIyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka aplikasi email default dengan alamat penerima yang sudah diisi
                    Desktop.getDesktop().mail(new URI("mailto:supriyandilaawe@gmail.com"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkLinkedinIyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website linkedin dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/supriyandi-la-awe-b81403296/"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkGithubIyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website github dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://github.com/yand06"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkFBIyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website facebook dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/yandla.sixdes?mibextid=ZbWKwL"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        }); 
        linkIGIyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website instagram dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/yand_la06/"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });  
    }
    
    private void linkNisa(){
        linkGmailNisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka aplikasi email default dengan alamat penerima yang sudah diisi
                    Desktop.getDesktop().mail(new URI("mailto:anissaamalia23571@gmail.com"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkLinkedinNisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website linkedin dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/anissa-amalia"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkGithubNisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website github dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://github.com/anissaamalia023"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
        linkFBNisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website facebook dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.facebook.com/share/hH4tJzM6QvPpHmrF/?mibextid=qi2Omg"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        }); 
        linkIGNisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Buka website instagram dengan link berikut
                    Desktop.getDesktop().browse(new URI("https://www.instagram.com/_its_ciaa_/"));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal membuka aplikasi email: " + e.getMessage());
                }
            }
        });
    }
    
    private void hoverLbAbout1(){
        lbAbout1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout1.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout1.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout2(){
        lbAbout2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout2.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout2.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout3(){
        lbAbout3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout3.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout3.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout4(){
        lbAbout4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout4.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout4.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout5(){
        lbAbout5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout5.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout5.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout6(){
        lbAbout6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout6.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout6.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout7(){
        lbAbout7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout7.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout7.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    private void hoverLbAbout8(){
        lbAbout8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                lbAbout8.setForeground(new java.awt.Color(255,106,0));
            }
            @Override
            public void mouseExited(MouseEvent e){
                lbAbout8.setForeground(new java.awt.Color(254,216,179));
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        about = new javax.swing.JPanel();
        backgroundHome2 = new Custom.BackgroundHome();
        panel1 = new Custom.Panel();
        labelIyan = new javax.swing.JLabel();
        lbAbout1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labellJudul = new javax.swing.JLabel();
        panel2 = new Custom.Panel();
        labelJose = new javax.swing.JLabel();
        lbAbout5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel3 = new Custom.Panel();
        labelNisa = new javax.swing.JLabel();
        lbAbout2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panel4 = new Custom.Panel();
        labelDapin = new javax.swing.JLabel();
        lbAbout6 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panel5 = new Custom.Panel();
        labelEbi = new javax.swing.JLabel();
        lbAbout3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panel6 = new Custom.Panel();
        labelDinda = new javax.swing.JLabel();
        lbAbout7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panel7 = new Custom.Panel();
        labelApis = new javax.swing.JLabel();
        lbAbout8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel8 = new Custom.Panel();
        labelHelmi = new javax.swing.JLabel();
        lbAbout4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panel9 = new Custom.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        aboutIyan = new javax.swing.JPanel();
        backgroundHome1 = new Custom.BackgroundHome();
        labellJudulIyan = new javax.swing.JLabel();
        Label1 = new javax.swing.JLabel();
        button1 = new Custom.Button();
        linkIGIyan = new javax.swing.JLabel();
        linkFBIyan = new javax.swing.JLabel();
        linkGithubIyan = new javax.swing.JLabel();
        linkLinkedinIyan = new javax.swing.JLabel();
        linkGmailIyan = new javax.swing.JLabel();
        aboutNisa = new javax.swing.JPanel();
        backgroundHome3 = new Custom.BackgroundHome();
        labellJudulNisa = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        button2 = new Custom.Button();
        linkIGNisa = new javax.swing.JLabel();
        linkFBNisa = new javax.swing.JLabel();
        linkGithubNisa = new javax.swing.JLabel();
        linkLinkedinNisa = new javax.swing.JLabel();
        linkGmailNisa = new javax.swing.JLabel();

        main.setLayout(new java.awt.CardLayout());

        panel1.setBackground(new java.awt.Color(98, 48, 0));
        panel1.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel1.setMaximumSize(new java.awt.Dimension(450, 450));
        panel1.setRoundBottomLeft(35);
        panel1.setRoundBottomRight(35);
        panel1.setRoundTopLeft(35);
        panel1.setRoundTopRight(35);

        labelIyan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout1.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout1.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout1.setText("Supriyandi La Awe");
        lbAbout1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbAbout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbAbout1MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 216, 179));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("202143500793");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelIyan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(lbAbout1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelIyan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        labellJudul.setFont(new java.awt.Font("Gabriola", 1, 130)); // NOI18N
        labellJudul.setForeground(new java.awt.Color(254, 216, 179));
        labellJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labellJudul.setText("About Us");

        panel2.setBackground(new java.awt.Color(98, 48, 0));
        panel2.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(450, 450));
        panel2.setRoundBottomLeft(35);
        panel2.setRoundBottomRight(35);
        panel2.setRoundTopLeft(35);
        panel2.setRoundTopRight(35);

        labelJose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout5.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout5.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout5.setText("Jose Pardamean");
        lbAbout5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout5MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 216, 179));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("202143500836");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelJose, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbAbout5, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(lbAbout5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelJose, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel3.setBackground(new java.awt.Color(98, 48, 0));
        panel3.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel3.setMaximumSize(new java.awt.Dimension(450, 450));
        panel3.setRoundBottomLeft(35);
        panel3.setRoundBottomRight(35);
        panel3.setRoundTopLeft(35);
        panel3.setRoundTopRight(35);

        labelNisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout2.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout2.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout2.setText("Anissa Amalia");
        lbAbout2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout2MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 216, 179));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("202143500798");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelNisa, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel3Layout.createSequentialGroup()
                        .addComponent(lbAbout2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelNisa, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel4.setBackground(new java.awt.Color(98, 48, 0));
        panel4.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel4.setMaximumSize(new java.awt.Dimension(450, 450));
        panel4.setRoundBottomLeft(35);
        panel4.setRoundBottomRight(35);
        panel4.setRoundTopLeft(35);
        panel4.setRoundTopRight(35);

        labelDapin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout6.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout6.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout6.setText("Dhafin Al Refy");
        lbAbout6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout6MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 216, 179));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("202143500793");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelDapin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelDapin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addComponent(lbAbout6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel5.setBackground(new java.awt.Color(98, 48, 0));
        panel5.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel5.setMaximumSize(new java.awt.Dimension(450, 450));
        panel5.setRoundBottomLeft(35);
        panel5.setRoundBottomRight(35);
        panel5.setRoundTopLeft(35);
        panel5.setRoundTopRight(35);

        labelEbi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout3.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout3.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout3.setText("M. Febriansyah");
        lbAbout3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout3MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 216, 179));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("202143500818");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelEbi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(lbAbout3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelEbi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel6.setBackground(new java.awt.Color(98, 48, 0));
        panel6.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel6.setMaximumSize(new java.awt.Dimension(450, 450));
        panel6.setRoundBottomLeft(35);
        panel6.setRoundBottomRight(35);
        panel6.setRoundTopLeft(35);
        panel6.setRoundTopRight(35);

        labelDinda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout7.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout7.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout7.setText("Adinda Gusti N");
        lbAbout7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 216, 179));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("202143500793");

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelDinda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbAbout7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel6Layout.createSequentialGroup()
                        .addComponent(lbAbout7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelDinda, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel7.setBackground(new java.awt.Color(98, 48, 0));
        panel7.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel7.setMaximumSize(new java.awt.Dimension(450, 450));
        panel7.setRoundBottomLeft(35);
        panel7.setRoundBottomRight(35);
        panel7.setRoundTopLeft(35);
        panel7.setRoundTopRight(35);

        labelApis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout8.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout8.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout8.setText("Hafidz Ramdani");
        lbAbout8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 216, 179));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("202143500822");

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelApis, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout8, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel7Layout.createSequentialGroup()
                        .addComponent(lbAbout8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelApis, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        panel8.setBackground(new java.awt.Color(98, 48, 0));
        panel8.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        panel8.setMaximumSize(new java.awt.Dimension(450, 450));
        panel8.setRoundBottomLeft(35);
        panel8.setRoundBottomRight(35);
        panel8.setRoundTopLeft(35);
        panel8.setRoundTopRight(35);

        labelHelmi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 216, 179), 2));

        lbAbout4.setFont(new java.awt.Font("Freehand521 BT", 1, 22)); // NOI18N
        lbAbout4.setForeground(new java.awt.Color(254, 216, 179));
        lbAbout4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAbout4.setText("H. Aditya Ferdian");
        lbAbout4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbAbout4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAbout4MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Freehand521 BT", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 216, 179));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("202143500808");

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labelHelmi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAbout4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel8Layout.createSequentialGroup()
                        .addComponent(lbAbout4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelHelmi, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel9.setBackground(new java.awt.Color(254, 216, 179, 180));
        panel9.setRoundBottomLeft(50);
        panel9.setRoundBottomRight(50);
        panel9.setRoundTopLeft(50);
        panel9.setRoundTopRight(50);

        jLabel1.setFont(new java.awt.Font("Gabriola", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(98, 48, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("\"Setiap bug adalah kesempatan untuk belajar, setiap error adalah batu loncatan menuju keberhasilan. ");

        jLabel2.setFont(new java.awt.Font("Gabriola", 1, 42)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(98, 48, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Teruslah menulis kode dengan semangat dan ketekunan, karena masa depan dibangun ");

        jLabel11.setFont(new java.awt.Font("Gabriola", 1, 42)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(98, 48, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("dari baris-baris kode yang Anda tulis hari ini.\"");

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout backgroundHome2Layout = new javax.swing.GroupLayout(backgroundHome2);
        backgroundHome2.setLayout(backgroundHome2Layout);
        backgroundHome2Layout.setHorizontalGroup(
            backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundHome2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labellJudul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundHome2Layout.createSequentialGroup()
                        .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundHome2Layout.createSequentialGroup()
                                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5))
                            .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50)
                        .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backgroundHome2Layout.createSequentialGroup()
                                .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(4, 4, 4)))))
                .addGap(50, 50, 50))
        );
        backgroundHome2Layout.setVerticalGroup(
            backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundHome2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(labellJudul, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(backgroundHome2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(panel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aboutLayout = new javax.swing.GroupLayout(about);
        about.setLayout(aboutLayout);
        aboutLayout.setHorizontalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        aboutLayout.setVerticalGroup(
            aboutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(about, "card2");

        labellJudulIyan.setFont(new java.awt.Font("Gabriola", 1, 130)); // NOI18N
        labellJudulIyan.setForeground(new java.awt.Color(254, 216, 179));
        labellJudulIyan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labellJudulIyan.setText("Supriyandi La Awe");

        Label1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 48, 0), 3));

        button1.setBackground(new java.awt.Color(103, 54, 0));
        button1.setForeground(new java.awt.Color(254, 216, 179));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Back_To_30px_1.png"))); // NOI18N
        button1.setText(" Back");
        button1.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button1MouseExited(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        linkIGIyan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_Instagram_50px.png"))); // NOI18N
        linkIGIyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkFBIyan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_facebook_50px.png"))); // NOI18N
        linkFBIyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkGithubIyan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_github_50px.png"))); // NOI18N
        linkGithubIyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkLinkedinIyan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_linkedin_50px.png"))); // NOI18N
        linkLinkedinIyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkGmailIyan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_Gmail_Logo_50px.png"))); // NOI18N
        linkGmailIyan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout backgroundHome1Layout = new javax.swing.GroupLayout(backgroundHome1);
        backgroundHome1.setLayout(backgroundHome1Layout);
        backgroundHome1Layout.setHorizontalGroup(
            backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundHome1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labellJudulIyan, javax.swing.GroupLayout.DEFAULT_SIZE, 1582, Short.MAX_VALUE)
                    .addGroup(backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(backgroundHome1Layout.createSequentialGroup()
                            .addComponent(linkGmailIyan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkLinkedinIyan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkGithubIyan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkFBIyan)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkIGIyan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        backgroundHome1Layout.setVerticalGroup(
            backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundHome1Layout.createSequentialGroup()
                .addGroup(backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundHome1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(labellJudulIyan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundHome1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(linkIGIyan)
                    .addComponent(linkFBIyan)
                    .addComponent(linkGithubIyan)
                    .addComponent(linkLinkedinIyan)
                    .addComponent(linkGmailIyan))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aboutIyanLayout = new javax.swing.GroupLayout(aboutIyan);
        aboutIyan.setLayout(aboutIyanLayout);
        aboutIyanLayout.setHorizontalGroup(
            aboutIyanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        aboutIyanLayout.setVerticalGroup(
            aboutIyanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(aboutIyan, "card3");

        labellJudulNisa.setFont(new java.awt.Font("Gabriola", 1, 130)); // NOI18N
        labellJudulNisa.setForeground(new java.awt.Color(254, 216, 179));
        labellJudulNisa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labellJudulNisa.setText("Anissa Amalia");

        Label2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 48, 0), 3));

        button2.setBackground(new java.awt.Color(103, 54, 0));
        button2.setForeground(new java.awt.Color(254, 216, 179));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Back_To_30px_1.png"))); // NOI18N
        button2.setText(" Back");
        button2.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button2MouseExited(evt);
            }
        });
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        linkIGNisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_Instagram_50px.png"))); // NOI18N
        linkIGNisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkFBNisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_facebook_50px.png"))); // NOI18N
        linkFBNisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkGithubNisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_github_50px.png"))); // NOI18N
        linkGithubNisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkLinkedinNisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_linkedin_50px.png"))); // NOI18N
        linkLinkedinNisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        linkGmailNisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/example/icons8_Gmail_Logo_50px.png"))); // NOI18N
        linkGmailNisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout backgroundHome3Layout = new javax.swing.GroupLayout(backgroundHome3);
        backgroundHome3.setLayout(backgroundHome3Layout);
        backgroundHome3Layout.setHorizontalGroup(
            backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundHome3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labellJudulNisa, javax.swing.GroupLayout.DEFAULT_SIZE, 1582, Short.MAX_VALUE)
                    .addGroup(backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(backgroundHome3Layout.createSequentialGroup()
                            .addComponent(linkGmailNisa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkLinkedinNisa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkGithubNisa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkFBNisa)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(linkIGNisa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        backgroundHome3Layout.setVerticalGroup(
            backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundHome3Layout.createSequentialGroup()
                .addGroup(backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundHome3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(labellJudulNisa, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundHome3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundHome3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(linkIGNisa)
                    .addComponent(linkFBNisa)
                    .addComponent(linkGithubNisa)
                    .addComponent(linkLinkedinNisa)
                    .addComponent(linkGmailNisa))
                .addContainerGap(439, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout aboutNisaLayout = new javax.swing.GroupLayout(aboutNisa);
        aboutNisa.setLayout(aboutNisaLayout);
        aboutNisaLayout.setHorizontalGroup(
            aboutNisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        aboutNisaLayout.setVerticalGroup(
            aboutNisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundHome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        main.add(aboutNisa, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbAbout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout1MouseClicked
        main.removeAll();
        main.add(aboutIyan);
        main.revalidate();
        main.repaint();
    }//GEN-LAST:event_lbAbout1MouseClicked

    private void lbAbout5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout5MouseClicked

    private void lbAbout2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout2MouseClicked
        main.removeAll();
        main.add(aboutNisa);
        main.revalidate();
        main.repaint();
    }//GEN-LAST:event_lbAbout2MouseClicked

    private void lbAbout6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout6MouseClicked

    private void lbAbout3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout3MouseClicked

    private void lbAbout7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout7MouseClicked

    private void lbAbout8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout8MouseClicked

    private void lbAbout4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbAbout4MouseClicked

    private void lbAbout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout1MouseEntered

    }//GEN-LAST:event_lbAbout1MouseEntered

    private void lbAbout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAbout1MouseExited

    }//GEN-LAST:event_lbAbout1MouseExited

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        main.removeAll();
        main.add(about);
        main.revalidate();
        main.repaint();
    }//GEN-LAST:event_button1ActionPerformed

    private void button1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseEntered
        button1.setBackground(new Color(254,216,179));
        button1.setForeground(new Color(103,54,0));
    }//GEN-LAST:event_button1MouseEntered

    private void button1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseExited
        button1.setBackground(new Color(103,54,0));
        button1.setForeground(new Color(254,216,179));
    }//GEN-LAST:event_button1MouseExited

    private void button2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseEntered
        button2.setBackground(new Color(254,216,179));
        button2.setForeground(new Color(103,54,0));
    }//GEN-LAST:event_button2MouseEntered

    private void button2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseExited
        button2.setBackground(new Color(103,54,0));
        button2.setForeground(new Color(254,216,179));
    }//GEN-LAST:event_button2MouseExited

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        main.removeAll();
        main.add(about);
        main.revalidate();
        main.repaint();
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JPanel about;
    private javax.swing.JPanel aboutIyan;
    private javax.swing.JPanel aboutNisa;
    private Custom.BackgroundHome backgroundHome1;
    private Custom.BackgroundHome backgroundHome2;
    private Custom.BackgroundHome backgroundHome3;
    private Custom.Button button1;
    private Custom.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelApis;
    private javax.swing.JLabel labelDapin;
    private javax.swing.JLabel labelDinda;
    private javax.swing.JLabel labelEbi;
    private javax.swing.JLabel labelHelmi;
    private javax.swing.JLabel labelIyan;
    private javax.swing.JLabel labelJose;
    private javax.swing.JLabel labelNisa;
    private javax.swing.JLabel labellJudul;
    private javax.swing.JLabel labellJudulIyan;
    private javax.swing.JLabel labellJudulNisa;
    private javax.swing.JLabel lbAbout1;
    private javax.swing.JLabel lbAbout2;
    private javax.swing.JLabel lbAbout3;
    private javax.swing.JLabel lbAbout4;
    private javax.swing.JLabel lbAbout5;
    private javax.swing.JLabel lbAbout6;
    private javax.swing.JLabel lbAbout7;
    private javax.swing.JLabel lbAbout8;
    private javax.swing.JLabel linkFBIyan;
    private javax.swing.JLabel linkFBNisa;
    private javax.swing.JLabel linkGithubIyan;
    private javax.swing.JLabel linkGithubNisa;
    private javax.swing.JLabel linkGmailIyan;
    private javax.swing.JLabel linkGmailNisa;
    private javax.swing.JLabel linkIGIyan;
    private javax.swing.JLabel linkIGNisa;
    private javax.swing.JLabel linkLinkedinIyan;
    private javax.swing.JLabel linkLinkedinNisa;
    private javax.swing.JPanel main;
    private Custom.Panel panel1;
    private Custom.Panel panel2;
    private Custom.Panel panel3;
    private Custom.Panel panel4;
    private Custom.Panel panel5;
    private Custom.Panel panel6;
    private Custom.Panel panel7;
    private Custom.Panel panel8;
    private Custom.Panel panel9;
    // End of variables declaration//GEN-END:variables
}

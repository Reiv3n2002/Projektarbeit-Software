import java.awt.*;
import javax.swing.*;
import java.sql.*;

/**
 *
 * Description
 *
 * @version 1.0 from 08.12.2024
 * @author 
 */

public class OnlineBanking extends JFrame {
  // start attributes
  // Anfang Attribute
  private JPanel jPanel1 = new JPanel(null, true);
  private JLabel lSpasskasseEssen1 = new JLabel();
  private JPanel jPanel2 = new JPanel(null, true);
  private JLabel lKontouebersicht1 = new JLabel();
  private JPanel jPanel3 = new JPanel(null, true);
  private JLabel lKontoinformation1 = new JLabel();
  private JLabel lKontoinhaber = new JLabel();
  private JLabel lIBAN = new JLabel();
  private JLabel lKontoArt = new JLabel();
  private JLabel lKontostand1 = new JLabel();
  private JPanel jPanel4 = new JPanel(null, true);
  private JPanel jPanel5 = new JPanel(null, true);
  // Ende Attribute
  // end attributes
  
  public OnlineBanking(int userId) { 
    // Frame init
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 800; 
    int frameHeight = 550;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Spaßkasse Essen - Online Banking");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    cp.setBackground(Color.WHITE);
    
    // Kontoinformationen abrufen und anzeigen
    loadAccountInformation(userId);
    
    // Panel 1 Einstellungen
    jPanel1.setBounds(0, 0, 800, 56);  // Position und Größe setzen
    jPanel1.setBackground(new Color(0xE60000));  // Rote Farbe für Sparkassen-Design
    jPanel1.setVisible(true);
    
    lSpasskasseEssen1.setBounds(16, 8, 216, 40);
    lSpasskasseEssen1.setText("Spaßkasse Essen");
    lSpasskasseEssen1.setFont(new Font("Bahnschrift Condensed", Font.BOLD, 22));
    lSpasskasseEssen1.setForeground(Color.WHITE);
    lSpasskasseEssen1.setVisible(true);
    jPanel2.setBounds(0, 56, 145, 457);
    jPanel1.setOpaque(true);
    jPanel2.setOpaque(true);
    jPanel2.setBackground(new Color(0x404040));
    cp.add(jPanel1);
    jPanel1.add(lSpasskasseEssen1);
    cp.add(jPanel2);
    jPanel4.setBounds(144, 56, 465, 40);
    jPanel4.setOpaque(true);
    jPanel4.setBackground(Color.GRAY);
    jPanel4.setLayout(null);  // Explizites null Layout für absolute Positionierung
    
    // Buttons erstellen
    JButton bTransaktionen = new JButton("Transaktionen");
    bTransaktionen.setBounds(10, 5, 140, 30);
    bTransaktionen.setBackground(new Color(0xE60000));
    bTransaktionen.setForeground(Color.WHITE);
    bTransaktionen.setFocusPainted(false);
    
    JButton bUeberweisungen = new JButton("Überweisungen");
    bUeberweisungen.setBounds(160, 5, 140, 30);
    bUeberweisungen.setBackground(Color.GRAY);
    bUeberweisungen.setForeground(Color.WHITE);
    bUeberweisungen.setFocusPainted(false);
    
    JButton bSparkonto = new JButton("Spark. erstellen");
    bSparkonto.setBounds(310, 5, 140, 30);
    bSparkonto.setBackground(Color.GRAY);
    bSparkonto.setForeground(Color.WHITE);
    bSparkonto.setFocusPainted(false);



    bSparkonto.addActionListener(e -> {
        bTransaktionen.setBackground(Color.GRAY);
        bUeberweisungen.setBackground(Color.GRAY);
        bSparkonto.setBackground(new Color(0xE60000));
    });

    bTransaktionen.addActionListener(e -> {
      bTransaktionen.setBackground(new Color(0xE60000));
      bUeberweisungen.setBackground(Color.GRAY);
      bSparkonto.setBackground(Color.GRAY);
    });

    bUeberweisungen.addActionListener(e -> {
      bTransaktionen.setBackground(Color.GRAY);
      bUeberweisungen.setBackground(new Color(0xE60000));
      bSparkonto.setBackground(Color.GRAY);
    });
    
    // Buttons zum Panel hinzufügen
    jPanel4.add(bTransaktionen);
    jPanel4.add(bUeberweisungen);
    jPanel4.add(bSparkonto);
    
    jPanel4.setVisible(true);
    cp.add(jPanel4);
    lKontouebersicht1.setBounds(16, 8, 107, 24);
    lKontouebersicht1.setText("Kontoübersicht");
    lKontouebersicht1.setFont(new Font("Dialog", Font.BOLD, 12));
    lKontouebersicht1.setForeground(Color.WHITE);
    lKontouebersicht1.setHorizontalAlignment(SwingConstants.CENTER);
    lKontouebersicht1.setHorizontalTextPosition(SwingConstants.CENTER);
    jPanel2.add(lKontouebersicht1);
    jPanel3.setBounds(608, 56, 177, 457);
    jPanel3.setOpaque(true);
    jPanel3.setBackground(new Color(0x404040));
    cp.add(jPanel3);
    lKontoinformation1.setBounds(0, 8, 176, 24);
    lKontoinformation1.setText("Kontoinformation");
    lKontoinformation1.setHorizontalAlignment(SwingConstants.CENTER);
    lKontoinformation1.setHorizontalTextPosition(SwingConstants.CENTER);
    lKontoinformation1.setFont(new Font("Dialog", Font.BOLD, 12));
    lKontoinformation1.setForeground(Color.WHITE);
    jPanel3.add(lKontoinformation1);
    lKontoinhaber.setBounds(0, 48, 176, 24);
    //lKontoinhaber.setText("Kontoinhaber: Test User");
    lKontoinhaber.setHorizontalAlignment(SwingConstants.CENTER);
    lKontoinhaber.setHorizontalTextPosition(SwingConstants.CENTER);
    lKontoinhaber.setFont(new Font("Dialog", Font.BOLD, 12));
    lKontoinhaber.setForeground(Color.WHITE);
    jPanel3.add(lKontoinhaber);
    lIBAN.setBounds(0, 88, 176, 24);
    //lIBAN.setText("IBAN: DE 30321912");
    lIBAN.setHorizontalAlignment(SwingConstants.CENTER);
    lIBAN.setHorizontalTextPosition(SwingConstants.CENTER);
    lIBAN.setFont(new Font("Dialog", Font.BOLD, 12));
    lIBAN.setForeground(Color.WHITE);
    jPanel3.add(lIBAN);
    lKontoArt.setBounds(0, 128, 176, 24);
    //lKontoArt.setText("Konto: Girokonto");
    lKontoArt.setHorizontalAlignment(SwingConstants.CENTER);
    lKontoArt.setHorizontalTextPosition(SwingConstants.CENTER);
    lKontoArt.setFont(new Font("Dialog", Font.BOLD, 12));
    lKontoArt.setForeground(Color.WHITE);
    jPanel3.add(lKontoArt);
    lKontostand1.setBounds(0, 168, 176, 24);
    //lKontostand1.setText("Kontostand: 3.430,48€");
    lKontostand1.setHorizontalAlignment(SwingConstants.CENTER);
    lKontostand1.setHorizontalTextPosition(SwingConstants.CENTER);
    lKontostand1.setFont(new Font("Dialog", Font.BOLD, 12));
    lKontostand1.setForeground(Color.WHITE);
    jPanel3.add(lKontostand1);
    
    jPanel5.setBounds(144, 96, 465, 417);
    jPanel5.setOpaque(true);
    jPanel5.setBackground(new Color(0xC0C0C0));
    cp.add(jPanel5);
    
    setVisible(true);
  } 
  
  private void loadAccountInformation(int userId) {
    try (Connection conn = DatabaseConnection.getConnection()) {
      String sql = "SELECT k.kontonummer, k.saldo, k.kontotyp, b.benutzername " +
      "FROM Konten k " +
      "JOIN Benutzer b ON k.benutzer_id = b.benutzer_id " +
      "WHERE b.benutzer_id = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
          String kontoinhaber = rs.getString("benutzername");
          String iban = rs.getString("kontonummer");
          String kontoArt = rs.getString("kontotyp");
          String kontostand = rs.getString("saldo");
          
          lKontoinhaber.setText("Kontoinhaber: " + kontoinhaber);
          lIBAN.setText("IBAN: " + iban);
          lKontoArt.setText("Konto: " + kontoArt);
          lKontostand1.setText("Kontostand: " + kontostand + " €");
        } else {
          JOptionPane.showMessageDialog(this, "Keine Kontoinformationen gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Fehler beim Laden der Kontoinformationen: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  public static void main(String[] args) {
    // Beispielaufruf mit einer Benutzer-ID
    new OnlineBanking(4); // Hier die Benutzer-ID anpassen
  } // end of main
  // Ende Methoden
} // end of class OnlineBanking

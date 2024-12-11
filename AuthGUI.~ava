import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.math.BigDecimal;

/**
 *
 * Description
 *
 * @version 1.0 from 08.12.2024
 * @author 
 */

public class AuthGUI extends JFrame {
  private JLabel lSpasskasseEssen2 = new JLabel();
  // start attributes
  private JLabel lAuthentifizierung1 = new JLabel();
  private JTextField jNameField = new JTextField();
  private JPasswordField jPasswortField = new JPasswordField(20);
  private JOptionPane jOptionPane1 = new JOptionPane();
  
  private JButton bAnmelden1 = new JButton();
  private JButton bRegistrieren1 = new JButton();
  // end attributes
  public AuthGUI() { 
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 400; 
    int frameHeight = 550;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Authentifizierung");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    setUndecorated(true);
    // start components
    cp.setBackground(Color.RED);
    
    lSpasskasseEssen2.setBounds(0, 64, 384, 31);
    lSpasskasseEssen2.setText("Spaßkasse Essen");
    lSpasskasseEssen2.setFont(new Font("Bahnschrift", Font.BOLD, 22));
    lSpasskasseEssen2.setForeground(Color.WHITE);
    lSpasskasseEssen2.setHorizontalAlignment(SwingConstants.CENTER);
    lSpasskasseEssen2.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lSpasskasseEssen2);
    lAuthentifizierung1.setBounds(0, 128, 384, 24);
    lAuthentifizierung1.setText("Authentifizierung");
    lAuthentifizierung1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
    lAuthentifizierung1.setForeground(Color.WHITE);
    lAuthentifizierung1.setHorizontalAlignment(SwingConstants.CENTER);
    lAuthentifizierung1.setHorizontalTextPosition(SwingConstants.CENTER);
    cp.add(lAuthentifizierung1);
    jNameField.setBounds(56, 192, 281, 41);
    //jNameField.setText("Anmeldename");
    jNameField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Anmeldename", 1, 0, new Font("Century Gothic", Font.BOLD, 12), Color.white));
    jNameField.setForeground(Color.WHITE);
    jNameField.setOpaque(false);
    jNameField.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
    cp.add(jNameField);
    jPasswortField.setBounds(56, 272, 281, 41);
    jPasswortField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white, 2), "Online-Banking-PIN", 1, 0, new Font("Century Gothic", Font.BOLD, 12), Color.white));
    jPasswortField.setOpaque(false);
    jPasswortField.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
    jPasswortField.setForeground(Color.white);
    cp.add(jPasswortField);
    
    bAnmelden1.setBounds(120, 352, 144, 40);
    bAnmelden1.setText("Anmelden");
    bAnmelden1.setMargin(new Insets(2, 2, 2, 2));
    bAnmelden1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
          authenticate();
      }
  });
    bAnmelden1.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1));
    cp.add(bAnmelden1);
    bRegistrieren1.setBounds(120, 400, 144, 40);
    bRegistrieren1.setText("Registrieren");
    bRegistrieren1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                openRegistrationDialog();
            }
        });
    cp.add(bRegistrieren1);
    // end components
    setVisible(true);
  }
  
  private void openRegistrationDialog() {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField emailField = new JTextField();

    Object[] message = {
      "Benutzername:", usernameField,
      "Passwort:", passwordField,
      "E-Mail:", emailField
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Registrierung", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      String username = usernameField.getText();
      String password = new String(passwordField.getPassword());
      String email = emailField.getText();

      registerUser(username, password, email);
    }
  }

  private void registerUser(String username, String password, String email) {
    try (Connection conn = DatabaseConnection.getConnection()) {
      String sqlUser = "INSERT INTO Benutzer (benutzername, passwort, email) VALUES (?, ?, ?)";
      try (PreparedStatement pstmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
        pstmtUser.setString(1, username);
        pstmtUser.setString(2, password);
        pstmtUser.setString(3, email);
        pstmtUser.executeUpdate();

        ResultSet generatedKeys = pstmtUser.getGeneratedKeys();
        if (generatedKeys.next()) {
          int userId = generatedKeys.getInt(1);

          String sqlAccount = "INSERT INTO Konten (benutzer_id, kontonummer, saldo, kontotyp) VALUES (?, ?, ?, ?)";
          try (PreparedStatement pstmtAccount = conn.prepareStatement(sqlAccount)) {
            pstmtAccount.setInt(1, userId);
            pstmtAccount.setString(2, generateAccountNumber());
            pstmtAccount.setBigDecimal(3, new BigDecimal(0.00));
            pstmtAccount.setString(4, "Girokonto");
            pstmtAccount.executeUpdate();
          }
        }

        JOptionPane.showMessageDialog(this, "Registrierung erfolgreich!", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Fehler bei der Registrierung: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }

  private String generateAccountNumber() {
    return "DE" + (int)(Math.random() * 1000000000);
  }

  private void authenticate() {
    String username = jNameField.getText();
    String password = new String(jPasswortField.getPassword());
    
    try (Connection conn = DatabaseConnection.getConnection()) {
      String sql = "SELECT * FROM Benutzer WHERE benutzername = ? AND passwort = ?";
      try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
          int userId = rs.getInt("benutzer_id");
          new OnlineBanking(userId);
          dispose();
        } else {
          JOptionPane.showMessageDialog(this, "Ungültiger Benutzername oder Passwort", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
      }
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(this, "Fehler bei der Authentifizierung: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  public static void main(String[] args) {
    new AuthGUI();
  }
  
}
 

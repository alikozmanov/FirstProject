import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class MainFrame extends JFrame {
    // Définition de la police principale
    final private Font mainFont = new Font("Segoe Print", Font.BOLD, 18); 

    // Déclaration des champs de texte et du label
    JTextField tfFirstName, tfLastName, tfAddress;
    JLabel lbWelcome;

    // Méthode pour initialiser la fenêtre
    public void initialize() {
        /************ Form Panel ************/
        // Création des composants pour le formulaire
        JLabel lbFirstName = new JLabel("First Name");
        lbFirstName.setFont(mainFont);

        tfFirstName = new JTextField();
        tfFirstName.setFont(mainFont);

        JLabel lbLastName = new JLabel("Last Name");
        lbLastName.setFont(mainFont);

        tfLastName = new JTextField();
        tfLastName.setFont(mainFont);

        // Ajout de la liste déroulante pour le genre
        JLabel lbGender = new JLabel("Gender");
        lbGender.setFont(mainFont);

        String[] genderOptions = {"Male", "Female"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
        genderComboBox.setFont(mainFont);

        // Ajout du champ d'adresse
        JLabel lbAddress = new JLabel("Address");
        lbAddress.setFont(mainFont);

        tfAddress = new JTextField();
        tfAddress.setFont(mainFont);

        // Création du panneau de formulaire
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 5, 5)); // Ajustement du nombre de lignes pour inclure les nouveaux éléments
        formPanel.setOpaque(false);
        formPanel.add(lbFirstName);
        formPanel.add(tfFirstName);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        formPanel.add(lbGender);
        formPanel.add(genderComboBox);
        formPanel.add(lbAddress);
        formPanel.add(tfAddress);

        /************ Welcome Label ************/
        // Création du label de bienvenue
        lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);

        /************ Buttons Panel ************/
        // Création des boutons et ajout des écouteurs d'événements
        JButton btnOK = new JButton("OK");
        btnOK.setFont(mainFont);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validation des champs
                String firstName = tfFirstName.getText().trim();
                String lastName = tfLastName.getText().trim();

                if (firstName.isEmpty() || lastName.isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Veuillez remplir tous les champs.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!isValidName(firstName) || !isValidName(lastName)) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Les noms ne doivent contenir que des lettres.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Traitement lorsque le bouton OK est cliqué
                    String selectedGender = (String) genderComboBox.getSelectedItem();
                    String address = tfAddress.getText().trim();
                    lbWelcome.setText("Hello " + firstName + " " + lastName + "! Gender: " + selectedGender + ", Address: " + address);
                }
            }
        });

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Traitement lorsque le bouton Clear est cliqué
                tfFirstName.setText("");
                tfLastName.setText("");
                genderComboBox.setSelectedIndex(0); // Réinitialise la liste déroulante du genre
                tfAddress.setText("");
                lbWelcome.setText("");
            }
        });

        // Création du panneau de boutons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnOK);
        buttonsPanel.add(btnClear);

        // Création du panneau principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(128, 128, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lbWelcome, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Ajout du panneau principal à la fenêtre
        add(mainPanel);

        // Configuration de la fenêtre
        setTitle("Welcome");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Méthode pour valider les noms
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    // Méthode principale
    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
        myFrame.initialize();
    }
}
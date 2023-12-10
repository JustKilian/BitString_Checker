import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 *
 * @version 1.0 from 19.11.2023
 * @author Celine J
 */
public class Main extends JFrame{
    // start attributes
    private JButton pruefen = new JButton();
    private JLabel ueberschrift = new JLabel();
    private JLabel ausgabe = new JLabel();
    private JTextField eingabe = new JTextField();
    private JButton pruefen11 = new JButton();

    private boolean z0, z1, z2, z3;
    // end attributes

    public Main() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 527;
        int frameHeight = 300;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Main");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // start components

        pruefen.setBounds(25, 125, 100, 30);
        pruefen.setText("Prüfen");
        pruefen.setMargin(new Insets(2, 2, 2, 2));
        pruefen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pruefen_ActionPerformed(evt);
            }
        });
        cp.add(pruefen);

        ueberschrift.setBounds(25, 25, 250, 25);
        ueberschrift.setText("Geben Sie einen Bitstring ein:");
        cp.add(ueberschrift);

        ausgabe.setBounds(25, 200, 450, 25);
        ausgabe.setText("");
        cp.add(ausgabe);

        eingabe.setBounds(25, 75, 250, 25);
        eingabe.setText("");
        cp.add(eingabe);

        pruefen11.setBounds(150, 125, 100, 30);
        pruefen11.setText("Prüfen 11");
        pruefen11.setMargin(new Insets(2, 2, 2, 2));
        pruefen11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pruefen11_ActionPerformed(evt);
            }
        });
        cp.add(pruefen11);
        // end components

        setVisible(true);
    } // end of public Main

    // start methods

    public static void main(String[] args) {
        new Main();
    } // end of main

    /**
     * Prüft, ob die Eingabe ein gültiger Bitstring ist
     * @param evt ActionEvent
     */
    private void pruefen_ActionPerformed(ActionEvent evt) {

        // Eingabe auslesen
        String eingabeString = eingabe.getText();

        if (eingabeString.isEmpty()) { // Überprüfen, ob die Eingabe leer ist.

            // Wird automatisch akzeptiert, da die Eingabe leer ist und somit sowohl 0 als auch 1 gerader Anzahl ist.
            ausgabe.setText("Akzeptiert");

        } else if (!eingabeString.matches("[01]+")) { // Überprüfen, ob die Eingabe aus anderen Zeichen als 0 oder 1 besteht (Regulärer Ausdruck)

            // Wird automatisch nicht akzeptiert, da die Eingabe aus anderen Zeichen als 0 oder 1 besteht.
            ausgabe.setText("Nicht akzeptiert");

        } else if (eingabeString.length() % 2 != 0) { // Überprüfen, ob die Eingabe eine ungerade Anzahl an Zeichen hat.

            // Wird automatisch nicht akzeptiert, da die Eingabe eine ungerade Anzahl an Zeichen hat. Denn zweimal gerade ergibt wieder gerade.
            // Das bedeutet, dass in der Eingabe mindestens einmal eine Zahl ungerade oft vorkommen muss.
            ausgabe.setText("Nicht akzeptiert");

        } else {
            // Alle Zustände initialisieren (z0 = Startzustand)
            setZ0();

            // Eingabe in ein Array aus einzelnen Zeichen umwandeln
            String[] eingabeArray = eingabeString.split("");

            // Zustandsübergänge durchführen
            for (String s : eingabeArray) {
                if (z0) {
                    if (s.equals("0")) {
                        setZ2();
                    } else {
                        setZ1();
                    }
                } else if (z1) {
                    if (s.equals("0")) {
                        setZ3();
                    } else {
                        setZ0();
                    }
                } else if (z2) {
                    if (s.equals("0")) {
                        setZ0();
                    } else {
                        setZ3();
                    }
                } else if (z3) {
                    if (s.equals("0")) {
                        setZ1();
                    } else {
                        setZ2();
                    }
                }
            }// Prüfen, ob der letzte Zustand z0 ist
            if (z0) {
                ausgabe.setText("Akzeptiert");
                // Prüfen, ob der letzte Zustand z1, z2 oder z3 ist
            } else {
                ausgabe.setText("Nicht akzeptiert");
            }
        }
    }

    private void pruefen11_ActionPerformed(ActionEvent evt) {
        String eingabeString = eingabe.getText();

        if (eingabeString.isEmpty()) {
            ausgabe.setText("Nicht akzeptiert");
        } else if (!eingabeString.matches("[01]+")) {
            ausgabe.setText("Nicht akzeptiert");
        } else {
            setZ0();

            String[] eingabeArray = eingabeString.split("");

            for (String s : eingabeArray) {
                if (z0) {
                    if (s.equals("0")) {
                        setZ2();
                    } else {
                        setZ1();
                    }
                } else if (z1) {
                    if (s.equals("0")) {
                        setZ3();
                    } else {
                        setZ1();
                    }
                } else if (z2) {
                    if (s.equals("0")) {
                        setZ2();
                    } else {
                        setZ2();
                    }
                } else if (z3) {
                    if (s.equals("0")) {
                        setZ3();
                    } else {
                        setZ1();
                    }
                }
            }
            if (z1) {
                ausgabe.setText("Akzeptiert");
            } else {
                ausgabe.setText("Nicht akzeptiert");
            }
        }
    }

    /**
     * Setzt alle Zustände auf false, außer z0
     */
    private void setZ0() {
        z0 = true;
        z1 = false;
        z2 = false;
        z3 = false;
    }

    /**
     * Setzt alle Zustände auf false, außer z1
     */
    private void setZ1() {
        z0 = false;
        z1 = true;
        z2 = false;
        z3 = false;
    }

    /**
     * Setzt alle Zustände auf false, außer z2
     */
    private void setZ2() {
        z0 = false;
        z1 = false;
        z2 = true;
        z3 = false;
    }

    /**
     * Setzt alle Zustände auf false, außer z3
     */
    private void setZ3() {
        z0 = false;
        z1 = false;
        z2 = false;
        z3 = true;
    }
}

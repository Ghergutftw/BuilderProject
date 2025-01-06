import builders.CarBuilder;
import cars.Car;
import cars.CarType;
import cars.Manual;
import components.Engine;
import components.Transmission;
import components.TripComputer;
import components.GPSNavigator;
import director.Director;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarBuilderApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarBuilderUI::new);
    }
}

class CarBuilderUI {
    private final JComboBox<String> carTypeComboBox;
    private final JTextField seatsField;
    private final JComboBox<String> transmissionComboBox;
    private final JCheckBox tripComputerCheckBox;
    private final JCheckBox gpsNavigatorCheckBox;
    private final JTextArea resultArea;
    private final JLabel priceLabel;

    public CarBuilderUI() {
        // Creare fereastră
        JFrame frame = new JFrame("Constructor de Mașini");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Panou principal pentru intrări
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tip mașină
        inputPanel.add(new JLabel("Tip Mașină:"));
        carTypeComboBox = new JComboBox<>(new String[]{"Mașină de Oraș", "Mașină Sport", "SUV"});
        inputPanel.add(carTypeComboBox);

        // Număr de locuri
        inputPanel.add(new JLabel("Locuri:"));
        seatsField = new JTextField("2");
        inputPanel.add(seatsField);

        // Transmisie
        inputPanel.add(new JLabel("Transmisie:"));
        transmissionComboBox = new JComboBox<>(new String[]{"Manuală", "Automată", "Semi-Automată"});
        inputPanel.add(transmissionComboBox);

        // Computer de bord
        inputPanel.add(new JLabel("Computer de Bord:"));
        tripComputerCheckBox = new JCheckBox("Include");
        inputPanel.add(tripComputerCheckBox);

        // Navigator GPS
        inputPanel.add(new JLabel("Navigator GPS:"));
        gpsNavigatorCheckBox = new JCheckBox("Include");
        inputPanel.add(gpsNavigatorCheckBox);

        // Etichetă preț
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        priceLabel = new JLabel("Preț: $0.00");
        pricePanel.add(priceLabel);

        // Zonă de afișare a rezultatelor
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setMargin(new Insets(10, 10, 10, 10)); // Adaugă margini
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Font monospațiat pentru aliniere
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Detalii Mașină"));

        // Panou pentru butonul de construire a mașinii
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buildCarButton = new JButton("Construiește Mașina");
        buttonPanel.add(buildCarButton);

        // Adăugare componente în fereastră
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Adăugare ascultător de evenimente
        buildCarButton.addActionListener(new BuildCarActionListener());

        // Afișare fereastră
        frame.setVisible(true);
    }

    private class BuildCarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Preluare intrări utilizator
                String carType = (String) carTypeComboBox.getSelectedItem();
                int seats = Integer.parseInt(seatsField.getText());
                String transmission = (String) transmissionComboBox.getSelectedItem();
                boolean includeTripComputer = tripComputerCheckBox.isSelected();
                boolean includeGPSNavigator = gpsNavigatorCheckBox.isSelected();

                // Construire mașină folosind Director și Builder
                CarBuilder builder = new CarBuilder();
//                Director director = new Director();
//
//                switch (carType) {
//                    case "Mașină de Oraș" -> director.constructCityCar(builder);
//                    case "Mașină Sport" -> director.constructSportsCar(builder);
//                    case "SUV" -> director.constructSUV(builder);
//                    default -> throw new IllegalStateException("Unexpected value: " + carType);
//                }

                // Personalizare mașină
                builder.setSeats(seats);

                switch (carType) {
                    case "Mașină de Oraș" -> builder.setCarType(CarType.CITY_CAR);
                    case "Mașină Sport" -> builder.setCarType(CarType.SPORTS_CAR);
                    case "SUV" -> builder.setCarType(CarType.SUV);
                }

                if (includeTripComputer) {
                    builder.setTripComputer(new TripComputer());
                }
                if (includeGPSNavigator) {
                    builder.setGPSNavigator(new GPSNavigator());
                }

                switch (transmission) {
                    case "Manuală" -> builder.setTransmission(Transmission.MANUAL);
                    case "Automată" -> builder.setTransmission(Transmission.AUTOMATIC);
                    case "Semi-Automată" -> builder.setTransmission(Transmission.SEMI_AUTOMATIC);
                }

                Car car = builder.getResult();

                // Actualizare etichetă preț
                priceLabel.setText("Preț: $" + String.format("%.2f", car.getPrice()));

                // Afișare detalii mașină
                resultArea.setText(
                        "Mașină Construită:\n" +
                                "-------------------\n" +
                                "Tip: " + getCarType(car.getCarType()) + "\n" +
                                "Locuri: " + car.getSeats() + "\n" +
                                "Transmisie: " + getTransmission(car.getTransmission()) + "\n" +
                                "Computer de Bord: " + (car.getTripComputer() != null ? "Da" : "Nu") + "\n" +
                                "Navigator GPS: " + (car.getGpsNavigator() != null ? "Da" : "Nu") + "\n" +
                                "Preț: $" + String.format("%.2f", car.getPrice()) + "\n"
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Intrare invalidă: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getTransmission(Transmission transmission) {
        switch (transmission) {
            case MANUAL -> {
                return "Manuală";
            }
            case AUTOMATIC -> {
                return "Automată";
            }
            case SEMI_AUTOMATIC -> {
                return "Semi-Automată";
            }
            default -> {
                return "Necunoscut";
            }
        }
    }

    private String getCarType(CarType carType) {
        switch (carType) {
            case CITY_CAR -> {
                return "Mașină de Oraș";
            }
            case SPORTS_CAR -> {
                return "Mașină Sport";
            }
            case SUV -> {
                return "SUV";
            }
            case VAN -> {
                return "Van";
            }
            default -> {
                return "Necunoscut";
            }
        }
    }
}


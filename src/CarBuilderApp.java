import builders.CarBuilder;
import cars.Car;
import cars.CarType;
import components.Transmission;
import director.Director;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CarBuilderApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarBuilderUI::new);
    }
}

class CarBuilderUI {
    private final JComboBox<String> carTypeComboBox;
    private final JTextField seatsField;
    private final JTextField engineVolumField;
    private final JComboBox<String> transmissionComboBox;
    private final JCheckBox tripComputerCheckBox;
    private final JCheckBox gpsNavigatorCheckBox;
    private final JTextArea resultArea;
    private final JLabel priceLabel;

    public CarBuilderUI() {
        // Create the main frame
        JFrame frame = new JFrame("Constructor de Mașini");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 460);
        frame.setLayout(new BorderLayout(10, 10));

        // Main input panel with reduced spacing
        JPanel inputPanel = new JPanel();
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Components for input panel
        JLabel carTypeLabel = new JLabel("Tip Mașină:");
        carTypeComboBox = new JComboBox<>(new String[]{"Custom", "Mașină de Oraș", "Mașină Sport", "SUV"});
        carTypeComboBox.addActionListener(e -> toggleCustomFields());

        JLabel seatsLabel = new JLabel("Locuri:");
        seatsField = new JTextField("4");

        JLabel engineVolumLabel = new JLabel("Volum motor:");
        engineVolumField = new JTextField("1.6");

        JLabel transmissionLabel = new JLabel("Transmisie:");
        transmissionComboBox = new JComboBox<>(new String[]{"Manuală", "Automată", "Semi-Automată"});

        JLabel tripComputerLabel = new JLabel("Computer de Bord:");
        tripComputerCheckBox = new JCheckBox("Include");

        JLabel gpsNavigatorLabel = new JLabel("Navigator GPS:");
        gpsNavigatorCheckBox = new JCheckBox("Include");

        priceLabel = new JLabel("Preț: $0.00");

        // Arrange components using GroupLayout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(carTypeLabel)
                                .addComponent(seatsLabel)
                                .addComponent(engineVolumLabel)
                                .addComponent(transmissionLabel)
                                .addComponent(tripComputerLabel)
                                .addComponent(gpsNavigatorLabel)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(carTypeComboBox)
                                .addComponent(seatsField)
                                .addComponent(engineVolumField)
                                .addComponent(transmissionComboBox)
                                .addComponent(tripComputerCheckBox)
                                .addComponent(gpsNavigatorCheckBox)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(carTypeLabel)
                                .addComponent(carTypeComboBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(seatsLabel)
                                .addComponent(seatsField)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(engineVolumLabel)
                                .addComponent(engineVolumField)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(transmissionLabel)
                                .addComponent(transmissionComboBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tripComputerLabel)
                                .addComponent(tripComputerCheckBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(gpsNavigatorLabel)
                                .addComponent(gpsNavigatorCheckBox)
                        )
        );

        // Result area
        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Detalii Mașină"));
        resultPanel.setLayout(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 12));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10px margin on all sides

        resultPanel.add(resultArea, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buildCarButton = new JButton("Construiește Mașina");
        buttonPanel.add(buildCarButton);

        // Add components to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultArea, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener
        buildCarButton.addActionListener(new BuildCarActionListener());

        // Show the frame
        frame.setVisible(true);
    }

    private String getTransmission(Transmission transmission) {
        return switch (transmission) {
            case MANUAL -> "Manuală";
            case AUTOMATIC -> "Automată";
            case SEMI_AUTOMATIC -> "Semi-Automată";
            default -> "Necunoscut";
        };
    }

    private String getCarType(CarType carType) {
        return switch (carType) {
            case CITY_CAR -> "Mașină de Oraș";
            case SPORTS_CAR -> "Mașină Sport";
            case SUV -> "SUV";
            case CUSTOM -> "Custom";
            default -> "Necunoscut";
        };
    }

    private void toggleCustomFields() {
        String selectedCarType = (String) carTypeComboBox.getSelectedItem();
        boolean isCustom = Objects.equals(selectedCarType, "Custom");

        // Enable/disable fields based on selection
        seatsField.setEditable(isCustom);
        engineVolumField.setEditable(isCustom);
        transmissionComboBox.setEnabled(isCustom);
        tripComputerCheckBox.setEnabled(isCustom);
        gpsNavigatorCheckBox.setEnabled(isCustom);

        if (!isCustom) {
            // Pre-fill and disable fields for specific car types
            switch (selectedCarType) {
                case "Mașină de Oraș" -> {
                    seatsField.setText("4");
                    engineVolumField.setText("1.2");
                    transmissionComboBox.setSelectedItem("Automată");
                    tripComputerCheckBox.setSelected(false);
                    gpsNavigatorCheckBox.setSelected(true);
                }
                case "Mașină Sport" -> {
                    seatsField.setText("2");
                    engineVolumField.setText("3.0");
                    transmissionComboBox.setSelectedItem("Semi-Automată");
                    tripComputerCheckBox.setSelected(true);
                    gpsNavigatorCheckBox.setSelected(true);
                }
                case "SUV" -> {
                    seatsField.setText("4");
                    engineVolumField.setText("2.5");
                    transmissionComboBox.setSelectedItem("Manuală");
                    tripComputerCheckBox.setSelected(false);
                    gpsNavigatorCheckBox.setSelected(true);
                }
                case null -> {}
                default -> throw new IllegalStateException("Unexpected value: " + selectedCarType);
            }
        } else {
            // Clear fields for custom input
            seatsField.setText("4");
            transmissionComboBox.setSelectedIndex(0);
            engineVolumField.setText("1.6");
            tripComputerCheckBox.setSelected(false);
            gpsNavigatorCheckBox.setSelected(false);
        }

        // Update visibility
        seatsField.setVisible(true);
        transmissionComboBox.setVisible(true);
        tripComputerCheckBox.setVisible(true);
        gpsNavigatorCheckBox.setVisible(true);

        // Refresh UI
        seatsField.getParent().revalidate();
        seatsField.getParent().repaint();
    }


    private class BuildCarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String carType = (String) carTypeComboBox.getSelectedItem();

                boolean includeTripComputer = tripComputerCheckBox.isSelected();
                boolean includeGPSNavigator = gpsNavigatorCheckBox.isSelected();

                // Create builder and director
                CarBuilder builder = new CarBuilder();
                Director director = new Director();

                // Use director for predefined car types
                switch (Objects.requireNonNull(carType)) {
                    case "Mașină de Oraș" -> director.constructCityCar(builder);
                    case "Mașină Sport" -> director.constructSportsCar(builder);
                    case "SUV" -> director.constructSUV(builder);
                    case "Custom" -> {
                        // Custom configuration
                        int seats = Integer.parseInt(seatsField.getText());
                        double engineVolume = Double.parseDouble(engineVolumField.getText());
                        String transmission = (String) transmissionComboBox.getSelectedItem();

                        builder.setCarType(CarType.CUSTOM);
                        builder.setSeats(seats);

                        builder.setTripComputer(includeTripComputer);
                        builder.setGPSNavigator(includeGPSNavigator);
                        builder.setEngineVolume(engineVolume);
                        // Set transmission
                        switch (transmission) {
                            case "Manuală" -> builder.setTransmission(Transmission.MANUAL);
                            case "Automată" -> builder.setTransmission(Transmission.AUTOMATIC);
                            case "Semi-Automată" -> builder.setTransmission(Transmission.SEMI_AUTOMATIC);
                            case null -> {}
                            default -> throw new IllegalStateException("Unexpected value: " + transmission);
                        }

                    }
                    default -> throw new IllegalStateException("Unexpected value: " + carType);
                }

                // Get the constructed car
                Car car = builder.getResult();

                // Update price label
                priceLabel.setText("Preț: $" + String.format("%.2f", car.getPrice()));

                // Display car details
                resultArea.setText(
                        "Mașină Construită:\n" +
                                "-------------------\n" +
                                "Tip: " + getCarType(car.getCarType()) + "\n" +
                                "Locuri: " + car.getSeats() + "\n" +
                                "Volum Motor: " + car.getEngineVolume() + "L\n" +
                                "Transmisie: " + getTransmission(car.getTransmission()) + "\n" +
                                "Computer de Bord: " + (car.getTripComputer() != null ? "Da" : "Nu") + "\n" +
                                "Navigator GPS: " + (car.getGpsNavigator() != null ? "Da" : "Nu") + "\n" +
                                "Preț: " + String.format("%.2f", car.getPrice()) + "€" + "\n" +
                                getCarAscii(car.getCarType())
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Intrare invalidă: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getCarAscii(CarType carType) {
        return switch (carType) {
            case CITY_CAR -> """
                 ______
               _/  |   \\_
              |           |
              |___________|
              O           O
              """;
            case SPORTS_CAR -> """
                 ______
                /|_||_\\`.__
               (   _    _ _\\
               =`-(_)--(_)-'
              """;
            case SUV -> """
                _______
              //  ||\\ \\
        _____//___||_\\ \\___
        )  _          _    \\
        |_/ \\________/ \\___|
        ___\\_/________\\_/_____
              """;
            case CUSTOM -> """
               _______
             _/       \\_
            |           |
           (|  Custom   |)
            |___________|
             O         O
              """;
            default -> """
                 ______
               _/  |   \\_
              |           |
              |___________|
              O           O
              """;
        };
    }

}


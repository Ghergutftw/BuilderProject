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
    private final JLabel asciiArtLabel;

    public CarBuilderUI() {
        // Create the main frame
        JFrame frame = new JFrame("Constructor de Mașini");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 550);
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

        JPanel resultPanel = new JPanel();
        resultPanel.setBorder(BorderFactory.createTitledBorder("Detalii Mașină"));
        resultPanel.setLayout(new BorderLayout());

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        resultArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        asciiArtLabel = new JLabel();
        asciiArtLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        asciiArtLabel.setVerticalAlignment(SwingConstants.TOP);

        JPanel sideBySidePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        sideBySidePanel.add(new JScrollPane(resultArea));
        sideBySidePanel.add(asciiArtLabel);

        resultPanel.add(sideBySidePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buildCarButton = new JButton("Construiește Mașina");
        buttonPanel.add(buildCarButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(resultPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        buildCarButton.addActionListener(new BuildCarActionListener());

        frame.setVisible(true);
    }

    private String getTransmission(Transmission transmission) {
        return switch (transmission) {
            case MANUAL -> "Manuală";
            case AUTOMATIC -> "Automată";
            case SEMI_AUTOMATIC -> "Semi-Automată";
        };
    }

    private String getCarType(CarType carType) {
        return switch (carType) {
            case CITY_CAR -> "Mașină de Oraș";
            case SPORTS_CAR -> "Mașină Sport";
            case SUV -> "SUV";
            case CUSTOM -> "Custom";
        };
    }

    private void toggleCustomFields() {
        String selectedCarType = (String) carTypeComboBox.getSelectedItem();
        boolean isCustom = Objects.equals(selectedCarType, "Custom");

        seatsField.setEditable(isCustom);
        engineVolumField.setEditable(isCustom);
        transmissionComboBox.setEnabled(isCustom);
        tripComputerCheckBox.setEnabled(isCustom);
        gpsNavigatorCheckBox.setEnabled(isCustom);

        if (!isCustom) {
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
            seatsField.setText("4");
            transmissionComboBox.setSelectedIndex(0);
            engineVolumField.setText("1.6");
            tripComputerCheckBox.setSelected(false);
            gpsNavigatorCheckBox.setSelected(false);
        }

        seatsField.setVisible(true);
        transmissionComboBox.setVisible(true);
        tripComputerCheckBox.setVisible(true);
        gpsNavigatorCheckBox.setVisible(true);

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

                CarBuilder builder = new CarBuilder();
                Director director = new Director();

                switch (Objects.requireNonNull(carType)) {
                    case "Mașină de Oraș" -> director.constructCityCar(builder);
                    case "Mașină Sport" -> director.constructSportsCar(builder);
                    case "SUV" -> director.constructSUV(builder);
                    case "Custom" -> {
                        int seats = Integer.parseInt(seatsField.getText());
                        double engineVolume = Double.parseDouble(engineVolumField.getText());
                        String transmission = (String) transmissionComboBox.getSelectedItem();

                        builder.setCarType(CarType.CUSTOM);
                        builder.setSeats(seats);
                        builder.setTripComputer(includeTripComputer);
                        builder.setGPSNavigator(includeGPSNavigator);
                        builder.setEngineVolume(engineVolume);

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

                Car car = builder.getResult();

                priceLabel.setText("Preț: $" + String.format("%.2f", car.getPrice()));

                resultArea.setText(
                        "Mașină Construită:\n" +
                                "-------------------\n" +
                                "Tip: " + getCarType(car.getCarType()) + "\n" +
                                "Locuri: " + car.getSeats() + "\n" +
                                "Volum Motor: " + car.getEngineVolume() + "L\n" +
                                "Transmisie: " + getTransmission(car.getTransmission()) + "\n" +
                                "Computer de Bord: " + (car.getTripComputer() != null ? "Da" : "Nu") + "\n" +
                                "Navigator GPS: " + (car.getGpsNavigator() != null ? "Da" : "Nu") + "\n" +
                                "Preț: " + String.format("%.2f", car.getPrice()) + "€" + "\n"
                );

                asciiArtLabel.setText("<html><pre>" + getCarAscii(car.getCarType()) + "</pre></html>");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Intrare invalidă: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getCarAscii(CarType carType) {
        return switch (carType) {
            case CITY_CAR -> """
               -           __
             --          ~( @\\   \\
            ---   _________]_[__/_>________
                 /  ____ \\ <>     |  ____  \\
                =\\_/ __ \\_\\_______|_/ __ \\__D
            ________(__)_____________(__)____
             \s""";
            case SPORTS_CAR -> """
                                  ____----------- _____
                    \\~~~~~~~~~~/~_--~~~------~~~~~     \\
                     `---`\\  _-~      |                   \\
                       _-~  <_         |                     \\[]
                     / ___     ~~--[""] |      ________-------'_
                    > /~` \\    |-.   `\\~~.~~~~~                _ ~ - _
                     ~|  ||\\%  |       |    ~  ._                ~ _   ~ ._
                       `_//|_%  \\      |          ~  .              ~-_   /\\
                              `--__     |    _-____  /\\               ~-_ \\/.
                                   ~--_ /  ,/ -~-_ \\ \\/          _______---~/
                                       ~~-/._<   \\ \\`~~~~~~~~~~~~~     ##--~/
                                             \\    ) |`------##---~~~~-~  ) )
                                              ~-_/_/                  ~~ ~~
              """;
            case SUV -> """
                _______
              //  ||\\ \\
        _____//___||_\\ \\___
        )  _          _    \\
        |_/ \\________/ \\___|
        ___\\_/________\\_/_____
             \s""";
            case CUSTOM -> """
                                                      @
                                   (__)    (__) _____/
                                /| (oo) _  (oo)/----/_____    *
                      _o\\______/_|\\_\\/_/_|__\\/|____|//////== *- *  * -
                     /_________   \\   00 |   00 |       /== -* * -
                    [_____/^^\\_____\\_____|_____/^^\\_____]     *- * -
                          \\__/                 \\__/
              """;
        };
    }
}

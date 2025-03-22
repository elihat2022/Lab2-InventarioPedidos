import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;


public class FormularioPedido extends JFrame {
    // Componentes
    private ButtonGroup grupoBotones;
    private JTextField nombreField;
    private JLabel nombreLabel;
    private JLabel tipoMLabel;
    private JLabel cantidadLabel;
    private JLabel distribuidorLabel;
    private JLabel sucursalLabel;
    private JComboBox<String> tipoMedicamentoField;
    private JTextField cantidadField;
    private JRadioButton cofarmaRadioButton;
    private JRadioButton empsepharRadioButton;
    private JRadioButton cemefarRadioButton;
    private JCheckBox principalCheckBox;
    private JCheckBox secundariaCheckBox;
    JButton borrarButton;
    JButton confirmarButton;
    JButton verPedidosButton;
    // Constructor
    public FormularioPedido() {
        initUI();
    }
    // Métodos
    private void initUI() {
        setTitle("Pedidos");
        setSize(650, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane =  new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel inputPanel  = new JPanel( new GridLayout( 3, 2, 10, 20));

        // Nombre Medicamento
        nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);
        tipoMLabel = new JLabel("Tipo Medicamento:");
        tipoMedicamentoField = new JComboBox<>(new String[] {"Analgésico", "Analéptico", "Anestésico",
                "Antiácido", "Antidepresivo", "Antibióticos"});
        tipoMedicamentoField.setSelectedIndex(-1);
        cantidadLabel = new JLabel("Cantidad");
        cantidadField = new JTextField (20);
        // Se añaden al JPanel
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(tipoMLabel);
        inputPanel.add(tipoMedicamentoField);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadField);

        JPanel panelDeOpciones = new JPanel(new GridLayout(2,4,10,10));


        distribuidorLabel = new JLabel("Distribuidor:");
        cofarmaRadioButton = new JRadioButton("Cofarma");
        empsepharRadioButton = new JRadioButton("Empsephar");
        cemefarRadioButton = new JRadioButton("Cemefar");

        grupoBotones = new ButtonGroup();
        grupoBotones.add(cofarmaRadioButton);
        grupoBotones.add(empsepharRadioButton);
        grupoBotones.add(cemefarRadioButton);

        // Se añaden los botones al JPanel panelDeOpciones
        panelDeOpciones.add(distribuidorLabel);
        panelDeOpciones.add(cofarmaRadioButton);
        panelDeOpciones.add(empsepharRadioButton);
        panelDeOpciones.add(cemefarRadioButton);

        sucursalLabel = new JLabel("Sucursal:");
        principalCheckBox = new JCheckBox("Principal");
        secundariaCheckBox = new JCheckBox("Secundaria");

        panelDeOpciones.add(sucursalLabel);
        panelDeOpciones.add(principalCheckBox);
        panelDeOpciones.add(secundariaCheckBox);

        // Botones
        JPanel panelDeBotones = new JPanel(new GridLayout(1,2,10,10));
        borrarButton = new JButton("Borrar");
        borrarButton.setBackground(new Color(221, 55, 55));
        borrarButton.setForeground(Color.WHITE);
        borrarButton.setOpaque(true);
        borrarButton.setBorderPainted(false);
        borrarButton.setFocusPainted(false);

        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(new Color(4, 188, 35));
        confirmarButton.setForeground(Color.WHITE);
        confirmarButton.setOpaque(true);
        confirmarButton.setBorderPainted(false);
        confirmarButton.setFocusPainted(false);

        verPedidosButton = new JButton("Ver Tabla de Pedidos");
        verPedidosButton.setBackground(new Color(70, 130, 180));
        verPedidosButton.setForeground(Color.WHITE);
        verPedidosButton.setOpaque(true);
        verPedidosButton.setBorderPainted(false);
        verPedidosButton.setFocusPainted(false);

        panelDeBotones.add(borrarButton);
        panelDeBotones.add(confirmarButton);
        panelDeBotones.add(verPedidosButton);

        // Se añaden los paneles al contentPane
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(panelDeOpciones, BorderLayout.CENTER);
        contentPane.add(panelDeBotones, BorderLayout.SOUTH);
        setVisible(true);

    }

    // Métodos Getter
    public String getNombreField() {
        return nombreField.getText();
    }
    public String getTipoMedicamentoField() {
        try {
            return Objects.requireNonNull(tipoMedicamentoField.getSelectedItem()).toString();
        } catch (NullPointerException e) {
            return null;
        }
    }


    public Integer getCantidadField() {
        try {
            return Integer.parseInt(cantidadField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getDistribuidorSelection(){

        if(cofarmaRadioButton.isSelected()){
            return "Cofarma";
        }
        else if(empsepharRadioButton.isSelected()){
            return "Empsephar";
        }
        else if(cemefarRadioButton.isSelected()){
            return "Cemefar";
        }
        return null;
    }

    public List<String> getSucursales(){
        List<String> sucursales = new ArrayList<>();
        if (principalCheckBox.isSelected() && secundariaCheckBox.isSelected()) {
            sucursales.add("Principal");
            sucursales.add("Secundaria");
        } else if (principalCheckBox.isSelected()) {
            sucursales.add("Principal");

        }else if (secundariaCheckBox.isSelected()) {
            sucursales.add("Secundaria");

        } else {
            return null;
        }
        return sucursales;
    }



    public void setControlador(ActionListener controladorPedidos) {
        confirmarButton.addActionListener(controladorPedidos);
        borrarButton.addActionListener(controladorPedidos);
        verPedidosButton.addActionListener(controladorPedidos);
    }

    public void borrarCampos() {
        nombreField.setText("");
        tipoMedicamentoField.setSelectedIndex(-1);
        cantidadField.setText("");
        grupoBotones.clearSelection();
        principalCheckBox.setSelected(false);
        secundariaCheckBox.setSelected(false);
    }

}

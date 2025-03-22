import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ResumenPedido extends JFrame {




    private JLabel tipoMedicamentoLabel;
    private JLabel direccionLabel;
    JButton cancelarButton;
    JButton confirmarButton;


    public ResumenPedido() {
        UIComponents();
    }
    private void UIComponents(){

        setSize(700,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel resumenPanel = new JPanel();
        resumenPanel.setLayout(new BorderLayout());
        resumenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(resumenPanel);

        JPanel mostrarInfo = new JPanel( new GridLayout(3,1,10,20));

        // Componentes
        tipoMedicamentoLabel = new JLabel();
        direccionLabel= new JLabel();
        mostrarInfo.add(tipoMedicamentoLabel);
        mostrarInfo.add(direccionLabel);


        JPanel panelBotones = new JPanel(new GridLayout(1,3,10,20));
        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBackground(new Color(221, 55, 55));
        cancelarButton.setForeground(Color.WHITE);
        cancelarButton.setOpaque(true);
        cancelarButton.setBorderPainted(false);
        cancelarButton.setFocusPainted(false);


        confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(new Color(4, 188, 35));
        confirmarButton.setForeground(Color.WHITE);
        confirmarButton.setOpaque(true);
        confirmarButton.setBorderPainted(false);
        confirmarButton.setFocusPainted(false);




        panelBotones.add(cancelarButton);
        panelBotones.add(confirmarButton);



        resumenPanel.add(mostrarInfo, BorderLayout.NORTH);
        resumenPanel.add(panelBotones, BorderLayout.SOUTH);

    }

    public void setControlador(ActionListener controlador){
        cancelarButton.addActionListener(controlador);
        confirmarButton.addActionListener(controlador);

    }

    public void mostrarResumen(ModeloPedidos.Medicamento medicamento){

        setTitle("Pedido al distribuidor " + medicamento.getDistribuidor()); // distribuidor
        tipoMedicamentoLabel.setText( medicamento.getCantidad() + " unidades del " + medicamento.getTipo() +" "+ medicamento.getNombre());
        List<String> sucursales = medicamento.getSucursales();

        if (sucursales.contains("Principal") && sucursales.contains("Secundaria")) {
            direccionLabel.setText("Para las farmacias situadas en Calle de la Rosa n.28, Calle Alcazabilla n.3, " +
                    "Avenida 3 y Calle 18 y 19 en barrio La Playa");
        } else if (sucursales.contains("Principal")) {
            direccionLabel.setText("Para la farmacia situada en Calle de la Rosa n.28 y para la situada en Calle Alcazabilla n.3");
        } else if (sucursales.contains("Secundaria")) {
            direccionLabel.setText("Para la farmacia situada en Avenida 3 y para la situada en Calle 18 y 19 en barrio La Playa");
        }


    }
}

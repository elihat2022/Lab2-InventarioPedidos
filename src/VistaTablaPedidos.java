import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaTablaPedidos extends JFrame {
    private JFrame ventanaTablaPedidos;
    private DefaultTableModel modeloTabla;
    private JTable tablaPedidos;
    private JButton cerrarButton;


    public VistaTablaPedidos( List<ModeloPedidos.Medicamento> medicamentos) {
        initUI(medicamentos);
    }

    private void initUI(List<ModeloPedidos.Medicamento> medicamentos) {
        setTitle("Tabla de Pedidos");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        String[] nombresColumnas = {"Nombre", "Tipo", "Cantidad", "Distribuidor", "Sucursales"};
        modeloTabla = new DefaultTableModel(nombresColumnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que la tabla no sea editable
            }
        };


        for (ModeloPedidos.Medicamento medicamento : medicamentos) {
            Object[] fila = new Object[5];
            fila[0] = medicamento.getNombre();
            fila[1] = medicamento.getTipo();
            fila[2] = medicamento.getCantidad();
            fila[3] = medicamento.getDistribuidor();
            fila[4] = formatSucursales(medicamento.getSucursales());
            modeloTabla.addRow(fila);
        }
        // Crear la tabla con el modelo
        tablaPedidos = new JTable(modeloTabla);
        tablaPedidos.setRowHeight(25);
        tablaPedidos.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaPedidos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.setFillsViewportHeight(true);

        // Ajustar ancho de columnas
        tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(80);
        tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaPedidos.getColumnModel().getColumn(4).setPreferredWidth(250);

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Título
        JLabel titleLabel = new JLabel("Lista de Medicamentos Pedidos", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cerrarButton = new JButton("Cerrar");
        cerrarButton.setBackground(new Color(70, 130, 180));
        cerrarButton.setForeground(Color.WHITE);
        cerrarButton.setOpaque(true);
        cerrarButton.setBorderPainted(false);
        cerrarButton.setFocusPainted(false);
        buttonPanel.add(cerrarButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);



    }
    private String formatSucursales(List<String> sucursales) {
        return String.join(" y ", sucursales);
    }
    // Método para agregar un medicamento a la tabla
    public void agregarMedicamento(ModeloPedidos.Medicamento medicamento) {
        Object[] fila = new Object[5];
        fila[0] = medicamento.getNombre();
        fila[1] = medicamento.getTipo();
        fila[2] = medicamento.getCantidad();
        fila[3] = medicamento.getDistribuidor();
        fila[4] = formatSucursales(medicamento.getSucursales());
        modeloTabla.addRow(fila);
    }
    public void setControlador(ActionListener controlador) {
        cerrarButton.addActionListener(controlador);
    }

}



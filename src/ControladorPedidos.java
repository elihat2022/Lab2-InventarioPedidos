import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPedidos implements ActionListener {
    private FormularioPedido ventanaPedidos;
    private ModeloPedidos modeloPedidos;
    public ControladorPedidos(FormularioPedido ventanaPedidos, ModeloPedidos modeloPedidos) {
        this.ventanaPedidos = ventanaPedidos;
        this.modeloPedidos = modeloPedidos;
        this.ventanaPedidos.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreMedicamento = ventanaPedidos.getNombreField();
        String tipoMedicamento = ventanaPedidos.getTipoMedicamentoField();
        Integer cantidadMedicamento = ventanaPedidos.getCantidadField();
        String distribuidor = ventanaPedidos.getDistribuidorSelection();
        List<String> sucursales = ventanaPedidos.getSucursales();

        if(!modeloPedidos.nombreDeMedicamentoValido(nombreMedicamento)){
            JOptionPane.showMessageDialog(ventanaPedidos, "Debe ingresar un nombre de medicamento válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(tipoMedicamento == null || tipoMedicamento.isEmpty()){
            JOptionPane.showMessageDialog(ventanaPedidos, "Debe seleccionar un tipo de medicamento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(cantidadMedicamento == null || !modeloPedidos.cantidadDeMedicamentoValida(cantidadMedicamento)){
            JOptionPane.showMessageDialog(ventanaPedidos, "Debe ingresar una cantidad válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(distribuidor == null){
            JOptionPane.showMessageDialog(ventanaPedidos, "Debe seleccionar un distribuidor.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(sucursales == null){
            JOptionPane.showMessageDialog(ventanaPedidos, "Debe seleccionar al menos una sucursal.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }


        ModeloPedidos.Medicamento medicamento = new ModeloPedidos.Medicamento(nombreMedicamento,
                tipoMedicamento, cantidadMedicamento, distribuidor, sucursales);
        modeloPedidos.agregarMedicamento(medicamento);
        modeloPedidos.imprimirListaMedicamentos();

    }
}

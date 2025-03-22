import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPedidos implements ActionListener {
    private final FormularioPedido ventanaPedidos;
    private final ModeloPedidos modeloPedidos;
    private ResumenPedido ventanaResumen;
    private VistaTablaPedidos vistaTablaPedidos;

    public ControladorPedidos(FormularioPedido ventanaPedidos, ModeloPedidos modeloPedidos, ResumenPedido ventanaResumen) {
        this.ventanaPedidos = ventanaPedidos;
        this.modeloPedidos = modeloPedidos;
        this.ventanaPedidos.setControlador(this);
        this.ventanaResumen = ventanaResumen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombreMedicamento = ventanaPedidos.getNombreField();
        String tipoMedicamento = ventanaPedidos.getTipoMedicamentoField();
        Integer cantidadMedicamento = ventanaPedidos.getCantidadField();
        String distribuidor = ventanaPedidos.getDistribuidorSelection();
        List<String> sucursales = ventanaPedidos.getSucursales();

        if(e.getSource() == ventanaPedidos.confirmarButton) {

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

            ventanaResumen = new ResumenPedido();
            ventanaResumen.mostrarResumen(medicamento);
            ventanaResumen.setControlador(this);
            ventanaResumen.setVisible(true);


        } else if (e.getSource() == ventanaPedidos.borrarButton) {
            ventanaPedidos.borrarCampos();

        } else if (e.getSource() == ventanaResumen.confirmarButton){

            ModeloPedidos.Medicamento medicamento = new ModeloPedidos.Medicamento(nombreMedicamento,
                    tipoMedicamento, cantidadMedicamento, distribuidor, sucursales);
            modeloPedidos.agregarMedicamento(medicamento);
            modeloPedidos.imprimirListaMedicamentos();
            System.out.println("Pedido confirmado");
            JOptionPane.showMessageDialog(ventanaResumen, "Pedido Confirmado", "Información", JOptionPane.INFORMATION_MESSAGE);
            ventanaResumen.dispose();
            ventanaPedidos.borrarCampos();

        }else if (e.getSource() == ventanaResumen.cancelarButton) {
            ventanaResumen.dispose();
        }// Add this to your existing actionPerformed method
        else if (e.getSource() == ventanaPedidos.verPedidosButton) {
            vistaTablaPedidos = new VistaTablaPedidos(modeloPedidos.getListaMedicamentos());
            vistaTablaPedidos.setControlador(this);
            vistaTablaPedidos.setVisible(true);
        } else if (e.getSource() instanceof JButton && ((JButton) e.getSource()).getText().equals("Cerrar")) {
            ((JFrame) ((JButton) e.getSource()).getTopLevelAncestor()).dispose();
    }








    }
}

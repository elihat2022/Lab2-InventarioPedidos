//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Main
        ModeloPedidos modeloPedidos = new ModeloPedidos();
        FormularioPedido ventanaPedidos = new FormularioPedido();
        ResumenPedido ventanaResumen = new ResumenPedido();
        ControladorPedidos controladorPedidos = new ControladorPedidos(ventanaPedidos, modeloPedidos, ventanaResumen);

    }
}
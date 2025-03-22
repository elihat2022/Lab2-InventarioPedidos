
import java.util.List;
import java.util.ArrayList;

public class ModeloPedidos {

    public static class Medicamento{
        private String nombre;
        private String tipo;
        private Integer cantidad;
        private String distribuidor;
        private List<String> sucursales;


        public Medicamento(String nombre, String tipo, Integer cantidad, String distribuidor,
                           List<String> sucursales) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.cantidad = cantidad;
            this.distribuidor = distribuidor;
            this.sucursales = sucursales;

        }
        // Getters y Setters
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public void setCantidad(Integer cantidad) {
            this.cantidad = cantidad;
        }
        public String getDistribuidor() {
            return distribuidor;
        }
        public void setDistribuidor(String distribuidor) {
            this.distribuidor = distribuidor;
        }

        public List<String> getSucursales() {
            return sucursales;
        }

        public void setSucursales(List<String> sucursales) {
            this.sucursales = sucursales;
        }
    }



    private final List<Medicamento> listaMedicamentos;

    // Constructor de la clase ModeloPedidos
    public ModeloPedidos() {
        listaMedicamentos = new ArrayList<>();
    }

    // Métodos de la clase ModeloPedidos
    public boolean nombreDeMedicamentoValido(String nombre){
        return nombre != null && !nombre.isEmpty() && nombre.matches("[a-zA-Z0-9]+");
    }

    public String tipoDeMedicamentoValido(String tipo){
        return tipo;
    }
    public boolean cantidadDeMedicamentoValida(Integer cantidad){
        return cantidad > 0 && cantidad != null ;
    }

    public void agregarMedicamento(Medicamento medicamento){
        listaMedicamentos.add(medicamento);

    }

    public List<Medicamento> getListaMedicamentos() {

        return listaMedicamentos;
    }

    public void imprimirListaMedicamentos() {
        for (Medicamento medicamento : listaMedicamentos) {
            System.out.println("Nombre: " + medicamento.getNombre() +
                    ", Tipo: " + medicamento.getTipo() +
                    ", Cantidad: " + medicamento.getCantidad() +
                    ", Distribuidor: " + medicamento.getDistribuidor() +
                    ", Sucursales: " + medicamento.getSucursales());
        }
    }


}

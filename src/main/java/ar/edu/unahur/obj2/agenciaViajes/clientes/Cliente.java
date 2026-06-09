package ar.edu.unahur.obj2.agenciaViajes.clientes;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.agenciaViajes.criterios.ICriterio;
import ar.edu.unahur.obj2.agenciaViajes.criterios.ViajeroFlexible;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class Cliente {
    private final Integer dni;
    private ICriterio criterio = new ViajeroFlexible();
    private final List<IPaquete> paquetesRecibidos = new ArrayList<>();

   public Cliente(Integer dni) {
        this.dni = dni;
    }

    public Cliente(Integer dni, ICriterio criterio) {
        this.dni = dni;
        this.criterio = criterio;
    }

    public Integer getDni() {
        return dni;
    }

    public ICriterio getCriterio() {
        return criterio;
    }

    public void setCriterio(ICriterio criterio) {
        this.criterio = criterio;
    }

public List<String> informarPaquetes() {
    return paquetesRecibidos.stream()
        .map(p -> p.getNombre() + " - Precio total: $" + p.precioTotal())
        .toList();
}
 
    public Boolean leInteresa(IPaquete paquete){
        return Boolean.valueOf(criterio.leInteresa(paquete));
    }

    public void recibirPaquete(IPaquete paquete){
        paquetesRecibidos.add(paquete);
    }
    
    

}

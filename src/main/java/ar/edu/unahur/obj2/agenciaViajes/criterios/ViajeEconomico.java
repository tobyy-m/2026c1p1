package ar.edu.unahur.obj2.agenciaViajes.criterios;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class ViajeEconomico implements ICriterio{
    private Integer valorMaximo;

    public ViajeEconomico(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    @Override
    public Boolean leInteresa(IPaquete paquete) {
        return Boolean.valueOf(paquete.precioTotal() < valorMaximo);
    }

}

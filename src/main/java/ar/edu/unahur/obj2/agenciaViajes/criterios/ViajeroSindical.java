package ar.edu.unahur.obj2.agenciaViajes.criterios;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class ViajeroSindical implements ICriterio {
    private Integer valorMaximo;

    
    public ViajeroSindical(Integer valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    @Override
    public Boolean leInteresa(IPaquete paquete) {
        ViajeEconomico criterioEconomico = new ViajeEconomico(valorMaximo);
        return Boolean.valueOf(
            paquete.tieneDescuentoSindical() || criterioEconomico.leInteresa(paquete));
    }
    

}

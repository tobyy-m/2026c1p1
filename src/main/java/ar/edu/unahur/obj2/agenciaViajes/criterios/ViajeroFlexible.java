package ar.edu.unahur.obj2.agenciaViajes.criterios;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class ViajeroFlexible implements ICriterio{

    @Override
    public Boolean leInteresa(IPaquete paquete) {
        return Boolean.TRUE;
    }

}

package ar.edu.unahur.obj2.agenciaViajes.criterios;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public interface ICriterio {
    Boolean leInteresa(IPaquete paquete);
}

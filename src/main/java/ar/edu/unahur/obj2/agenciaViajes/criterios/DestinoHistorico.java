package ar.edu.unahur.obj2.agenciaViajes.criterios;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class DestinoHistorico implements ICriterio{
    
    private static DestinoHistorico instance = new DestinoHistorico();

    private DestinoHistorico() {}

    public static DestinoHistorico getInstance() {return instance;}

    @Override
    public Boolean leInteresa(IPaquete paquete) {
        return Boolean.valueOf(paquete.esHistorico());
    }

}

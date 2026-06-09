package ar.edu.unahur.obj2.agenciaViajes.estrategiascomerciales;

import ar.edu.unahur.obj2.agenciaViajes.decoradores.Guia;
import ar.edu.unahur.obj2.agenciaViajes.decoradores.TrasladoVIP;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class GuiaYTrasladoVIP implements IEstrategiaComercial{

    @Override
    public IPaquete agregarExtra(IPaquete paquete) {
        return new Guia(new TrasladoVIP(paquete));
    }

}

package ar.edu.unahur.obj2.agenciaViajes.estrategiascomerciales;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class ComoViene implements IEstrategiaComercial{

    @Override
    public IPaquete agregarExtra(IPaquete paquete) {
        return paquete;
    }
    
}

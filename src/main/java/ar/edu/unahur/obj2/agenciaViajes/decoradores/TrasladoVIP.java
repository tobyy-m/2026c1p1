package ar.edu.unahur.obj2.agenciaViajes.decoradores;

import ar.edu.unahur.obj2.agenciaViajes.excepciones.ExtraNoDisponibleException;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class TrasladoVIP extends ExtraDecorator {

    public TrasladoVIP(IPaquete paquete) {
        super(paquete);
        if(!Boolean.valueOf(paquete.esInternacional())){
            throw new ExtraNoDisponibleException(
                "El traslado VIP solo está disponible para paquetes internacionales.");
        }
    }

    @Override
    protected String getNombreExtra() {
        return "Transporte";
    }

    @Override
    protected Double getValorExtra() {
        return 250.0;
    }
}

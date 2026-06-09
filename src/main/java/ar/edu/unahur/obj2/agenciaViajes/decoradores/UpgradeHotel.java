package ar.edu.unahur.obj2.agenciaViajes.decoradores;

import ar.edu.unahur.obj2.agenciaViajes.excepciones.ExtraNoDisponibleException;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class UpgradeHotel extends ExtraDecorator {

    public UpgradeHotel(IPaquete paquete) {
        super(paquete);
        if(!Boolean.valueOf(paquete.tieneAlojamiento())){
            throw new ExtraNoDisponibleException("El Upgrade de Hotel no esta disponible para paquetes sin alojamiento");
        }
    }

    @Override
    protected String getNombreExtra() {
        return "upgrade de hotel";
    }

    @Override
    protected Double getValorExtra() {
        return 320.0;
    }
    

}

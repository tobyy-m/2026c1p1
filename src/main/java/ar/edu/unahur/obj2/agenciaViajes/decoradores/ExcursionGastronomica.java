package ar.edu.unahur.obj2.agenciaViajes.decoradores;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class ExcursionGastronomica extends ExtraDecorator {

    public ExcursionGastronomica(IPaquete paquete) {
        super(paquete);
    }

    @Override
    protected String getNombreExtra() {
        return "Excursiones Gastronómicas";
    }

    @Override
    protected Double getValorExtra() {
        return 180.0;
    }

}

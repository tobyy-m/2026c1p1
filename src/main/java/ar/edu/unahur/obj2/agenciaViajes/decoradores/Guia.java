package ar.edu.unahur.obj2.agenciaViajes.decoradores;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class Guia extends ExtraDecorator {

    public Guia(IPaquete paquete) {
        super(paquete);
    }

    @Override
    protected String getNombreExtra() {
        return "Guia Acompañante";
    }

    @Override
    protected Double getValorExtra() {
        return 200.0;
    }

}

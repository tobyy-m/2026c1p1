package ar.edu.unahur.obj2.agenciaViajes.decoradores;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public abstract class ExtraDecorator implements IPaquete {
    private final IPaquete paquete;

    public ExtraDecorator(IPaquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public Boolean esHistorico() {
        return paquete.esHistorico();
    }

    @Override
    public Boolean esInternacional(){
        return paquete.esInternacional();
    }

    @Override
    public String getAgenciaOrganizadora() {
        return paquete.getAgenciaOrganizadora();
    }

    @Override
    public Integer getAniosHistoricos() {
        
        return paquete.getAniosHistoricos();
    }

    @Override
    public Double getPrecioBase() {
        
        return paquete.getPrecioBase();
    }

    @Override
    public Boolean tieneDescuentoSindical() {
        
        return paquete.tieneDescuentoSindical();
    }

    @Override
    public String getNombre() {
        
        return paquete.getNombre() + " + " + this.getNombreExtra();
    }

    
    @Override
    public Double precioTotal() {
        
        return paquete.precioTotal() + this.getValorExtra();
    }

    @Override
    public Boolean tieneAlojamiento(){
        return paquete.tieneAlojamiento();
    }

    protected abstract Double getValorExtra();

    protected abstract String getNombreExtra();
}

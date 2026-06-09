package ar.edu.unahur.obj2.agenciaViajes.paquetes;

import ar.edu.unahur.obj2.agenciaViajes.excepciones.PaqueteInvalidoException;

public class Paquete implements IPaquete {
    private String nombre;
    private String agenciaOrganizadora;
    private Double precioBase;
    private Integer aniosHistoricos;
    private Boolean descuentoSindical = Boolean.FALSE;
    private Boolean tieneAlojamiento = Boolean.FALSE;
    private Boolean esInternacional = Boolean.FALSE;
    
    public Paquete(String nombre, String agenciaOrganizadora, Double precioBase,
            Integer aniosHistoricos) {
        if(nombre == null || nombre.isBlank() ) { throw new PaqueteInvalidoException("El paquete no tiene un nombre establecido");}
        if(precioBase == null || precioBase < 0) {throw new PaqueteInvalidoException("El Precio base del paquete no puede ser negativo"); }
        if(aniosHistoricos == null || aniosHistoricos < 0) {throw new PaqueteInvalidoException("Los Años Hitoricos no pueden ser negativos");}        
        this.nombre = nombre;
        this.agenciaOrganizadora = agenciaOrganizadora;
        this.precioBase = precioBase;
        this.aniosHistoricos = aniosHistoricos;
    }


    public Paquete(String nombre, String agenciaOrganizadora, Double precioBase,
            Integer aniosHistoricos, Boolean descuentoSindical) {
        if(nombre == null || nombre.isBlank() ) { throw new PaqueteInvalidoException("El paquete no tiene un nombre establecido");}
        if(precioBase == null || precioBase < 0) {throw new PaqueteInvalidoException("El Precio base del paquete no puede ser negativo"); }
        if(aniosHistoricos == null || aniosHistoricos < 0) {throw new PaqueteInvalidoException("Los Años Hitoricos no pueden ser negativos");}        
        this.nombre = nombre;
        this.agenciaOrganizadora = agenciaOrganizadora;
        this.precioBase = precioBase;
        this.aniosHistoricos = aniosHistoricos;
        this.descuentoSindical = descuentoSindical;
    }

    public Paquete(String nombre, String agenciaOrganizadora, Double precioBase,
            Integer aniosHistoricos, Boolean descuentoSindical, Boolean tieneAlojamiento) {
        if(nombre == null || nombre.isBlank() ) { throw new PaqueteInvalidoException("El paquete no tiene un nombre establecido");}
        if(precioBase == null || precioBase < 0) {throw new PaqueteInvalidoException("El Precio base del paquete no puede ser negativo"); }
        if(aniosHistoricos == null || aniosHistoricos < 0) {throw new PaqueteInvalidoException("Los Años Hitoricos no pueden ser negativos");}        
        this.nombre = nombre;
        this.agenciaOrganizadora = agenciaOrganizadora;
        this.precioBase = precioBase;
        this.aniosHistoricos = aniosHistoricos;
        this.descuentoSindical = descuentoSindical;
        this.tieneAlojamiento = tieneAlojamiento;
    }

    

    public Paquete(String nombre, String agenciaOrganizadora, Double precioBase,
            Integer aniosHistoricos, Boolean descuentoSindical, Boolean tieneAlojamiento,
            Boolean esInternacional) {
        if(nombre == null || nombre.isBlank() ) { throw new PaqueteInvalidoException("El paquete no tiene un nombre establecido");}
        if(precioBase == null || precioBase < 0) {throw new PaqueteInvalidoException("El Precio base del paquete no puede ser negativo"); }
        if(aniosHistoricos == null || aniosHistoricos < 0) {throw new PaqueteInvalidoException("Los Años Hitoricos no pueden ser negativos");}        
        this.nombre = nombre;
        this.agenciaOrganizadora = agenciaOrganizadora;
        this.precioBase = precioBase;
        this.aniosHistoricos = aniosHistoricos;
        this.descuentoSindical = descuentoSindical;
        this.tieneAlojamiento = tieneAlojamiento;
        this.esInternacional = esInternacional;
    }


    @Override
    public String getAgenciaOrganizadora() {
        return agenciaOrganizadora;
    }

    @Override
    public Integer getAniosHistoricos() {
        return aniosHistoricos;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Double getPrecioBase() {
        return precioBase;
    }

    @Override
    public Boolean esHistorico() {
        return aniosHistoricos >= 300;
    }

    @Override
    public Boolean tieneDescuentoSindical(){
        return Boolean.valueOf(descuentoSindical);
    }

    @Override
    public Boolean tieneAlojamiento() {
        return Boolean.valueOf(tieneAlojamiento);
    }
    
    
    @Override
    public Boolean esInternacional() {
        return esInternacional;
    }


    @Override
    public Double precioTotal() {
        return precioBase;
    }
  
}

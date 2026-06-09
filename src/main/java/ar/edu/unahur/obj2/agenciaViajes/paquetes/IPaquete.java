package ar.edu.unahur.obj2.agenciaViajes.paquetes;

public interface IPaquete {
    String getNombre();
    String getAgenciaOrganizadora();
    Double getPrecioBase();
    Double precioTotal();
    Integer getAniosHistoricos();
    Boolean esHistorico();
    Boolean tieneDescuentoSindical();
    Boolean tieneAlojamiento();
    Boolean esInternacional();
}

package ar.edu.unahur.obj2.agenciaViajes.criterios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class Premium implements ICriterio {
    private List<String> agenciasPreferidas = new ArrayList<>();

    public Premium(List<String> agenciasPreferidas) {
        this.agenciasPreferidas = agenciasPreferidas;
    }

    @Override
    public Boolean leInteresa(IPaquete paquete) {
        return Boolean.valueOf(
            agenciasPreferidas.contains(
                paquete.getAgenciaOrganizadora()));
    }
}

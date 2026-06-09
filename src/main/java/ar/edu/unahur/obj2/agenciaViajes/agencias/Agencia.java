package ar.edu.unahur.obj2.agenciaViajes.agencias;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.agenciaViajes.clientes.Cliente;
import ar.edu.unahur.obj2.agenciaViajes.estrategiascomerciales.ComoViene;
import ar.edu.unahur.obj2.agenciaViajes.estrategiascomerciales.IEstrategiaComercial;
import ar.edu.unahur.obj2.agenciaViajes.excepciones.ClienteSinCriterioException;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.IPaquete;

public class Agencia {
    private static Agencia instance = new Agencia();
    private static List<IPaquete> paquetes = new ArrayList<>();
    private IEstrategiaComercial estrategiaComercial = new ComoViene();

    private Agencia() {}

    public static Agencia getInstance() {return instance;}

    public void agregarPaquete(IPaquete paquete){
        paquetes.add(paquete);
    }

    public List<IPaquete> getPaquetes() {
        return paquetes;
    }
    
    public void setEstrategia(IEstrategiaComercial estrategia){
        this.estrategiaComercial = estrategia;
    }
    public void atenderCliente(Cliente cliente){
        if(cliente.getCriterio() == null){
            throw new ClienteSinCriterioException("El cliente no tiene un criterio configurado"); 
        }
        IPaquete paqueteAEntregar = paquetes.stream()
        .filter(p -> cliente.leInteresa(p)).findFirst().orElseThrow();
        paqueteAEntregar = estrategiaComercial.agregarExtra(paqueteAEntregar);
        cliente.recibirPaquete(paqueteAEntregar);
    }

    
}

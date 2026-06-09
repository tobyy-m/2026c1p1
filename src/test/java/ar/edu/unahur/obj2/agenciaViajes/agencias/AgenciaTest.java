package ar.edu.unahur.obj2.agenciaViajes.agencias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.agenciaViajes.clientes.Cliente;
import ar.edu.unahur.obj2.agenciaViajes.criterios.*;
import ar.edu.unahur.obj2.agenciaViajes.decoradores.*;
import ar.edu.unahur.obj2.agenciaViajes.estrategiascomerciales.*;
import ar.edu.unahur.obj2.agenciaViajes.excepciones.*;
import ar.edu.unahur.obj2.agenciaViajes.paquetes.*;

public class AgenciaTest {
    @BeforeEach
    void setUp() {
        Agencia agencia = Agencia.getInstance();
        agencia.setEstrategia(new ComoViene());
        agencia.getPaquetes().clear();
    }

    @Test
    void dadoUnPaqueteValido_cuandoSeCrea_EntoncesExponeSusDatosYPrecioBase() {
        Paquete paquete = new Paquete("Roma", "JetSmart", 1200.0, 400);
        assertEquals("Roma", paquete.getNombre());
        assertEquals("JetSmart", paquete.getAgenciaOrganizadora());
        assertEquals(1200.0, paquete.getPrecioBase());
        assertEquals(400, paquete.getAniosHistoricos());
        assertTrue(paquete.esHistorico());
        assertFalse(paquete.tieneDescuentoSindical());
        assertFalse(paquete.tieneAlojamiento());
        assertFalse(paquete.esInternacional());
        assertEquals(1200.0, paquete.precioTotal());
    }

    @Test
    void dadoNombreVacio_cuandoSeCreaPaquete_EntoncesLanzaPaqueteInvalidoException() {
        assertThrows(PaqueteInvalidoException.class, () -> new Paquete("", "JetSmart", 1000.0, 100));
    }

    @Test
    void dadoPrecioNegativo_cuandoSeCreaPaquete_EntoncesLanzaPaqueteInvalidoException() {
        assertThrows(PaqueteInvalidoException.class, () -> new Paquete("Roma", "JetSmart", -1.0, 100));
    }

    @Test
    void dadoAniosHistoricosNegativos_cuandoSeCreaPaquete_EntoncesLanzaPaqueteInvalidoException() {
        assertThrows(PaqueteInvalidoException.class, () -> new Paquete("Roma", "JetSmart", 1000.0, -1));
    }

    @Test
    void dadoPrecioNulo_cuandoSeCreaPaquete_EntoncesLanzaPaqueteInvalidoException() {
        assertThrows(PaqueteInvalidoException.class, () -> new Paquete("Roma", "JetSmart", null, 100));
    }

    @Test
    void dadoAniosHistoricosNulos_cuandoSeCreaPaquete_EntoncesLanzaPaqueteInvalidoException() {
        assertThrows(PaqueteInvalidoException.class, () -> new Paquete("Roma", "JetSmart", 1000.0, null));
    }

    @Test
    void dadoClienteConCriterioFlexible_cuandoSeModifica_EntoncesActualizaCriterio() {
        Cliente cliente = new Cliente(1111111);
        cliente.setCriterio(new ViajeEconomico(1500));
        assertTrue(cliente.getCriterio() instanceof ViajeEconomico);
    }

    @Test
    void dadoClienteFlexibleYPaqueteDisponible_cuandoAtenderCliente_EntoncesRecibePaquete() {
        Agencia agencia = Agencia.getInstance();
        agencia.agregarPaquete(new Paquete("Buenos Aires", "JetSmart", 1000.0, 100));
        Cliente cliente = new Cliente(1231231, new ViajeroFlexible());
        agencia.atenderCliente(cliente);
        assertEquals(List.of("Buenos Aires - Precio total: $1000.0"), cliente.informarPaquetes());
    }

    @Test
    void dadoClienteSinCriterio_cuandoAtenderCliente_EntoncesLanzaClienteSinCriterioException() {
        Agencia agencia = Agencia.getInstance();
        agencia.agregarPaquete(new Paquete("Roma", "JetSmart", 1000.0, 400));
        Cliente cliente = new Cliente(33333);
        cliente.setCriterio(null);
        assertThrows(ClienteSinCriterioException.class, () -> agencia.atenderCliente(cliente));
    }

    @Test
    void dadoUnClienteConCriterioViajeEconomico_cuandoElPaqueteCuestaMenosQueElMaximoEstablecido_EntoncesInteresa() {
        Cliente cliente = new Cliente(32131, new ViajeEconomico(1000));
        IPaquete paquete = new Paquete("Cordoba", "Ruta Atlántica", 900.0, 50);
        assertTrue(cliente.leInteresa(paquete));
    }

    @Test
    void dadoUnClienteConCriterioViajeEconomico_cuandoElPaqueteCuestaMasQueElMaximoEstablecido_EntoncesNoInteresa() {
        Cliente cliente = new Cliente(414141, new ViajeEconomico(1000));
        IPaquete paquete = new Paquete("Cordoba", "Ruta Atlántica", 1200.0, 50);
        assertFalse(cliente.leInteresa(paquete));
    }

    @Test
    void dadoUnClienteConCriterioViajeroSindical_cuandoElPaqueteTieneDescuentoSindical_EntoncesInteresa() {
        Cliente cliente = new Cliente(1315135, new ViajeroSindical(1500));
        IPaquete paquete = new Paquete("Bariloche", "FlyBondi", 1200.0, 100, true);
        assertTrue(cliente.leInteresa(paquete));
    }

    @Test
    void dadoUnClienteConCriterioViajeroSindical_cuandoElPaqueteEsEconomicoYSinDescuento_EntoncesInteresa() {
        Cliente cliente = new Cliente(1412561, new ViajeroSindical(700));
        IPaquete paquete = new Paquete("Salta", "FlyBondi", 600.0, 100, false);
        assertTrue(cliente.leInteresa(paquete));
    }

    @Test
    void dadoUnClienteConCriterioDestinoHistorico_cuandoElPaqueteTieneAlMenos300Anios_EntoncesInteresa() {
        Cliente cliente = new Cliente(13561, DestinoHistorico.getInstance());
        IPaquete paquete = new Paquete("Cusco", "Ruta Atlántica", 1500.0, 300);
        assertTrue(cliente.leInteresa(paquete));
    }

    @Test
    void dadoUnClienteConCriterioPremium_cuandoAgenciaEstaEnListaPreferida_EntoncesInteresa() {
        Cliente cliente = new Cliente(61342, new Premium(List.of("JetSmart", "FlyBondi", "Ruta Atlántica")));
        IPaquete paquete = new Paquete("París", "FlyBondi", 2000.0, 200);
        assertTrue(cliente.leInteresa(paquete));
    }

    @Test
    void dadoEstrategiaConGuiaYTraslado_cuandoAtenderCliente_EntoncesAgregaExtras() {
        Agencia agencia = Agencia.getInstance();
        agencia.setEstrategia(new GuiaYTrasladoVIP());
        agencia.agregarPaquete(new Paquete("Madrid", "JetSmart", 1000.0, 400, false, true, true));
        Cliente cliente = new Cliente(13414, new ViajeroFlexible());
        agencia.atenderCliente(cliente);
        assertEquals(List.of("Madrid + Transporte + Guia Acompañante - Precio total: $1450.0"), cliente.informarPaquetes());
    }

    @Test
    void dadoEstrategiaConGuiaUpgradeYTraslado_cuandoAtenderCliente_EntoncesAgregaTodosLosExtras() {
        Agencia agencia = Agencia.getInstance();
        agencia.setEstrategia(new GuiaConUpgradeDeHotelYTrasladoVIP());
        agencia.agregarPaquete(new Paquete("Lisboa", "JetSmart", 1000.0, 400, false, true, true));
        Cliente cliente = new Cliente(1234131, new ViajeroFlexible());
        agencia.atenderCliente(cliente);
        assertEquals(List.of("Lisboa + Transporte + upgrade de hotel + Guia Acompañante - Precio total: $1770.0"), cliente.informarPaquetes());
    }

    @Test
    void dadoTrasladoVipSobrePaqueteNoInternacional_cuandoSeAplica_EntoncesLanzaExtraNoDisponibleException() {
        IPaquete paquete = new Paquete("Mendoza", "JetSmart", 900.0, 50, false, true, false);
        assertThrows(ExtraNoDisponibleException.class, () -> new TrasladoVIP(paquete));
    }

    @Test
    void dadoUpgradeHotelSobrePaqueteSinAlojamiento_cuandoSeAplica_EntoncesLanzaExtraNoDisponibleException() {
        IPaquete paquete = new Paquete("Mendoza", "JetSmart", 900.0, 50, false, false, true);
        assertThrows(ExtraNoDisponibleException.class, () -> new UpgradeHotel(paquete));
    }

    @Test
    void dadoExcursionGastronomica_cuandoSeAplica_EntoncesSumaPrecioYModificaNombre() {
        IPaquete paquete = new Paquete("Peru", "Ruta Atlántica", 1000.0, 400, false, true, true);
        IPaquete decorado = new ExcursionGastronomica(paquete);
        assertEquals("Peru + Excursiones Gastronómicas", decorado.getNombre());
        assertEquals(1180.0, decorado.precioTotal());
    }

    @Test
    void dadoGuia_cuandoSeAplica_EntoncesSumaPrecioYModificaNombre() {
        IPaquete paquete = new Paquete("Peru", "Ruta Atlántica", 1000.0, 400, false, true, true);
        IPaquete decorado = new Guia(paquete);
        assertEquals("Peru + Guia Acompañante", decorado.getNombre());
        assertEquals(1200.0, decorado.precioTotal());
    }

    @Test
    void dadoPaqueteHistorico_cuandoEvaluarDestinoHistorico_EntoncesEsVerdadero() {
        Paquete paquete = new Paquete("Quito", "Ruta Atlántica", 1000.0, 350);
        assertTrue(paquete.esHistorico());
    }
}
//package br.com.infnet.transporte_service.service;
//
//import br.com.infnet.transporte_service.domain.Transporte;
//import br.com.infnet.transporte_service.repository.TransporteRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class TransporteServiceTests {
//
//    @InjectMocks
//    private TransporteService transporteService;
//
//    @Mock
//    private TransporteRepository transporteRepository;
//
//    private Transporte transporte;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        transporte = new Transporte();
//        transporte.setId(1L);
//        transporte.setVeiculo("Caminhão");
//    }
//
//    @Test
//    void testProcessarPedido_Sucesso() {
//        String pedidoEvent = "Pedido ID: 123, Entrega: Produto B, Endereço: Rua A";
//        transporteService.processarPedido(pedidoEvent);
//        verify(transporteRepository, times(1)).save(any(Transporte.class));
//    }
//
//    @Test
//    void testVerificarStatusTransporte() {
//        transporteService.adicionarTransporte(transporte);
//        String status = transporteService.verificarStatusTransporte();
//        assertEquals("Status das Entregas:\nVeículo: Caminhão\n  Nenhuma entrega registrada.\n", status);
//    }
//}

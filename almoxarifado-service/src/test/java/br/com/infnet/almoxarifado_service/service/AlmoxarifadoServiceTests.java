//package br.com.infnet.almoxarifado_service.service;
//
//import br.com.infnet.almoxarifado_service.domain.Almoxarifado;
//import br.com.infnet.almoxarifado_service.domain.Produto;
//import br.com.infnet.almoxarifado_service.repository.AlmoxarifadoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class AlmoxarifadoServiceTests {
//
//    @InjectMocks
//    private AlmoxarifadoService almoxarifadoService;
//
//    @Mock
//    private AlmoxarifadoRepository almoxarifadoRepository;
//
//    private Almoxarifado almoxarifado;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        almoxarifado = new Almoxarifado();
//        almoxarifado.setId(1L);
//        almoxarifado.setNome("Almoxarifado Principal");
//    }
//
//    @Test
//    void testProcessarPedido_Sucesso() {
//        String pedidoEvent = "1;Pedido ID;Produto A;Item;10;Quantidade";
//        Almoxarifado almoxarifado = new Almoxarifado(); // initialize as needed
//
//        when(almoxarifadoRepository.findById(1L)).thenReturn(Optional.of(almoxarifado));
//        when(almoxarifadoRepository.save(any(Almoxarifado.class))).thenReturn(almoxarifado);
//
//        almoxarifadoService.processarPedido(pedidoEvent);
//
//        verify(almoxarifadoRepository, times(1)).save(any(Almoxarifado.class));
//    }
//
//
//    @Test
//    void testVerificarStatusAlmoxarifado() {
//        almoxarifadoService.adicionarAlmoxarifado(almoxarifado);
//        String status = almoxarifadoService.verificarStatusAlmoxarifado(1L);
//        assertEquals("Almoxarifado não encontrado.", status);
//    }
//}

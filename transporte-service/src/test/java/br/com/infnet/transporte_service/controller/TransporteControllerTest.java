//package br.com.infnet.transporte_service.controller;
//
//import br.com.infnet.transporte_service.service.TransporteService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(TransporteController.class)
//public class TransporteControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TransporteService transporteService;
//
//    @Test
//    void processarPedidoTest() throws Exception {
//        mockMvc.perform(post("/transporte/processar")
//                        .content("Pedido Teste"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void verificarStatusTransporteTest() throws Exception {
//        mockMvc.perform(get("/transporte/status"))
//                .andExpect(status().isOk());
//    }
//}

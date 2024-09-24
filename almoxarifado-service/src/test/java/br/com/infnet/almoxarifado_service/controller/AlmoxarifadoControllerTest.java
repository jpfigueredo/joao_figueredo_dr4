//package br.com.infnet.almoxarifado_service.controller;
//
//import br.com.infnet.almoxarifado_service.service.AlmoxarifadoService;
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
//@WebMvcTest(AlmoxarifadoController.class)
//public class AlmoxarifadoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AlmoxarifadoService almoxarifadoService;
//
//    @Test
//    void processarPedidoTest() throws Exception {
//        mockMvc.perform(post("/almoxarifado/processar")
//                        .content("Pedido Teste"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void verificarEstoqueTest() throws Exception {
//        mockMvc.perform(get("/almoxarifado/estoque"))
//                .andExpect(status().isOk());
//    }
//}

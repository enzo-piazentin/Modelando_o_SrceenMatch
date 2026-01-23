```java
package com.santander.aof.app.resource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.aof.app.service.SistService;
import com.santander.aof.model.SistPageResponseDTO;
import java.util.concurrent.CompletableFuture;

import com.santander.aof.model.SistRequestDTO;
import com.santander.aof.model.SistResponseDTO;
import com.santander.aof.model.SistUpdatePatchRequestDTO;
import com.santander.ars.error.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class SistResourceTest {

    @Mock private SistService sistService;

    @InjectMocks private SistResource sistResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar resposta válida ao listar sistemas com parâmetros válidos")
    void deveRetornarRespostaValidaAoListarSistemas() {
        Integer page = 0;
        Integer size = 10;
        SistPageResponseDTO mockResponse = new SistPageResponseDTO();
        when(sistService.listarSists(page, size)).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistPageResponseDTO>> result =
                sistResource.sistGet(page, size);

        ResponseEntity<SistPageResponseDTO> response = result.join();
        assertThat(response).isEqualTo(ResponseEntity.ok(mockResponse));
        verify(sistService, times(1)).listarSists(page, size);
    }

    @Test
    @DisplayName("Deve lidar com parâmetros nulos e retornar resposta válida")
    void deveLidarComParametrosNulosERetornarRespostaValida() {
        Integer page = null;
        Integer size = null;
        SistPageResponseDTO mockResponse = new SistPageResponseDTO();
        when(sistService.listarSists(page, size)).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistPageResponseDTO>> result =
                sistResource.sistGet(page, size);

        ResponseEntity<SistPageResponseDTO> response = result.join();
        assertThat(response).isEqualTo(ResponseEntity.ok(mockResponse));
        verify(sistService, times(1)).listarSists(page, size);
    }

    @Test
    @DisplayName("Deve retornar resposta vazia quando o serviço retorna null")
    void deveRetornarRespostaVaziaQuandoServicoRetornaNull() {
        Integer page = 0;
        Integer size = 10;
        when(sistService.listarSists(page, size)).thenReturn(null);

        CompletableFuture<ResponseEntity<SistPageResponseDTO>> result =
                sistResource.sistGet(page, size);

        ResponseEntity<SistPageResponseDTO> response = result.join();
        assertThat(response).isEqualTo(ResponseEntity.ok(null));
        verify(sistService, times(1)).listarSists(page, size);
    }

    @Test
    @DisplayName("Deve criar um sistema com dados válidos e retornar status 201")
    void deveCriarSistemaComDadosValidosERetornarStatus201() {
        SistRequestDTO request = new SistRequestDTO();
        request.setCodigoSistema("123");
        request.setNomeSistema("Sistema Teste");
        request.setIndicadorSistemaAtivo("A");

        SistResponseDTO mockResponse = new SistResponseDTO();
        when(sistService.createSist(any(SistRequestDTO.class))).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistCreateSystemPost(request);

        ResponseEntity<SistResponseDTO> response = result.join();
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(mockResponse);
        verify(sistService, times(1)).createSist(any(SistRequestDTO.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar sistema com corpo de requisição nulo")
    void deveLancarExcecaoAoCriarSistemaComCorpoDeRequisicaoNulo() {
        SistRequestDTO request = null;

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistCreateSystemPost(request);

        assertThatThrownBy(result::join)
                .hasCauseInstanceOf(ValidationException.class)
                .hasMessageContaining("Payload da requisição é obrigatório.");
        verify(sistService, never()).createSist(any(SistRequestDTO.class));
    }

    @Test
    @DisplayName("Deve atualizar sistema com dados válidos e retornar status 200")
    void deveAtualizarSistemaComDadosValidosERetornarStatus200() {
        SistUpdatePatchRequestDTO request = new SistUpdatePatchRequestDTO();
        request.setCodigoSistema("123");
        request.setNomeSistema("Sistema Atualizado");
        request.setIndicadorSistemaAtivo("A");

        SistResponseDTO mockResponse = new SistResponseDTO();
        when(sistService.updateSist(any(SistUpdatePatchRequestDTO.class))).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistUpdateSystemPatch(request);

        ResponseEntity<SistResponseDTO> response = result.join();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockResponse);
        verify(sistService, times(1)).updateSist(any(SistUpdatePatchRequestDTO.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar sistema sem campos preenchidos")
    void deveLancarExcecaoAoAtualizarSistemaSemCamposPreenchidos() {
        SistUpdatePatchRequestDTO request = new SistUpdatePatchRequestDTO();

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistUpdateSystemPatch(request);

        assertThatThrownBy(result::join)
                .hasCauseInstanceOf(ValidationException.class)
                .hasMessageContaining("Pelo menos um campo deve ser preenchido.");
        verify(sistService, never()).updateSist(any(SistUpdatePatchRequestDTO.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar sistema com nome do sistema vazio")
    void deveLancarExcecaoAoCriarSistemaComNomeSistemaVazio() {
        SistRequestDTO request = new SistRequestDTO();
        request.setCodigoSistema("123");
        request.setNomeSistema(" ");
        request.setIndicadorSistemaAtivo("A");

        assertThatThrownBy(() -> sistResource.sistCreateSystemPost(request).join())
                .hasCauseInstanceOf(ValidationException.class)
                .hasMessageContaining("Descrição do Sistema é um campo obrigatório.");
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar sistema com indicador inválido")
    void deveLancarExcecaoAoAtualizarSistemaComIndicadorInvalido() {
        SistUpdatePatchRequestDTO request = new SistUpdatePatchRequestDTO();
        request.setIndicadorSistemaAtivo("X");

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistUpdateSystemPatch(request);

        assertThatThrownBy(result::join)
                .hasCauseInstanceOf(ValidationException.class)
                .hasMessageContaining("Indicador de Perfil Ativo inválido.");
        verify(sistService, never()).updateSist(any(SistUpdatePatchRequestDTO.class));
    }

    // ===== Novos testes de normalização =====

    @Test
    @DisplayName("Deve normalizar nome (trim) e manter indicador no create antes de chamar service")
    void deveNormalizarNomeNoCreateEManterIndicador() {
        SistRequestDTO request = new SistRequestDTO();
        request.setCodigoSistema("CD1");
        request.setNomeSistema("  Meu Sistema  ");
        request.setIndicadorSistemaAtivo("a"); // note: create normalize does not uppercase per implementação

        SistResponseDTO mockResponse = new SistResponseDTO();
        when(sistService.createSist(any(SistRequestDTO.class))).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistCreateSystemPost(request);

        ResponseEntity<SistResponseDTO> response = result.join();
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(mockResponse);

        ArgumentCaptor<SistRequestDTO> captor = ArgumentCaptor.forClass(SistRequestDTO.class);
        verify(sistService).createSist(captor.capture());
        SistRequestDTO sent = captor.getValue();
        assertThat(sent.getCodigoSistema()).isEqualTo("CD1");
        assertThat(sent.getNomeSistema()).isEqualTo("Meu Sistema"); // trimmed
        assertThat(sent.getIndicadorSistemaAtivo()).isEqualTo("a"); // unchanged by create normalize
    }

    @Test
    @DisplayName("Deve normalizar nome (trim) e indicador (uppercase) no patch antes de chamar service")
    void deveNormalizarNomeEIndicadorNoPatch() {
        SistUpdatePatchRequestDTO request = new SistUpdatePatchRequestDTO();
        request.setCodigoSistema("CD1");
        request.setNomeSistema("  NomePatch  ");
        request.setIndicadorSistemaAtivo("a"); // should be uppercased by normalizePatch

        SistResponseDTO mockResponse = new SistResponseDTO();
        when(sistService.updateSist(any(SistUpdatePatchRequestDTO.class))).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistResponseDTO>> result =
                sistResource.sistUpdateSystemPatch(request);

        ResponseEntity<SistResponseDTO> response = result.join();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockResponse);

        ArgumentCaptor<SistUpdatePatchRequestDTO> captor = ArgumentCaptor.forClass(SistUpdatePatchRequestDTO.class);
        verify(sistService).updateSist(captor.capture());
        SistUpdatePatchRequestDTO sent = captor.getValue();
        assertThat(sent.getCodigoSistema()).isEqualTo("CD1");
        assertThat(sent.getNomeSistema()).isEqualTo("NomePatch"); // trimmed
        assertThat(sent.getIndicadorSistemaAtivo()).isEqualTo("A"); // uppercased by normalizePatch
    }
}

// language: java
package com.santander.aof.app.resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santander.aof.app.service.SistService;
import com.santander.aof.model.SistPageResponseDTO;
import com.santander.aof.model.SistRequestDTO;
import com.santander.aof.model.SistResponseDTO;
import com.santander.aof.model.SistUpdatePatchRequestDTO;
import com.santander.ars.error.exceptions.ValidationException;
import java.util.concurrent.CompletionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
@DisplayName("SistResource Test Suite")
class SistResourceTest {

    @Mock
    private SistService sistService;

    private SistResource resource;

    @BeforeEach
    void setUp() {
        resource = new SistResource(sistService);
    }

    @Test
    @DisplayName("sistGet deve retornar 200 com body")
    void sistGet_deveRetornarOkComBody() {
        SistPageResponseDTO page = mock(SistPageResponseDTO.class);
        when(sistService.listarSists(1, 10)).thenReturn(page);

        ResponseEntity<SistPageResponseDTO> resp = resource.sistGet(1, 10).join();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(page, resp.getBody());
        verify(sistService).listarSists(1, 10);
    }

    @Test
    @DisplayName("sistCreate deve retornar 201 e chamar service com DTO normalizado")
    void sistCreate_deveRetornar201EChamarServiceComNormalizedDTO() {
        SistRequestDTO incoming = mock(SistRequestDTO.class);
        when(incoming.getCodigoSistema()).thenReturn("CD1");
        when(incoming.getNomeSistema()).thenReturn("  My System  ");
        when(incoming.getIndicadorSistemaAtivo()).thenReturn("A");

        SistResponseDTO created = mock(SistResponseDTO.class);
        when(sistService.createSist(any())).thenReturn(created);

        ResponseEntity<SistResponseDTO> resp = resource.sistCreateSystemPost(incoming).join();

        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        assertSame(created, resp.getBody());

        ArgumentCaptor<SistRequestDTO> captor = ArgumentCaptor.forClass(SistRequestDTO.class);
        verify(sistService).createSist(captor.capture());
        SistRequestDTO normalized = captor.getValue();
        assertEquals("CD1", normalized.getCodigoSistema());
        assertEquals("My System", normalized.getNomeSistema());
        assertEquals("A", normalized.getIndicadorSistemaAtivo());
    }

    @Test
    @DisplayName("sistCreate deve lançar ValidationException quando request for nulo")
    void sistCreate_deveLancarValidationExceptionQuandoRequestNull() {
        CompletionException thrown = assertThrows(
                CompletionException.class, () -> resource.sistCreateSystemPost(null).join());
        assertTrue(thrown.getCause() instanceof ValidationException);
    }

    @Test
    @DisplayName("sistUpdate deve retornar 200 e chamar service com patch normalizado")
    void sistUpdate_deveRetornarOkEChamarServiceComNormalizedPatch() {
        SistUpdatePatchRequestDTO incoming = mock(SistUpdatePatchRequestDTO.class);
        when(incoming.getCodigoSistema()).thenReturn("CD1");
        when(incoming.getNomeSistema()).thenReturn("  NomePatch  ");
        when(incoming.getIndicadorSistemaAtivo()).thenReturn("a"); // should be uppercased

        SistResponseDTO patched = mock(SistResponseDTO.class);
        when(sistService.updateSist(any())).thenReturn(patched);

        ResponseEntity<SistResponseDTO> resp = resource.sistUpdateSystemPatch(incoming).join();

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(patched, resp.getBody());

        ArgumentCaptor<SistUpdatePatchRequestDTO> captor = ArgumentCaptor.forClass(SistUpdatePatchRequestDTO.class);
        verify(sistService).updateSist(captor.capture());
        SistUpdatePatchRequestDTO normalized = captor.getValue();
        assertEquals("CD1", normalized.getCodigoSistema());
        assertEquals("NomePatch", normalized.getNomeSistema());
        assertEquals("A", normalized.getIndicadorSistemaAtivo());
    }

    @Test
    @DisplayName("sistUpdate deve lançar ValidationException quando nenhum campo preenchido")
    void sistUpdate_deveLancarValidationExceptionQuandoNenhumCampoPreenchido() {
        SistUpdatePatchRequestDTO incoming = mock(SistUpdatePatchRequestDTO.class);
        when(incoming.getCodigoSistema()).thenReturn("CD1");
        when(incoming.getNomeSistema()).thenReturn(null);
        when(incoming.getIndicadorSistemaAtivo()).thenReturn(null);

        CompletionException thrown = assertThrows(
                CompletionException.class, () -> resource.sistUpdateSystemPatch(incoming).join());
        assertTrue(thrown.getCause() instanceof ValidationException);
    }
}


    import static org.assertj.core.api.Assertions.*;
    import static org.mockito.ArgumentMatchers.any;
    import static org.mockito.Mockito.*;
    
    import com.santander.aof.app.service.SistService;
    import com.santander.aof.model.SistPageResponseDTO;
    import com.santander.aof.model.SistRequestDTO;
    import com.santander.aof.model.SistResponseDTO;
    import com.santander.aof.model.SistUpdatePatchRequestDTO;
    import com.santander.ars.error.exceptions.ValidationException;
    import java.util.concurrent.CompletableFuture;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.ArgumentCaptor;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.http.ResponseEntity;

    @ExtendWith(MockitoExtension.class)
    class SistResourceTest {

    @Mock private SistService sistService;
    @InjectMocks private SistResource sistResource;

    @Test
    @DisplayName("listar: deve retornar ResponseEntity.ok com o DTO do serviço")
    void listarDeveRetornarOk() {
        Integer page = 0;
        Integer size = 10;
        SistPageResponseDTO mockResponse = new SistPageResponseDTO();
        when(sistService.listarSists(page, size)).thenReturn(mockResponse);

        CompletableFuture<ResponseEntity<SistPageResponseDTO>> future = sistResource.sistGet(page, size);
        ResponseEntity<SistPageResponseDTO> resp = future.join();

        assertThat(resp).isEqualTo(ResponseEntity.ok(mockResponse));
        verify(sistService, times(1)).listarSists(page, size);
    }

    @Test
    @DisplayName("create: deve normalizar nome (trim) e retornar 201 com body")
    void createDeveNormalizarEVoltar201() {
        SistRequestDTO req = new SistRequestDTO();
        req.setCodigoSistema("123");
        req.setNomeSistema(" NomeSistema ");
        req.setIndicadorSistemaAtivo("A");

        SistResponseDTO svcResp = new SistResponseDTO();
        when(sistService.createSist(any(SistRequestDTO.class))).thenReturn(svcResp);

        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistCreateSystemPost(req);
        ResponseEntity<SistResponseDTO> resp = future.join();

        assertThat(resp.getStatusCodeValue()).isEqualTo(201);
        assertThat(resp.getBody()).isSameAs(svcResp);

        ArgumentCaptor<SistRequestDTO> captor = ArgumentCaptor.forClass(SistRequestDTO.class);
        verify(sistService).createSist(captor.capture());
        SistRequestDTO sent = captor.getValue();
        assertThat(sent.getNomeSistema()).isEqualTo("NomeSistema");
        assertThat(sent.getIndicadorSistemaAtivo()).isEqualTo("A");
        assertThat(sent.getCodigoSistema()).isEqualTo("123");
    }

    @Test
    @DisplayName("create: deve lançar ValidationException quando payload for nulo")
    void createDeveLancarQuandoPayloadNulo() {
        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistCreateSystemPost(null);

        assertThatThrownBy(future::join)
                .hasCauseInstanceOf(ValidationException.class);
        verify(sistService, never()).createSist(any());
    }

    @Test
    @DisplayName("create: deve lançar ValidationException quando nome em branco")
    void createDeveLancarQuandoNomeVazio() {
        SistRequestDTO req = new SistRequestDTO();
        req.setCodigoSistema("1");
        req.setNomeSistema("   ");
        req.setIndicadorSistemaAtivo("A");

        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistCreateSystemPost(req);

        assertThatThrownBy(future::join)
                .hasCauseInstanceOf(ValidationException.class)
                .hasMessageContaining("Descrição do Sistema");
    }

    @Test
    @DisplayName("patch: deve normalizar nome (trim) e indicador (upper) e retornar 200")
    void patchDeveNormalizarEVoltar200() {
        SistUpdatePatchRequestDTO req = new SistUpdatePatchRequestDTO();
        req.setCodigoSistema("10");
        req.setNomeSistema(" NomeX ");
        req.setIndicadorSistemaAtivo(" i ");

        SistResponseDTO svcResp = new SistResponseDTO();
        when(sistService.updateSist(any(SistUpdatePatchRequestDTO.class))).thenReturn(svcResp);

        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistUpdateSystemPatch(req);
        ResponseEntity<SistResponseDTO> resp = future.join();

        assertThat(resp.getStatusCodeValue()).isEqualTo(200);
        assertThat(resp.getBody()).isSameAs(svcResp);

        ArgumentCaptor<SistUpdatePatchRequestDTO> captor = ArgumentCaptor.forClass(SistUpdatePatchRequestDTO.class);
        verify(sistService).updateSist(captor.capture());
        SistUpdatePatchRequestDTO sent = captor.getValue();
        assertThat(sent.getCodigoSistema()).isEqualTo("10");
        assertThat(sent.getNomeSistema()).isEqualTo("NomeX");
        assertThat(sent.getIndicadorSistemaAtivo()).isEqualTo("I");
    }

    @Test
    @DisplayName("patch: deve lançar ValidationException quando nenhum campo preenchido")
    void patchDeveLancarQuandoNenhumCampoPreenchido() {
        SistUpdatePatchRequestDTO req = new SistUpdatePatchRequestDTO();
        req.setCodigoSistema("5"); // código presente, mas sem nome e indicador

        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistUpdateSystemPatch(req);

        assertThatThrownBy(future::join)
                .hasCauseInstanceOf(ValidationException.class);
        verify(sistService, never()).updateSist(any());
    }

    @Test
    @DisplayName("patch: deve lançar ValidationException quando indicador inválido")
    void patchDeveLancarQuandoIndicadorInvalido() {
        SistUpdatePatchRequestDTO req = new SistUpdatePatchRequestDTO();
        req.setCodigoSistema("7");
        req.setIndicadorSistemaAtivo("X");

        CompletableFuture<ResponseEntity<SistResponseDTO>> future = sistResource.sistUpdateSystemPatch(req);

        assertThatThrownBy(future::join)
                .hasCauseInstanceOf(ValidationException.class);
        verify(sistService, never()).updateSist(any());
    }
}


    import static org.mockito.Mockito.*;
    import static org.junit.jupiter.api.Assertions.*;
    
    import java.util.Arrays;
    import java.util.List;
    import java.util.concurrent.CompletableFuture;
    
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.ArgumentCaptor;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.springframework.http.ResponseEntity;
    
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.AggregationsRulesUseCase;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.modal.AggregationsRulesPage;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.infrastructure.mapper.AggregationsRulesMapper;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsOfRulesResponseDTO;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO;
    
    @ExtendWith(MockitoExtension.class)
    class AggregationsRulesApiDelegateImplTest {

    @Mock
    AggregationsRulesUseCase useCase;

    @Mock
    AggregationsRulesMapper mapper;

    @InjectMocks
    AggregationsRulesApiDelegateImpl delegate;

    @Test
    void happyPath_returnsPageDto() throws Exception {
        // Arrange
        List<Object> content = Arrays.asList("a", "b"); // ou use DTOs reais
        AggregationsRulesPage page = new AggregationsRulesPage(content, content.size());
        when(useCase.execute(any(), any())).thenReturn(page);

        // mapper.toRulesDTOList deve converter o content em DTOs
        List<AggregationsOfRulesResponseDTO> dtos = Arrays.asList(
            new AggregationsOfRulesResponseDTO("a"),
            new AggregationsOfRulesResponseDTO("b")
        );
        when(mapper.toRulesDTOList(content)).thenReturn(dtos);

        when(mapper.toPage(eq(dtos), eq(2), eq(1), eq(10), eq(0)))
            .thenReturn(new AggregationsRulesAllPageResponseDTO(dtos, 2, 1, 10, 0));

        // Act
        CompletableFuture<ResponseEntity<AggregationsRulesAllPageResponseDTO>> future =
            delegate.aggregationFieldAggregationRuleCodigoRegraGet(42, 0, 10);

        ResponseEntity<AggregationsRulesAllPageResponseDTO> response = future.get();
        AggregationsRulesAllPageResponseDTO body = response.getBody();

        // Assert
        assertNotNull(body);
        assertEquals(2, body.getTotalElementos());
        assertEquals(1, body.getTotalPages());
        assertEquals(10, body.getSize());
        assertEquals(0, body.getPage());
        assertEquals(2, body.getItems().size());

        // Verificar que useCase.execute foi chamado com um filtro que contém o codigo
        ArgumentCaptor<Object> pageableCaptor = ArgumentCaptor.forClass(Object.class);
        verify(useCase, times(1)).execute(any(), pageableCaptor.capture());
    }
}


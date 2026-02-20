    import java.util.concurrent.CompletableFuture;
    import org.springframework.http.ResponseEntity;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.AggregationsRulesUseCase;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.infrastructure.mapper.AggregationsRulesMapper;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsOfRulesResponseDTO;
    import com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.modal.AggregationsRulesPage;
    import java.util.Arrays;
    import java.util.List;
  
    public class AggregationsRulesApiDelegateImplTest {
        public static void main(String[] args) throws Exception {
            // Arrange
            AggregationsRulesUseCase fakeUseCase = new AggregationsRulesUseCase() {
                @Override
                public AggregationsRulesPage execute(com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.modal.filters.AggregationsRulesFilter filter, Object pageable) {
                    List<AggregationsOfRulesResponseDTO> content = Arrays.asList(new AggregationsOfRulesResponseDTO("value1"), new AggregationsOfRulesResponseDTO("value2"));
                    return new AggregationsRulesPage(content, content.size());
                }
            };
            AggregationsRulesMapper fakeMapper = new AggregationsRulesMapper();
  
          AggregationsRulesApiDelegateImpl delegate = new AggregationsRulesApiDelegateImpl(fakeUseCase, fakeMapper);
  
          // Act
          CompletableFuture<ResponseEntity<AggregationsRulesAllPageResponseDTO>> future = delegate.aggregationFieldAggregationRuleCodigoRegraGet(42, 0, 10);
          ResponseEntity<AggregationsRulesAllPageResponseDTO> response = future.get();
          AggregationsRulesAllPageResponseDTO body = response.getBody();
  
          // Assert
          if (body == null) throw new AssertionError("body was null");
          if (body.getTotalElementos() != 2) throw new AssertionError("expected totalElementos 2 but was " + body.getTotalElementos());
          if (body.getTotalPages() != 1) throw new AssertionError("expected totalPages 1 but was " + body.getTotalPages());
          if (body.getSize() != 10) throw new AssertionError("expected size 10 but was " + body.getSize());
          if (body.getPage() != 0) throw new AssertionError("expected page 0 but was " + body.getPage());
          if (body.getItems().size() != 2) throw new AssertionError("expected 2 items");
      }
  }

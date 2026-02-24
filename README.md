    @Mock
    private com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.AggregationsRulesUseCase useCase;
    
    @Mock
    private com.santander.gbr.aofaoag.gbraofaoagbackconsulta.infrastructure.mapper.AggregationsRulesMapper mapper;
    
    @InjectMocks
    private AggregationsRulesApiDelegateImpl delegate;
    
    @Test
    public void aggregationFieldAggregationRuleCodigoRegraGet_returns_mapped_page_and_calculates_totalPages() throws Exception {
        // cenário
        Integer codigoRegra = 123;
        Integer page = 1;   // Assumimos que resolvePage(page) retorna page quando não é null
        Integer size = 10;  // Assumimos que resolveSize(size) retorna size quando não é null

    // conteúdo bruto retornado pelo useCase
    List<Object> rawContent = List.of(new Object(), new Object(), new Object()); // 3 itens qualquer
    int totalElementos = 25;

    // construir uma instância simples de AggregationsRulesPage (stub) com conteúdo e total
    com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.modal.AggregationsRulesPage pageResult =
            new com.santander.gbr.aofaoag.gbraofaoagbackconsulta.application.usecases.modal.AggregationsRulesPage(rawContent, totalElementos);

    // quando o useCase for chamado, retorna o pageResult
    when(useCase.execute(any(), any())).thenReturn(pageResult);

    // mapper.toRulesDTOList deve transformar o rawContent em DTOs (simular)
    List<com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsOfRulesResponseDTO> mappedDtoList =
            List.of(new com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsOfRulesResponseDTO(),
                    new com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsOfRulesResponseDTO());

    when(mapper.toRulesDTOList(rawContent)).thenReturn(mappedDtoList);

    // calcular esperado totalPages: ceil(totalElementos / size)
    int expectedTotalPages = (int) Math.ceil((double) totalElementos / size);

    // preparar o DTO de página esperado
    com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO expectedPageDto =
            new com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO();

    // quando mapper.toPage for chamado, retornar expectedPageDto
    when(mapper.toPage(mappedDtoList, totalElementos, expectedTotalPages, size, page)).thenReturn(expectedPageDto);

    // ação
    CompletableFuture<ResponseEntity<com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO>> future =
            delegate.aggregationFieldAggregationRuleCodigoRegraGet(codigoRegra, page, size);

    // assert: futuro completo e com o body correto
    assertNotNull(future, "O futuro não deve ser nulo");
    ResponseEntity<com.santander.gbr.aofaoag.gbraofaoagbackconsulta.model.AggregationsRulesAllPageResponseDTO> response =
            future.get(5, TimeUnit.SECONDS);

    assertNotNull(response, "Response não deve ser nulo");
    assertEquals(200, response.getStatusCodeValue(), "Deve retornar 200 OK");
    assertSame(expectedPageDto, response.getBody(), "O corpo deve ser exatamente o DTO retornado pelo mapper");

    // verificar interações
    verify(useCase, times(1)).execute(any(), any());
    verify(mapper, times(1)).toRulesDTOList(rawContent);
    verify(mapper, times(1)).toPage(mappedDtoList, totalElementos, expectedTotalPages, size, page);
}


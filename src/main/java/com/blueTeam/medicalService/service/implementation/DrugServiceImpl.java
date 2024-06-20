package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.fda.DrugDto;
import com.blueTeam.medicalService.exception.JsonProcessException;
import com.blueTeam.medicalService.feign.FdaServiceClient;
import com.blueTeam.medicalService.service.DrugService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {
    private final FdaServiceClient feignClient;
    private final ObjectMapper objectMapper;

    @Override
    public DrugDto getDrugInfo(String name) {
        JsonNode root;
        try {
            root = objectMapper.readTree(feignClient.getDrugInfo(name));
        } catch (JsonProcessingException e) {
            throw new JsonProcessException(e.getMessage());
        }
        JsonNode meta = root.get("meta");
        JsonNode results = root.get("results");

        return DrugDto.builder()
                .name(name)
                .productElements(results.get(0).get("spl_product_data_elements").toPrettyString())
                .disclaimer(meta.get("disclaimer").toPrettyString())
                .purpose(results.get(0).get("purpose").toPrettyString())
                .warnings(results.get(0).get("warnings").toPrettyString())
                .activeIngredient(results.get(0).get("active_ingredient").toPrettyString())
                .build();
    }
}

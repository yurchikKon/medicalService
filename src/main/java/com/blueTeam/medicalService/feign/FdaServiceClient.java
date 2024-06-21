package com.blueTeam.medicalService.feign;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${app.feign.config.name}", url = "${app.feign.config.url}")
public interface FdaServiceClient {

  @GetMapping(value = "/drug/label.json?search=openfda.generic_name:{name}")
  String getDrugInfo(@PathVariable String name);

}

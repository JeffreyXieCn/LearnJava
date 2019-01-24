package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

  @GetMapping("/filtering")
  public SomeBean retrieveSomeBean() {
    SomeBean someBean = new SomeBean("value1", "value2", "value3");
    return someBean;
  }

  @GetMapping("/dynamic-filtering")
  public MappingJacksonValue retrieveDynamicFilteringBean() {
    DynamicFilteringBean dynaBean = new DynamicFilteringBean("value1", "value2", "value3");
    return createMappingJacksonValue(dynaBean, "field1", "field2");
  }

  @GetMapping("/dynamic-filtering-list")
  public MappingJacksonValue retrieveListOfDynamicFilteringBeans() {
    List<DynamicFilteringBean> listOfDynaBeans =
        Arrays.asList(
            new DynamicFilteringBean("value1", "value2", "value3"),
            new DynamicFilteringBean("value4", "value5", "value6"));

    return createMappingJacksonValue(listOfDynaBeans, "field2", "field3");
  }

  private MappingJacksonValue createMappingJacksonValue(Object valueToMap, String... fields) {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
    FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicBeanFilter", filter);
    MappingJacksonValue mapping = new MappingJacksonValue(valueToMap);
    mapping.setFilters(filters);

    return mapping;
  }

  @GetMapping("/filtering-list")
  public List<SomeBean> retrieveListOfSomeBeans() {
    return Arrays.asList(
        new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
  }
}

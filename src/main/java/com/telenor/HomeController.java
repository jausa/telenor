package com.telenor;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;


@RestController
@Slf4j
public class HomeController {

    @Autowired
    ProductService productService;


    @RequestMapping("/product")
    public List<Product> home(
            @RequestParam(required = false, name = "type") String type,
            @RequestParam(required = false, name = "min_price") Double minPrice,
            @RequestParam(required = false, name = "max_price") Double maxPrice,
            @RequestParam(required = false, name = "city") String city,
            @RequestParam(required = false, name = "property.color") String propertyColor,
            @RequestParam(required = false, name = "property.gb_limit_min") Integer propertyGBMin,
            @RequestParam(required = false, name = "property.gb_limit_max") Integer propertyGBMax
    )  {
        return productService.filter(type, minPrice, maxPrice, city, propertyColor, propertyGBMin, propertyGBMax);

    }

}

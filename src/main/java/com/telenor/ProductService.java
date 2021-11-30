package com.telenor;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;


@Service
@Slf4j
public class ProductService {

    //@Value("classpath:data.csv")
    //Resource resourceFile;

private static InputStream getFile()throws FileNotFoundException{
     ClassLoader cl =ProductService.class.getClassLoader();

    return cl.getResourceAsStream("data.csv");
}

    private List<Product> getData() {
        CsvMapper csvMapper = new CsvMapper();
        try {
            CsvSchema schema = CsvSchema.builder()
                    .addColumn("Product type")
                     .addColumn("Product properties")
                    .addColumn("Price")
                    .addColumn("Store address")
                    .build();
            MappingIterator<Product> productsIterator = csvMapper
                    .readerWithTypedSchemaFor(Product.class)
                    .with(schema.withSkipFirstDataRow(true))
                    .readValues(getFile());
                    //.readValues(resourceFile.getFile());

            return productsIterator.readAll();

        } catch (IOException e) {
            log.error("Error", e);
        }

        return Collections.emptyList();
    }


    public List<Product> filter(String type, Double minPrice, Double maxPrice, String city, String propertyColor, Integer propertyGBMin, Integer propertyGBMax) {
       List<Product> products = getData().stream().filter(Objects::nonNull)
               .filter(p -> {
                   if (type != null) {
                       return p.getType().equalsIgnoreCase(type);
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (minPrice != null) {
                       return Double.parseDouble(p.getPrice()) >= minPrice;
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (maxPrice != null) {
                       return Double.parseDouble(p.getPrice()) <= maxPrice;
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (city != null) {
                       return p.getStoreAddress().contains(city);
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (propertyColor != null) {
                       String[] dataSplit = p.getProperties().split(":");
                       return "color".equalsIgnoreCase(dataSplit[0]) && propertyColor.equalsIgnoreCase(dataSplit[1]);
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (propertyGBMin != null) {
                       String[] dataSplit = p.getProperties().split(":");
                       return "gb_limit".equalsIgnoreCase(dataSplit[0]) && Integer.parseInt(dataSplit[1]) >= propertyGBMin;
                   } else {
                       return true;
                   }
               })
               .filter(p -> {
                   if (propertyGBMax != null) {
                       String[] dataSplit = p.getProperties().split(":");
                       return "gb_limit".equalsIgnoreCase(dataSplit[0]) && Integer.parseInt(dataSplit[1]) <= propertyGBMax;
                   } else {
                       return true;
                   }
               }) .collect(toList());


        return products;
    }
}

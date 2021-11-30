package com.telenor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @JsonProperty("Product type")
    private String type;
    @JsonProperty("Price")
    private String price;
    @JsonProperty("Product properties")
    private String properties;
    @JsonProperty("Store address")
    private String StoreAddress;
}

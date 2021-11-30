package com.telenor.assignment;

import com.telenor.Product;
import com.telenor.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = {ProductService.class, Resource.class})

class ProductServiceTests {

	@Test
	void shouldFilterOnType() {
			ProductService service = new ProductService();
			List<Product> result = service.filter("phone",null,null,null,null,null,null);
			assertEquals(42, result.size());
		}

	@Test
	void shouldFilterOnMinPrice() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,705D,null,null,null,null,null);
		assertEquals(24, result.size());
	}
	@Test
	void shouldFilterOnMaxPrice() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,150D,null,null,null,null);
		assertEquals(18, result.size());
	}
	@Test
	void shouldFilterOnMinAndMaxPrice() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,60D,150D,null,null,null,null);
		assertEquals(10, result.size());
	}

	@Test
	void shouldFilterOnCity() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,"Svensson allén, Stockholm",null,null,null);
		assertEquals(1, result.size());
	}

	@Test
	void shouldFilterOnColor() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,null,"grå",null,null);
		assertEquals(4, result.size());
	}

	@Test
	void shouldFilterOnGBMin() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,null,null,50,null);
		assertEquals(34, result.size());
	}

	@Test
	void shouldFilterOnGBMax() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,null,null,null,100);
		assertEquals(58, result.size());
	}

	@Test
	void shouldFilterOnGBMinGBMax() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,null,null,10,49);
		assertEquals(24, result.size());
	}

	@Test
	void shouldGetAll() {
		ProductService service = new ProductService();
		List<Product> result = service.filter(null,null,null,null,null,null,null);
		assertEquals(100, result.size());
	}

	@Test
	void shouldFilterOnTypeMinPriceMaxPriceCityColor() {
		ProductService service = new ProductService();
		List<Product> result = service.filter("phone",700D,900D,"Malmö","rosa",null,null);
		assertEquals(1, result.size());
	}
}




package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.database.repository.CustomProductRepositoryImpl;
import com.zakharenkov.shop.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@SessionAttributes({"filter", "currentUser", "basket"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getPage() {
        return "redirect:/products/1";
    }

    @GetMapping("/products/{page}")
    public String getProducts(Model model, @PathVariable("page") String page) {
        FilterDto filter = getFilter(model, page);

        loadAllAttribute(filter, model);
        return "products";
    }

    @PostMapping("/products")
    public String getForm(Model model, FilterDto filter) {
        model.addAttribute("filter", filter);
        return "redirect:/products";
    }

    @GetMapping("/clear-filter")
    public String clearFilter(Model model) {
        FilterDto filter = FilterDto.builder()
                .brand(CustomProductRepositoryImpl.ANY)
                .orderByDesc(true)
                .pageSize(10)
                .page(1)
                .build();

        model.addAttribute("filter", filter);
        return "redirect:/products";
    }

    private FilterDto getFilter(Model model, String page) {
        FilterDto filter = (FilterDto) model.asMap().get("filter");
        if (filter == null) {
            filter = FilterDto.builder()
                    .brand(CustomProductRepositoryImpl.ANY)
                    .orderByDesc(true)
                    .pageSize(10)
                    .page(1)
                    .build();
        }
        filter.setPage(Integer.parseInt(page));

        return filter;
    }

    private List<Integer> getCountPage(FilterDto filter, Long count) {
        Long countPage = count % filter.getPageSize() == 0 ? count / filter.getPageSize() : count / filter.getPageSize() + 1;
        return IntStream.rangeClosed(1, countPage.intValue()).boxed().collect(Collectors.toList());
    }

    private void loadAllAttribute(FilterDto filter, Model model) {
        List<Product> products = productService.findByFilter(filter);
        Long countProduct = productService.getCountProduct(filter);
        Set<String> allBrand = productService.getAllBrand();
        List<Integer> pageSizeList = IntStream.rangeClosed(1, 6).boxed().map(it -> it * 5).collect(Collectors.toList());

        model.addAttribute("products", products);
        model.addAttribute("count", countProduct);
        model.addAttribute("filter", filter);
        model.addAttribute("brands", allBrand);
        model.addAttribute("countPage", getCountPage(filter, countProduct));
        model.addAttribute("pageSizeList", pageSizeList);
    }
}

package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.repository.CustomProductRepositoryImpl;
import com.zakharenkov.shop.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes({"filter", "brands"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getAllProducts(Model model,
                                 HttpServletRequest servletRequest) {
        FilterDto filter = (FilterDto) servletRequest.getSession().getAttribute("filter");

        if (filter == null) {
            filter = FilterDto.builder()
                    .brand(CustomProductRepositoryImpl.ANY)
                    .orderByDesc(true)
                    .pageSize(10)
                    .page(1)
                    .build();
        }

        String page = servletRequest.getParameter("page");
        String pageSize = servletRequest.getParameter("pageSize");
        if (page != null) {
            filter.setPage(Integer.parseInt(page));
        }
        if (pageSize != null) {
            filter.setPageSize(Integer.parseInt(pageSize));
        }

        List<Product> products = productService.findByFilter(filter);
        Long countProduct = productService.getCountProduct(filter);
        Set<String> allBrand = productService.getAllBrand();
        List<Integer> pageSizeList = Arrays.asList(5, 10, 15, 20);

        model.addAttribute("products", products);
        model.addAttribute("count", countProduct);
        model.addAttribute("filter", filter);
        model.addAttribute("brands", allBrand);
        model.addAttribute("countPage", getCountPage(filter, countProduct));
        model.addAttribute("pageSizeList", pageSizeList);

        return "products";
    }

    private List<Integer> getCountPage(FilterDto filter, Long count) {
        Long countPage;
        if (count % filter.getPageSize() == 0) {
            countPage = count / filter.getPageSize();
        } else {
            countPage = count / filter.getPageSize() + 1;
        }
        List<Integer> countPageList = new ArrayList<>();
        for (int i = 0; i < countPage; i++) {
            countPageList.add(i + 1);
        }
        return countPageList;
    }

    @PostMapping("/products")
    public String getForm(Model model,
                          @SessionAttribute("filter") FilterDto filter2,
                          HttpServletRequest servletRequest) {
        System.out.println(filter2 + "*********************");

        FilterDto filter = getFilter(servletRequest);

        List<Product> products = productService.findByFilter(filter);
        Long countProduct = productService.getCountProduct(filter);
        Set<String> allBrand = productService.getAllBrand();
        List<Integer> pageSizeList = Arrays.asList(5, 10, 15, 20);



        model.addAttribute("products", products);
        model.addAttribute("count", countProduct);
        model.addAttribute("filter", filter);
        model.addAttribute("brands", allBrand);
        model.addAttribute("countPage", getCountPage(filter, countProduct));
        model.addAttribute("pageSizeList", pageSizeList);

        return "products";
    }

    private FilterDto getFilter(HttpServletRequest req) {
        String tv = req.getParameter("tv");
        String audio = req.getParameter("audio");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String pageSize = req.getParameter("pageSize");

        System.out.println(req.getParameter("orderByDesc") + "+++++++++++++++++");
        FilterDto filter = FilterDto.builder()
                .brand(req.getParameter("brand"))
                .pageSize(Integer.parseInt(pageSize))
                .page(1)
                .orderByDesc(Boolean.parseBoolean(req.getParameter("orderBy")))
                .build();

        if (StringUtils.isEmpty(tv)) {
            filter.setTv(null);
        } else {
            filter.setTv(Integer.parseInt(tv));
        }

        if (StringUtils.isEmpty(audio)) {
            filter.setAudio(null);
        } else {
            filter.setAudio(Integer.parseInt(audio));
        }

        if (StringUtils.isEmpty(minPrice)) {
            filter.setMinPrice(null);
        } else {
            filter.setMinPrice(Integer.parseInt(minPrice));
        }

        if (StringUtils.isEmpty(maxPrice)) {
            filter.setMaxPrice(null);
        } else {
            filter.setMaxPrice(Integer.parseInt(maxPrice));
        }

        return filter;
    }
}

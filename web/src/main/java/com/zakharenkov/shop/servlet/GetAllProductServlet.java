package com.zakharenkov.shop.servlet;

import com.zakharenkov.shop.dto.FilterDto;
import com.zakharenkov.shop.model.Product;
import com.zakharenkov.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class GetAllProductServlet extends HttpServlet {

    private static final String EMPTY = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterDto filter = FilterDto.builder()
                .brand("ALL")
                .orderBy("desc")
                .pageSize(10)
                .page(1)
                .build();
        List<Product> products = ProductService.getInstance().findByFilter(filter);
        Long count = ProductService.getInstance().getCountProduct(filter);

        req.setAttribute("products", products);
        req.setAttribute("count", count);
        System.out.println(count + "+++++++++++++++++++");
        req.getSession().setAttribute("filter", filter);
        req.getSession().setAttribute("brands", ProductService.getInstance().getAllBrand());

        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/products.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterDto filter = getFilter(req);

        System.out.println(filter);

        req.getSession().setAttribute("filter", filter);
        req.getSession().setAttribute("brands", ProductService.getInstance().getAllBrand());

        List<Product> products = ProductService.getInstance().findByFilter(filter);
        req.setAttribute("products", products);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(req, resp);
    }

    private FilterDto getFilter(HttpServletRequest req) {
        String tv = req.getParameter("tv");
        String audio = req.getParameter("audio");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String pageSize = req.getParameter("pageSize");

        FilterDto filter = FilterDto.builder()
                .brand(req.getParameter("brand"))
                .orderBy(req.getParameter("rad"))
                .pageSize(Integer.parseInt(pageSize))
                .page(1)
                .build();

        if (EMPTY.equals(tv) || tv == null) {
            filter.setTv(null);
        } else {
            filter.setTv(Integer.parseInt(tv));
        }

        if (EMPTY.equals(audio) || audio == null) {
            filter.setAudio(null);
        } else {
            filter.setAudio(Integer.parseInt(audio));
        }

        if (EMPTY.equals(minPrice) || minPrice == null) {
            filter.setMinPrice(null);
        } else {
            filter.setMinPrice(Integer.parseInt(minPrice));
        }

        if (EMPTY.equals(maxPrice) || maxPrice == null) {
            filter.setMaxPrice(null);
        } else {
            filter.setMaxPrice(Integer.parseInt(maxPrice));
        }

        return filter;
    }
}

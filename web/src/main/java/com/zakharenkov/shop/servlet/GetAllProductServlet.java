package com.zakharenkov.shop.servlet;

import com.zakharenkov.shop.dto.FilterDto;
import com.zakharenkov.shop.model.Product;
import com.zakharenkov.shop.service.ProductService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class GetAllProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterDto filter = (FilterDto) req.getSession().getAttribute("filter");
        if (filter == null) {
            filter = FilterDto.builder()
                    .brand("Любой")
                    .orderBy("desc")
                    .pageSize(10)
                    .page(1)
                    .build();
        }
        if (req.getParameter("page") != null) {
            filter.setPage(Integer.parseInt(req.getParameter("page")));
        }

        List<Product> products = ProductService.getInstance().findByFilter(filter);
        Long count = ProductService.getInstance().getCountProduct(filter);

        req.setAttribute("products", products);
        req.setAttribute("count", count);
        req.getSession().setAttribute("filter", filter);
        req.getSession().setAttribute("brands", ProductService.getInstance().getAllBrand());
        req.setAttribute("countPage", getCountPage(filter, count));


        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/products.jsp")
                .forward(req, resp);
    }

    private Long getCountPage(FilterDto filter, Long count) {
        Long countPage;
        if (count % filter.getPageSize() == 0) {
            countPage = count / filter.getPageSize();
        } else {
            countPage = count / filter.getPageSize() + 1;
        }
        return countPage;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FilterDto filter = getFilter(req);
        Long count = ProductService.getInstance().getCountProduct(filter);

        req.getSession().setAttribute("filter", filter);
        req.getSession().setAttribute("brands", ProductService.getInstance().getAllBrand());
        req.setAttribute("count", count);
        req.setAttribute("countPage", getCountPage(filter, count));


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

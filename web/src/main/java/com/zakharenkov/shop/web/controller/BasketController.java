package com.zakharenkov.shop.web.controller;

import com.zakharenkov.shop.database.model.Coupon;
import com.zakharenkov.shop.database.model.Discount;
import com.zakharenkov.shop.database.model.LineItem;
import com.zakharenkov.shop.database.model.Order;
import com.zakharenkov.shop.database.model.OrderStatus;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.model.Sale;
import com.zakharenkov.shop.database.model.TypeSale;
import com.zakharenkov.shop.service.dto.SaleIdDto;
import com.zakharenkov.shop.service.service.LineItemService;
import com.zakharenkov.shop.service.service.OrderService;
import com.zakharenkov.shop.service.service.ProductService;
import com.zakharenkov.shop.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.zakharenkov.shop.database.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Controller
@SessionAttributes({"currentUser", "basket"})
public class BasketController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private LineItemService lineItemService;

    @GetMapping("/basket")
    public String get(Model model) {
        System.out.println();
        Order basket = (Order) model.asMap().get("basket");
        return "basket";
    }

    @GetMapping("/basket/add/{id}")
    public String addBasket(Model model, @PathVariable("id") String id) {
        System.out.println();
        Product product = productService.getById(Long.parseLong(id)).orElse(null);
        Order basket = (Order) model.asMap().get("basket");
        LineItem lineItem = LineItem.builder()
                .product(product)
                .count(1)
                .build();
        boolean check = true;
        if (product != null) {
            if (basket.getLineItems().size() > 0) {
                for (LineItem item : basket.getLineItems()) {
                    if (lineItem.getProduct().equals(item.getProduct())) {
                        item.setCount(item.getCount() + 1);
                        check = false;
                    }
                }
            }
            if (check) {
                basket.getLineItems().add(lineItem);
            }
        }

        return "redirect:/basket";
    }

    @GetMapping("/basket/sale")
    public String getPageSale(Model model) {
        System.out.println();
        String result;
        User currentUser = (User) model.asMap().get("currentUser");
        if (currentUser.getSales() != null) {
            if (currentUser.getSales().size() > 0) {
                result = "basketSale";
            } else {
                result = "redirect:/basket/total";
            }
        } else {
            result = "redirect:/basket/total";
        }
        return result;
    }

    @GetMapping("/basket/sale/{id}")
    public String getPageTotal(Model model, @PathVariable("id") String id) {
        Long saleId = Long.parseLong(id);
        User currentUser = (User) model.asMap().get("currentUser");
        Set<Sale> sales = currentUser.getSales();

        Order basket = (Order) model.asMap().get("basket");
        Set<LineItem> lineItems = basket.getLineItems();
        Integer sum = 0;
        for (LineItem lineItem : lineItems) {
            sum += lineItem.getProduct().getPrice();
        }
        if (saleId != 0) {
            for (Sale sale : sales) {
                if (saleId.equals(sale.getId())) {
                    if (TypeSale.DISCOUNT.equals(sale.getType())) {
                        Discount sale1 = (Discount) sale;
                        sum = (Integer) (sum * (100 - sale1.getPrecent()) / 100);
                    }
                    if (TypeSale.COUPON.equals(sale.getType())) {
                        Coupon sale1 = (Coupon) sale;
                        sum = (Integer) (sum - sale1.getCount());
                    }
                }
            }
        }
        basket.setPrice(sum);
        return "basketTotal";
    }

    @GetMapping("/basket/total")
    public String getTotalOrder(Model model) {
        Order basket = (Order) model.asMap().get("basket");
        User currentUser = (User) model.asMap().get("currentUser");
        Set<Sale> sales = currentUser.getSales();

//        Long deleteSaleId = null;
//        for (Sale sale : sales) {
//            if (saleId.getId().equals(sale.getId())) {
//                boolean remove = sales.remove(sale);
//            }
//        }
        currentUser.setSales(sales);

        basket.setStatus(OrderStatus.PROCESSED);
        basket.setDate(LocalDate.now());
        basket = orderService.save(basket);
        Set<LineItem> lineItems = basket.getLineItems();
        for (LineItem lineItem : lineItems) {
            lineItem.setOrder(basket);
            lineItemService.save(lineItem);
        }

        userService.update(currentUser);
        basket = null;
        return "basketSuccess";
    }
}

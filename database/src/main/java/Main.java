import dao.ProductDao;
import lombok.Cleanup;
import model.Product;
import model.ProductDetail;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        Product product = Product.builder()
                .category_id(1)
                .price(1000)
                .productDetail(ProductDetail.of("Samsung", "UE32M5550AUXRU", "", ""))
                .build();
        ProductDao.getInstance().add(product);
        System.out.println(product);
    }
}

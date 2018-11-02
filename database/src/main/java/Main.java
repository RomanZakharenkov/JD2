import dao.BaseDao;
import model.Role;
import model.User;

public class Main {

    public static void main(String[] args) {


//        Product product = BaseDao.getInstance().findById(Product.class, 3L);
        BaseDao<User> dao = new BaseDao<>();
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("qwesadqw")
                .password("21321")
                .build();

        Long save = dao.save(user);
        User user1 = dao.findById(User.class, save).get();
        dao.delete(user1);

        System.out.println(user);
//        User user = User.builder()
//                .lastName("sada")
//                .firstName("sadas")
//                .email("sadawqsa")
//                .password("qweasd")
//                .role(Role.USER)
//                .build();
//
//        Serializable save = BaseDao.getInstance().save(user);
//        System.out.println(save);
    }
}

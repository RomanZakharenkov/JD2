import dao.BaseDaoImpl;
import dao.UserDaoImpl;
import model.Role;
import model.User;

public class Main {

    public static void main(String[] args) {


//        BaseDaoImpl<User> dao = new BaseDaoImpl<>();

//        ProductDao.getInstance().findByFilter().forEach(System.out::println);

        User user = User.builder()
                .lastName("sada")
                .firstName("sadas")
                .email("sadawqsa")
                .password("qweasd")
                .role(Role.USER)
                .build();

        Long save = UserDaoImpl.getInstance().save(user);
    }
}

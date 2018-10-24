package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void getName() throws Exception {
        assert "USER".equals(Role.USER.getName());
    }

    @Test
    public void checkToString() throws Exception {
        assertNotNull(Role.ADMIN.toString());
    }

}
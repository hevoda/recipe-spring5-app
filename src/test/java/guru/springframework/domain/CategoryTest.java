package guru.springframework.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    public void setUp(){

        category = new Category();

    }


    @Test
    void getId()  {
        Long idValue = 4l;
        category.setId(idValue);
        assertEquals(4l ,category.getId());
    }

    @Test
    void getDescription()  {
    }

    @Test
    void getRecipes() {
    }
}
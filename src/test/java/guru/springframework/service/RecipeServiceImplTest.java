package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class RecipeServiceImplTest {

    @InjectMocks
    RecipeServiceImpl recipeServiceImpl;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipesByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Optional<Recipe> returnedRecipe = Optional.ofNullable(recipeServiceImpl.findById(1L));

        assertNotNull("Null recipe returned" , String.valueOf(returnedRecipe));
        verify(recipeRepository , times(1)).findById(anyLong());
        verify(recipeRepository ,never()).findAll();
    }

    @Test
    void getRecipesTest() throws Exception {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeServiceImpl.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipeSet = recipeServiceImpl.getRecipes();

        assertEquals(recipeSet.size() , 1);
        verify(recipeRepository , times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
}
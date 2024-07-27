package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    void setUp() {
        initMocks(this);
        controller = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        //given
        Set<Recipe> recipeSet = new HashSet<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();

        recipe1.setId(17L);
        recipe2.setDescription("Second recipe!!");

        recipeSet.add(recipe2);
        recipeSet.add(recipe1);
        recipeSet.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);

        //then
        assertEquals("index" , viewName);
        verify(recipeService,times(1)).getRecipes() ;
        verify(model, times(1)).addAttribute(eq("recipes") , argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(3,setInController.size());
    }
}
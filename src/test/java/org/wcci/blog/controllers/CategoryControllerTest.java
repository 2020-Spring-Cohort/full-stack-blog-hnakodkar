package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.Controllers.CategoryController;
import org.wcci.blog.Models.Category;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {


        private MockMvc mockMvc;
        private CategoryController underTest;
        private CategoryStorage categoryStorage;
        private Model mockModel;
        private PostStorage postStorage;
        private AuthorStorage authorStorage;

        @BeforeEach
        public void setUp() {
            mockModel = mock(Model.class);
            categoryStorage = mock(CategoryStorage.class);
            authorStorage = mock(AuthorStorage.class);
            postStorage = mock(PostStorage.class);
            underTest = new CategoryController(categoryStorage, postStorage, authorStorage);
            mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        }

        @Test
        public void shouldReturnViewWithOneCategory() {
            Category testCategory = new Category("TESTER TOWN");
            when(categoryStorage.findCategoryByName("Fashion")).thenReturn(testCategory);

            underTest.displaySingleCategory("Fashion", mockModel);

            verify(categoryStorage).findCategoryByName("Fashion");
            verify(mockModel).addAttribute("category", testCategory);
        }

        @Test
        public void shouldReturnViewNamedCategoryDisplaySingleCategoryIsCalled() {
            String viewName = underTest.displaySingleCategory("Fashion", mockModel);
            assertThat(viewName).isEqualTo("category");
        }

        @Test
        public void shouldGoToIndividualEndPoint() throws Exception {
            Category testCategory = new Category("Fashion");
            when(categoryStorage.findCategoryByName("Fashion")).thenReturn(testCategory);

            mockMvc.perform(get("/categories/Fashion"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("category"))
                    .andExpect(model().attributeExists("category"))
                    .andExpect(model().attribute("category", testCategory));
        }

        @Test
        public void categoriesEndPointDisplaysAllCategories() throws Exception {
            Category testCampus = new Category("Fashion");

            List<Category> categoryCollection = Collections.singletonList(testCampus);
            when(categoryStorage.findAllCategories()).thenReturn(categoryCollection);
            mockMvc.perform(get("/categories"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("blogPage"))
                    .andExpect(model().attributeExists("categories"))
                    .andExpect(model().attribute("categories", categoryCollection));
        }

        @Test
        public void addCategoryShouldRedirectToCategoriesEndPoint() {
            String result = underTest.addCategory("Cars");
            assertThat(result).isEqualTo("redirect:categories");
        }

        @Test
        public void addCategoryShouldStoreANewCategory() {
            underTest.addCategory("Cars");
            verify(categoryStorage).store(new Category("Cars"));
        }

        @Test
        public void addCategoryEndpointShouldAddNewCategory() throws Exception {
            mockMvc.perform(post("/add-category")
                    .param("categoryName", "Cars"))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
            verify(categoryStorage).store(new Category("Cars"));
        }

}

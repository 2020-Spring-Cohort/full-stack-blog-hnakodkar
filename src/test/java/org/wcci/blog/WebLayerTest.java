package org.wcci.blog;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @MockBean
    CategoryStorage mockStorage;
    @MockBean
    PostStorage postStorage;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void categoriesShouldBeOkAndReturnTheCategoryViewWithCategoryModelAttribute() throws Exception {
        mockMvc.perform(get("/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("blogPage"))
                .andExpect(model().attributeExists("categories"));

    }
    @Test
    public void shouldReceiveOKFromSingleCategoryEndpoint() throws Exception {
        Category testCategory = new Category("Fashion");
        when(mockStorage.findCategoryByName("Fashion")).thenReturn(testCategory);
        mockMvc.perform(get("/categories/Fashion"))
                .andExpect(status().isOk())
                .andExpect(view().name("category"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    public void shouldBeAbleToCreateNewCategory() throws Exception {
        mockMvc.perform(post("/add-category")
                .param("categoryName", "Fashion"))
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Category("Fashion"));
    }

}

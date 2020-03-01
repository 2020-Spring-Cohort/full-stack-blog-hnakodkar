package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.Controllers.CategoryController;
import org.wcci.blog.Controllers.PostController;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import static org.mockito.Mockito.mock;

public class PostControllerTest {
    private MockMvc mockMvc;
    private PostController underTest;
    private PostStorage postStorage;
    private Model mockModel;



    @BeforeEach
    public void setUp() {
        mockModel = mock(Model.class);
        postStorage = mock(PostStorage.class);
        underTest = new PostController(postStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();

    }

}
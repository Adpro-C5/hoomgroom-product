package id.ac.ui.cs.advprog.product.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CatalogueControllerTest {
    @Mock
    private Model model;

    @InjectMocks
    private CatalogueController catalogueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testProductCataloguePage() {
        String viewName = catalogueController.productCataloguePage();
        assert(viewName.equals("Catalogue"));
    }
}

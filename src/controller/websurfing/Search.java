package controller.websurfing;

import technicalservices.persistence.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.products.Category;
import model.products.Manufacturer;
import model.products.Product;

import java.io.IOException;
import java.util.ArrayList;

public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId, page;

        try {
            categoryId = Integer.parseInt(req.getParameter("category"));
        } catch (NumberFormatException e){
            categoryId = -1;
        }

        try {
            page = Integer.parseInt(req.getParameter("p"));
        } catch (NumberFormatException e){
            page = 1;
        }

        String keyword = req.getParameter("keyword");
        if(keyword == null)
            keyword = "";

        float lowerBound = 0;
        float upperBound = 5000;

        int productCount = DBManager.getInstance().getProductsCount(categoryId, "", lowerBound,
                upperBound, keyword);
        int productsInAPage = 9;
        int numPages = productCount / productsInAPage;
        if(productCount % productsInAPage != 0)
            numPages++;

        ArrayList<Product> products = DBManager.getInstance().getProducts(categoryId, page, "",
                lowerBound, upperBound, 0, keyword);
        Category category = DBManager.getInstance().getCategory(categoryId);
        ArrayList<Manufacturer> manufacturers = DBManager.getInstance().getManufacturers(categoryId, keyword);
        req.setAttribute("category", category);
        req.setAttribute("products", products);
        req.setAttribute("manufacturers", manufacturers);
        req.setAttribute("pages", numPages);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(req, resp);
    }
}

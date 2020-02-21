package controller.websurfing;

import com.google.gson.Gson;

import model.products.Product;
import technicalservices.persistence.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchJSON extends HttpServlet {
    private Gson gson = new Gson();

    private int getPagesNumber(int productCount){
        int productsInAPage = 9;
        int numPages = productCount / productsInAPage;
        if(productCount % productsInAPage != 0)
            numPages++;

        return numPages;
    }

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

        String orderBy = req.getParameter("orderBy");
        int orderType;

        try {
            switch (orderBy) {
                case "priceAsc":
                    orderType = 1;
                    break;
                case "priceDesc":
                    orderType = 2;
                    break;
                default:
                    orderType = 0;
            }
        } catch (NullPointerException e){
            orderType = 0;
        }

        String filterBy = req.getParameter("filterBy");
        if(filterBy == null)
            filterBy = "";

        String keyword = req.getParameter("keyword");
        if(keyword == null)
            keyword = "";

        float lowerBound, upperBound;

        try {
            lowerBound = Float.parseFloat(req.getParameter("priceFrom"));
        } catch (NumberFormatException | NullPointerException e){
            lowerBound = -1;
        }

        try {
            upperBound = Float.parseFloat(req.getParameter("priceTo"));
        } catch (NumberFormatException | NullPointerException e){
            upperBound = -1;
        }


        int productCount = 0;
        int pagesNumber = 0;

        productCount = DBManager.getInstance().getProductsCount(categoryId, filterBy, lowerBound, upperBound, keyword);
        pagesNumber = getPagesNumber(productCount);
        ArrayList<Product> products = DBManager.getInstance().getProducts(categoryId, page, filterBy, lowerBound,
                upperBound, orderType, keyword);

        String json = this.gson.toJson(products);
        String completeJSONToSend = "[{\"pages\":" + pagesNumber + "}";
        if(!json.equals("[]"))
        	completeJSONToSend += ",";
        completeJSONToSend += json.substring(1);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(completeJSONToSend);
        out.flush();

    }
}

package controller.websurfing;

import com.google.gson.Gson;

import model.products.Review;
import technicalservices.persistence.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FindReviews extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int offset, productId;

        try {
            offset = Integer.parseInt(req.getParameter("offset"));
        } catch (NumberFormatException | NullPointerException e){
            offset = -1;
        }

        try {
            productId = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException | NullPointerException e){
            productId = -1;
        }

        if(productId != -1 && offset != -1) {
            ArrayList<Review> reviews = DBManager.getInstance().getLastReviews(productId, offset);
            String json = this.gson.toJson(reviews);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }
    }
}

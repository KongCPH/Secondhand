package app.controllers;

import app.entities.Listing;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.ListingMapper;
import app.persistence.UserMapper;
import app.entities.User;
import io.javalin.http.Context;

import java.util.List;

public class UserController {


    public static void login(Context ctx, ConnectionPool connectionPool){
        try{
            String name = ctx.formParam("name");
            String password = ctx.formParam("psw");
            User user = UserMapper.login(name, password, connectionPool);
            List<Listing> listings = ListingMapper.getListingHeadlinesByUserID(user.getId(), connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.attribute("listings", listings);
            ctx.render("welcome");

        }
        catch (DatabaseException ex){
            ctx.attribute("msg", ex.getMessage());
            ctx.render("index");
        }


    }
}

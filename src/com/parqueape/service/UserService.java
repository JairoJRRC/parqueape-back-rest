package com.parqueape.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.parqueape.entity.User;

@Path("/users")
public class UserService {
        private static final long serialVersionUID = 1L;
        @GET
        @Produces("application/json")
        public Response getUsers() throws JSONException {
                List<User> users = new ArrayList();
                users.add(new User("admin"));
                users.add(new User("john"));
                users.add(new User("usuario2"));
                return Response.status(200).entity(users.toString()).build();
        }
}

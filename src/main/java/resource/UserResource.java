package resource;

import dto.UpdateUserData;
import dto.UserData;
import dto.UserLogin;
import entity.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.UserRepository;
import service.PasswordService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("user")
public class UserResource {
    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordService passwordService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserData>getUser(){
        List<User>userList = userRepository.listAll();

        return userList.stream()
                .map(item -> new UserData(item.getId(),item.getName(),item.getEmail(),item.getPassword(),item.getNo_telp(),item.getRole(),item.getStatus(),item.getCreated()))
                .collect(Collectors.toList());

    }

    @POST
    @Path("create")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserData userData){
        User user = new User();

        user.setName(userData.name());
        user.setEmail(userData.email());
        String hashedPassword = passwordService.hashPassword(userData.password());
        user.setPassword(hashedPassword);
        user.setNo_telp(userData.no_telp());
        user.setRole(userData.role());
        user.setStatus("True");
        user.setCreated(Instant.now());

        userRepository.persist(user);

        return  Response.ok().build();

    }
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserLogin userLogin) {

//        User user = userRepository.findByEmail(userLogin.getEmail());
//
//        if (user != null) {
//            boolean passwordMatch = passwordService.verifyPassword(userLogin.getPassword(), user.getPassword());
//
//            if (passwordMatch) {
//                return Response.ok().build();
//            } else {
//                return Response.status(Response.Status.UNAUTHORIZED).build();
//            }
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }

        Optional<User> userOptional = userRepository.findByEmail(userLogin.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            boolean passwordMatch = passwordService.verifyPassword(userLogin.getPassword(), user.getPassword());

            if (passwordMatch) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, UserData userData) {
        Optional<User> optionalUser = userRepository.findByIdOptional(id);
        User user = optionalUser.get();

        user.setName(userData.name());
        user.setEmail(userData.email());
        String hashedPassword = passwordService.hashPassword(userData.password());
        user.setPassword(hashedPassword);
        user.setNo_telp(userData.no_telp());
        user.setRole(userData.role());
        user.setStatus(userData.status());

        userRepository.persist(user);

        return Response.ok().build();

    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id")Long id){
            Optional<User>userOptional = userRepository.findByIdOptional(id);
            if(userOptional.isPresent()){
                userRepository.delete(userOptional.get());

                return Response.ok().build();
            }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}

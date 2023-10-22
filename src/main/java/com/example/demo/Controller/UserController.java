package com.example.demo.Controller;



import com.example.demo.Entity.UserAuth;
import com.example.demo.Response.ResponseEntity;
import com.example.demo.Service.MyUserDetailsService;
import com.example.demo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity health() {
        return new ResponseEntity("I am healty, User Microservice is up",null);
    }

    @GetMapping("/all")
    public ResponseEntity getUser() {
       return new ResponseEntity("Successfully returned all users",null);

    }
    @GetMapping("/{userId}")
    public ResponseEntity getUser(@PathVariable("userId") int userId) {
        // Your logic here
        return new ResponseEntity("Succesfully returned "+userId+".",null);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody UserAuth user)throws Exception{

        try{
            System.out.println("before auth");
            System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword())));
            System.out.println("after auth");

        }
        catch (AuthenticationException e){
            System.out.println("catch");

            //throw new Exception("Incorect username and password ",e);
            return new ResponseEntity("password mismatch",null);
        }

        System.out.println("outside catch");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getName());
        final String jwt=jwtUtil.generateToken(userDetails);
        return new ResponseEntity("successfully created jwt",jwt);



    }
    // Implement update and delete endpoints as needed
}

package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthetificationController {

    @RequestMapping(value="/register", method = RequestMethod.POST,produces="application/json",consumes="application/json")
    public Map<String, String> greeting(@RequestBody User user) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println(user.getUsername());
        if(user.getUsername() == "sou"){
            response.put("message","success");
            return response;
        }else{
            response.put("message","failure");
            return response;
        }


    }
}

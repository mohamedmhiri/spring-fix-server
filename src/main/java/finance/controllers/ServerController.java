package finance.controllers;

import finance.models.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fix")
public class ServerController {

    @RequestMapping(method = RequestMethod.POST, path = "/logon")
    public Greeting getMessage(@RequestBody Greeting input) {
        return new Greeting(1, input.getContent());
    }
}

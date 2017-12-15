package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quickfix.ConfigError;
import quickfix.SessionNotFound;

@RestController
public class FIXController {

    @RequestMapping(value="/processTransaction", method = RequestMethod.POST, consumes = "application/json")
    public  Map<String, String> greeting(@RequestBody Transaction t) throws InterruptedException, ConfigError, SessionNotFound {
                Map<String,String> result = new HashMap<>();
                result.put("orderid", t.getOrderid());
                result.put("side",t.getSide());
                result.put("ordertype",t.getOrdertype());

                System.out.println("Executing requested transaction : ");

                FIX.SenderApp.execute(t.getOrderid(),t.getSide(),t.getOrdertype(),t.getOrderqty(),t.getPrice(),t.getSymbol());




            return result;

    }
}

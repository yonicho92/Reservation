
package clothesRental.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by uengine on 2018. 11. 21..
 */

@FeignClient(name="Payment", url="http://:8082")
public interface PaymentProcessingService {

    @RequestMapping(method= RequestMethod.GET, path="/payProcessings")
    public void payment(@RequestBody PaymentProcessing paymentProcessing);

}
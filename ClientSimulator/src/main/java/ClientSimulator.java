import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.model.Customer;
import mdb.utils.GsonWrapper;

public class ClientSimulator {

    //public static String endpoint = "http://159.89.18.108:8080/DTUPay-0.5/api";
    public static String endpoint = "http://localhost:8080/DTUPay-0.5/api";

    public ClientSimulator(){}

    public ResponseModel createCustomer(Customer customer) throws UnirestException {
        //make json
        HttpResponse<String> r = Unirest.post(endpoint + "/customer").header("Content-Type", "application/json").body(GsonWrapper.toJson(customer)).asString();

        return new ResponseModel(r.getStatus(), r.getBody());
    }

}
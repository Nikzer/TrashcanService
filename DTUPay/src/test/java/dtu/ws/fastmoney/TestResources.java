package dtu.ws.fastmoney;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.ExternalResource;

import static org.junit.Assert.assertEquals;

public class TestResources extends ExternalResource {
    public BankServiceService service;
    public BankService server;
    public User user1;
    public User user2;

    protected void before() {

        service = new BankServiceService();
        server = service.getBankServicePort();
        user1 = new User();
        user2 = new User();

        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setCprNumber("0304451357");

        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setCprNumber("0204451358");
        }

    protected void after() {
        try {
            Account account = server.getAccountByCprNumber(user1.getCprNumber());
            server.retireAccount(account.id);
        } catch (BankServiceException_Exception e) {
            assertEquals("Account does not exist", e.getMessage());
        }
        try {
            Account account = server.getAccountByCprNumber(user2.getCprNumber());
            server.retireAccount(account.id);
        } catch (BankServiceException_Exception e) {
            assertEquals("Account does not exist", e.getMessage());
        }

    }
}


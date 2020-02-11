import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

public class ApiTest {
    @Test
    void checkIfAhemEmailServiceIsAvailable (){
        ApiTestData testData = new ApiTestData();
        ApiStep.ahemSystemCheck(testData);
    }

    @Test
    void getAhemEmailToken () {
        ApiTestData testData = new ApiTestData();
        ApiStep.getAhemServiceEmailToken(testData);
    }

    @Test
    void getDataFromEmail() throws MessagingException, InterruptedException {
        ApiTestData testData = new ApiTestData();
        String ahemEmail = ApiUtils.generateRandomString(6);
        String bodyText = "Body Text: " + ApiUtils.generateRandomString(10);
        String emailSub = "Email Subject" + ApiUtils.generateRandomString(7);
        ApiStep.getAhemServiceEmailToken(testData);
        SendEmail.send("balamutsanzh@yandex.ru", ahemEmail + "@ahem.email",
                emailSub, bodyText);
        ApiStep.getAhemEmailList(testData, ahemEmail);


    }
}

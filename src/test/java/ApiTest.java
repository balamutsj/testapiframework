import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

public class ApiTest {
    @Test
    void checkIfAhemEmailServiceIsAvailable (){
        ApiTestData testData = new ApiTestData();
        ApiStep.ahemSystemCheck(testData);
    }

    @Test
    void getAhemEmailToken () throws MessagingException {
        ApiTestData testData = new ApiTestData();
        //SendEmail.send("balamutsanzh@yandex.ru", );
        ApiStep.getAhemServiceEmailToken(testData);
    }//"balamutsanzh@yandex.ru", "balamut_sj@mail.ru", "smtp.yandex.com", "balamutsanzh", "zveri098ugly"sa29nj05ifrit86
}

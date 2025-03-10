import org.fabbit.ConfigUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.fabbit.Platform;
import org.fabbit.LoginPage;

public class loginUser {

    @Test
    @Parameters("platform")
    public void testLoginWithToken(String platform) {

        Platform platformEnum = Platform.valueOf(platform);
        String token = ConfigUtil.getToken(platformEnum); // Retrieve token based on platform
        LoginPage loginPage = new LoginPage(platformEnum);
        loginPage.loginWithToken(token);
    }
}
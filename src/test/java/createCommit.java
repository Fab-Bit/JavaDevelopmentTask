import org.fabbit.ConfigUtil;
import org.fabbit.Platform;
import org.fabbit.RepositoryUseCase;
import org.testng.annotations.Test;

public class createCommit {

    @Test(priority = 2)
    public void testCreateCommit(String platform) {

        Platform platformEnum = Platform.valueOf(platform);
        String apiUrl = ConfigUtil.getApiUrl(platformEnum);
        String username = ConfigUtil.getUsername(platformEnum);
        String token = ConfigUtil.getToken(platformEnum);
        RepositoryUseCase repository = new RepositoryUseCase(platformEnum, apiUrl, username, token);
        repository.createCommit();
    }
}
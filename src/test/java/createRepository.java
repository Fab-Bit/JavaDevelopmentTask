import org.fabbit.ConfigUtil;
import org.fabbit.Platform;
import org.fabbit.RepositoryUseCase;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class createRepository {

    @Test(priority = 1, threadPoolSize = 3)

    public void testCreateRepository(String platform) {

        Platform platformEnum = Platform.valueOf(platform);
        String apiUrl = ConfigUtil.getApiUrl(platformEnum);
        String username = ConfigUtil.getUsername(platformEnum);
        String token = ConfigUtil.getToken(platformEnum);
        RepositoryUseCase repository = new RepositoryUseCase(platformEnum, apiUrl, username, token);
        repository.createRepository();
    }
}
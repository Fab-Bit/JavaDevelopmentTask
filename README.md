# Java Development Task: Github Testing

This project demonstrates the use of REST Assured for API testing in a Java application. It includes creating a repository, committing changes, and creating pull requests using GitHub's API.

## Prerequisites

- Java 11 or higher
- Maven
- GitHub Personal Access Token
- 
## Running Tests Manually

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/JavaDevelopmentTask.git
    cd JavaDevelopmentTask
    ```

2. Configure your GitHub credentials in the `ConfigUtil` class.

3. Run the tests using Maven:
    ```sh
    mvn test -Dsurefire.suiteXmlFiles=testng.xml -Dplatform=GITHUB
    ```

## Running Tests Using GitHub Actions

1. Go to Actions tab on Github repo main page.

2. Select Run Tests from the list of workflows.
3. Click on the Run workflow button. 
 ```

## License

This project is licensed under the MIT License.
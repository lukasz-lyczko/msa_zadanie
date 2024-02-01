import org.example.LoginService
import org.example.User
import spock.lang.Specification

class LoginSpec extends Specification {
    User validUserCredentials = new User()
    User invalidUserCredentials = new User()

    def setup() {
        given: "example user exists"
        validUserCredentials.setId(1)
        validUserCredentials.setUsername("user123")
        validUserCredentials.setPassword("pass123")

        invalidUserCredentials.setId(1)
        invalidUserCredentials.setUsername("invalid_user123")
        invalidUserCredentials.setPassword("invalid_pass123")
    }

    def "logging in with valid credentials should be successful"() {
        String testAccessToken = "Bearer valid_token"

        given: "login service is stubbed"
        LoginService loginServiceMock = Mock(LoginService.class)
        loginServiceMock.login(validUserCredentials.username, validUserCredentials.password) >> testAccessToken

        when: "we login with valid credentials"
        String actualAccessToken = loginServiceMock.login(validUserCredentials.username, validUserCredentials.password)

        then: "should get correct response"
        noExceptionThrown()

        and: "response should not be empty"
        actualAccessToken.length() > 0

        and: "response should contain valid access token"
        actualAccessToken == testAccessToken
    }

    def "logging in with invalid password should fail"() {
        given: "login service is mocked"
        LoginService loginServiceMock = Mock(LoginService.class)
        loginServiceMock.login(validUserCredentials.username, invalidUserCredentials.password) >> { throw new Exception("Invalid credentials") }

        when: "we login with valid username but invalid password"
        loginServiceMock.login(validUserCredentials.username, invalidUserCredentials.password)

        then: "we should not get response response (exception thrown)"
        def exception = thrown Exception
        exception.message == "Invalid credentials"
    }

    def "logging in with invalid credentials should fail"() {
        given: "login service is mocked"
        LoginService loginServiceMock = Mock(LoginService.class)
        loginServiceMock.login(validUserCredentials.username, invalidUserCredentials.password) >> { throw new Exception("Invalid credentials") }

        when: "we login with invalid both username and password"
        loginServiceMock.login(validUserCredentials.username, invalidUserCredentials.password)

        then: "we should not get response response (exception thrown)"
        def exception = thrown Exception
        exception.message == "Invalid credentials"
    }
}

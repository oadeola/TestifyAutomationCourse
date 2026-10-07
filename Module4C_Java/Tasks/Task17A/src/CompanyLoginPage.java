 public class CompanyLoginPage extends LoginPage {

        @Override
        public void rememberMe() {
            System.out.println("Remember Me checkbox is available");
        }

        @Override
        public void continueToHomePage() {
            System.out.println("Continue to Home Page is available");
        }

        @Override
        public void oAuthButton() {
            System.out.println("OAuth button is available");
        }

        public static void main(String[] args) {

            CompanyLoginPage login = new CompanyLoginPage();

            login.usernameField();
            login.passwordField();
            login.forgotPassword();
            login.signInButton();

            login.rememberMe();
            login.continueToHomePage();
            login.oAuthButton();
        }
    }


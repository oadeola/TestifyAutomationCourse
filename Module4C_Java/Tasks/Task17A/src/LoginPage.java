public abstract class LoginPage {
        // Common to all login pages
        public void usernameField() {
            System.out.println("Username field is available");
        }

        public void passwordField() {
            System.out.println("Password field is available");
        }

        public void forgotPassword() {
            System.out.println("Forgot Password field is available");
        }

        public void signInButton() {
            System.out.println("Sign In button is available");
        }

        // Optional elements
        public abstract void rememberMe();

        public abstract void continueToHomePage();

        public abstract void oAuthButton();
    }


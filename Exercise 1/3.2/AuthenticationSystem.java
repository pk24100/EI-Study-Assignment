// Component interface
 interface Authenticator {
    boolean authenticate(String username, String password);
}

// Concrete component
 class SimpleAuthenticator implements Authenticator {
    @Override
    public boolean authenticate(String username, String password) {
        // Simple authentication logic
        return username.equals("admin") && password.equals("password");
    }
}

// Decorator
 abstract class AuthenticationDecorator implements Authenticator {
    protected Authenticator authenticator;

    public AuthenticationDecorator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authenticator.authenticate(username, password);
    }
}

// Concrete decorator - MultiFactorAuthenticator
 class MultiFactorAuthenticator extends AuthenticationDecorator {
    public MultiFactorAuthenticator(Authenticator authenticator) {
        super(authenticator);
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Additional multi-factor authentication logic
        return super.authenticate(username, password) && verifyTwoFactorCode();
    }

    private boolean verifyTwoFactorCode() {
        // Logic to verify two-factor code
        return true;
    }
}

// Concrete decorator - RoleBasedAuthenticator
 class RoleBasedAuthenticator extends AuthenticationDecorator {
    public RoleBasedAuthenticator(Authenticator authenticator) {
        super(authenticator);
    }

    @Override
    public boolean authenticate(String username, String password) {
        // Additional role-based authentication logic
        return super.authenticate(username, password) && hasRequiredRole();
    }

    private boolean hasRequiredRole() {
        // Logic to check required role
        return true;
    }
}

public class AuthenticationSystem {
    public static void main(String[] args) {
        // Create simple authenticator
        Authenticator simpleAuthenticator = new SimpleAuthenticator();

        // Create multi-factor authenticator decorator
        Authenticator multiFactorAuthenticator = new MultiFactorAuthenticator(simpleAuthenticator);

        // Create role-based authenticator decorator
        Authenticator roleBasedAuthenticator = new RoleBasedAuthenticator(multiFactorAuthenticator);

        // Use decorated authenticator
        boolean authenticated = roleBasedAuthenticator.authenticate("admin", "password");
        System.out.println("Authenticated: " + authenticated);
    }
}
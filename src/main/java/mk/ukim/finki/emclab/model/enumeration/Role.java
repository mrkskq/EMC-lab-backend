package mk.ukim.finki.emclab.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

// lab3

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMINISTRATOR;

    @Override
    public String getAuthority() {
        return name();
    }
}


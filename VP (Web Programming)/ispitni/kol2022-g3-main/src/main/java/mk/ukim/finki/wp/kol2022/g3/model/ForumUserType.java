package mk.ukim.finki.wp.kol2022.g3.model;

import org.springframework.security.core.GrantedAuthority;

public enum ForumUserType implements GrantedAuthority {
    ADMIN,
    REGULAR,
    GOLDEN;

    @Override
    public String getAuthority() {
        return "ROLE_"+name();
    }
}

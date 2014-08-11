package net.pkhapps.fenix.core.ldap;

import net.pkhapps.fenix.core.entity.FireDepartmentUser;
import net.pkhapps.fenix.core.entity.FireDepartmentUserRepository;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import java.util.Collection;

/**
 * A {@link org.springframework.security.ldap.userdetails.UserDetailsContextMapper} for {@link FenixLdapUserDetailsImpl}.
 */
public class FenixLdapUserDetailsMapper extends LdapUserDetailsMapper {

    // TODO Make the attribute names configurable

    private final FireDepartmentUserRepository fireDepartmentUserRepository;
    private final String displayNameAttributeName = "displayName";
    private final String commonNameAttributeName = "cn";

    public FenixLdapUserDetailsMapper(FireDepartmentUserRepository fireDepartmentUserRepository) {
        this.fireDepartmentUserRepository = fireDepartmentUserRepository;
    }

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        final FenixLdapUserDetailsImpl.Essence essence = new FenixLdapUserDetailsImpl.Essence((LdapUserDetails) super.mapUserFromContext(ctx, username, authorities));

        final String displayNameValue = ctx.getStringAttribute(displayNameAttributeName);
        final String commonNameValue = ctx.getStringAttribute(commonNameAttributeName);

        if (displayNameValue != null) {
            essence.setDisplayName(displayNameValue.toString());
        } else {
            essence.setDisplayName(commonNameValue);
        }

        final FireDepartmentUser fireDepartmentUser = fireDepartmentUserRepository.findByUid(username);
        if (fireDepartmentUser != null) {
            return new FireDepartmentUserDetailsWrapper(essence.createTarget(), fireDepartmentUser);
        } else {
            return essence.createUserDetails();
        }
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        throw new UnsupportedOperationException("FenixLdapUserDetailsMapper only supports reading from a context. Please" +
                "use a subclass if mapUserToContext() is required.");
    }
}

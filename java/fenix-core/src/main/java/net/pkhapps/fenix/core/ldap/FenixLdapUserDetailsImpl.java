package net.pkhapps.fenix.core.ldap;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.ldap.FenixLdapUserDetails}.
 */
public class FenixLdapUserDetailsImpl extends LdapUserDetailsImpl implements FenixLdapUserDetails {

    private String displayName;



    protected FenixLdapUserDetailsImpl() {
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public static class Essence extends LdapUserDetailsImpl.Essence {

        public Essence() {
        }

        public Essence(DirContextOperations ctx) {
            super(ctx);
        }

        public Essence(LdapUserDetails copyMe) {
            super(copyMe);
            if (copyMe instanceof FenixLdapUserDetails) {
                setDisplayName(((FenixLdapUserDetails) copyMe).getDisplayName());
            }
        }

        @Override
        protected FenixLdapUserDetailsImpl createTarget() {
            return new FenixLdapUserDetailsImpl();
        }

        public void setDisplayName(String displayName) {
            ((FenixLdapUserDetailsImpl) instance).displayName = displayName;
        }
    }
}

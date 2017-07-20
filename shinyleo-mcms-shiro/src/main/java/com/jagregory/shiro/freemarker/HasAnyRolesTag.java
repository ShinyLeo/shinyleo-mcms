package com.jagregory.shiro.freemarker;

import org.apache.shiro.subject.Subject;

/**
 * Created by shinyleo on 17/7/20.
 */
public class HasAnyRolesTag  extends RoleTag{

    private static final String ROLE_NAMES_DELIMETER = ",";

    public HasAnyRolesTag() {
    }

    protected boolean showTagBody(String roleNames) {
        boolean hasAnyRole = false;
        Subject subject = this.getSubject();
        if(subject != null) {
            String[] var7;
            int var6 = (var7 = roleNames.split(",")).length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String role = var7[var5];
                if(subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }

        return hasAnyRole;
    }
}

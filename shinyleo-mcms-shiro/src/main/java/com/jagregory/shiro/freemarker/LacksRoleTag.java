package com.jagregory.shiro.freemarker;

/**
 * Created by shinyleo on 17/7/20.
 */
public class LacksRoleTag extends RoleTag{

    public LacksRoleTag() {
    }

    protected boolean showTagBody(String roleName) {
        boolean hasRole = this.getSubject() != null && this.getSubject().hasRole(roleName);
        return !hasRole;
    }

}

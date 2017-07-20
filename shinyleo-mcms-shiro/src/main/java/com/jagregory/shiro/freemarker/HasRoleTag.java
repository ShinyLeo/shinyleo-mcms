package com.jagregory.shiro.freemarker;

/**
 * Created by shinyleo on 17/7/20.
 */
public class HasRoleTag extends RoleTag{

    public HasRoleTag() {
    }

    protected boolean showTagBody(String roleName) {
        return this.getSubject() != null && this.getSubject().hasRole(roleName);
    }
}

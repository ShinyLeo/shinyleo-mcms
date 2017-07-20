package com.jagregory.shiro.freemarker;

import freemarker.template.SimpleHash;

/**
 * Created by shinyleo on 17/7/20.
 */
public class ShiroTags extends SimpleHash {

    public ShiroTags() {
        this.put("authenticated", new AuthenticatedTag());
        this.put("guest", new GuestTag());
        this.put("hasAnyRoles", new HasAnyRolesTag());
        this.put("hasPermission", new HasPermissionTag());
        this.put("hasRole", new HasRoleTag());
        this.put("lacksPermission", new LacksPermissionTag());
        this.put("lacksRole", new LacksRoleTag());
        this.put("notAuthenticated", new NotAuthenticatedTag());
        this.put("principal", new PrincipalTag());
        this.put("user", new UserTag());
    }

}

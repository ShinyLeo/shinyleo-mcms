package com.jagregory.shiro.freemarker;

/**
 * Created by shinyleo on 17/7/20.
 */
public class HasPermissionTag  extends PermissionTag{

    public HasPermissionTag() {
    }

    protected boolean showTagBody(String p) {
        return this.isPermitted(p);
    }

}

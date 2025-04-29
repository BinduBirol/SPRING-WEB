package com.birol.user.entity;

import com.birol.util.BootstrapColor;

public enum UserRoles {
    ADMIN("Admin", "fas fa-user-shield", BootstrapColor.WARNING),
    USER("User", "fas fa-user-circle", BootstrapColor.SUCCESS),
    MODERATOR("Moderator", "fas fa-users-cog", BootstrapColor.INFO),
    GUEST("Guest", "fas fa-user-secret", BootstrapColor.LIGHT);

    private final String displayName;
    private final String iconClass;
    private final BootstrapColor bootstrapColor;

    UserRoles(String displayName, String iconClass, BootstrapColor bootstrapColor) {
        this.displayName = displayName;
        this.iconClass = iconClass;
        this.bootstrapColor = bootstrapColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIconClass() {
        return iconClass;
    }

    public String getTextColorClass() {
        return bootstrapColor.getTextColorClass();
    }

    public String getTableClass() {
        return bootstrapColor.getTableClass();
    }

    public String getBackgroundColorClass() {
        return bootstrapColor.getBackgroundColorClass();
    }
}

package com.birol.util;

public enum BootstrapColor {
    PRIMARY("text-primary", "table-primary", "bg-primary"),
    SECONDARY("text-secondary", "table-secondary", "bg-secondary"),
    SUCCESS("text-success", "table-success", "bg-success"),
    DANGER("text-danger", "table-danger", "bg-danger"),
    WARNING("text-warning", "table-warning", "bg-warning"),
    INFO("text-info", "table-info", "bg-info"),
    LIGHT("text-light", "table-light", "bg-light"),
    DARK("text-dark", "table-dark", "bg-dark"),
    MUTED("text-muted", "", ""),
    WHITE("text-white", "", "bg-white"),
    TRANSPARENT("text-transparent", "", "bg-transparent");

    private final String textColorClass;
    private final String tableClass;
    private final String backgroundColorClass;

    BootstrapColor(String textColorClass, String tableClass, String backgroundColorClass) {
        this.textColorClass = textColorClass;
        this.tableClass = tableClass;
        this.backgroundColorClass = backgroundColorClass;
    }

    public String getTextColorClass() {
        return textColorClass;
    }

    public String getTableClass() {
        return tableClass;
    }

    public String getBackgroundColorClass() {
        return backgroundColorClass;
    }
}


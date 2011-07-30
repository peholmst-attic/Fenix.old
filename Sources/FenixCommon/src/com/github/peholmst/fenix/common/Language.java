package com.github.peholmst.fenix.common;

import java.util.Locale;

public enum Language {
    ENGLISH(new Locale("en"), "English"), SWEDISH(new Locale("sv"), "Svenska"), FINNISH(
            new Locale("fi"), "Suomi");
    private final Locale locale;
    private final String displayName;

    private Language(Locale locale, String displayName) {
        this.locale = locale;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Locale getLocale() {
        return locale;
    }
}

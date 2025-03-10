package org.fabbit;

public enum Platform {
    GITHUB("https://api.github.com/"),
    GITLAB("https://gitlab.com/api/v4/");

    private final String apiUrl;

    Platform(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}

package com.example.desafiogfxisrael.domain;

public enum Category {
    MENS_CLOTHING("men's clothing", "Men's clothing"),
    WOMENS_CLOTHING("women's clothing", "Women's clothing"),
    JEWELERY("jewelery", "Jewelery"),
    ELECTRONICS("electronics", "Electronics");

    private final String apiValue;
    private final String displayName;

    Category(String apiValue, String displayName) {
        this.apiValue = apiValue;
        this.displayName = displayName;
    }

    public String getApiValue() {
        return apiValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromApiValue(String apiValue) {
        if (apiValue == null) {
            return null;
        }
        for (Category category : values()) {
            if (category.apiValue.equalsIgnoreCase(apiValue.trim())) {
                return category;
            }
        }
        return null;
    }
}

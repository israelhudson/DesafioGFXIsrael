package com.example.desafiogfxisrael.domain;

public enum Category {
    MENS_CLOTHING("men's clothing", "Men's clothing"),
    WOMENS_CLOTHING("women's clothing", "Women's clothing"),
    JEWELERY("jewelery", "Jewelery"),
    ELECTRONICS("electronics", "Electronics");

    private final String keyValue;
    private final String displayName;

    Category(String keyValue, String displayName) {
        this.keyValue = keyValue;
        this.displayName = displayName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromKeyValue(String keyValue) {
        if (keyValue == null) {
            return null;
        }
        for (Category category : values()) {
            if (category.keyValue.equalsIgnoreCase(keyValue.trim())) {
                return category;
            }
        }
        return null;
    }

    public static Category fromFilterPosition(int position) {
        switch (position) {
            case 1:
                return MENS_CLOTHING;
            case 2:
                return WOMENS_CLOTHING;
            case 3:
                return JEWELERY;
            case 4:
                return ELECTRONICS;
            default:
                return null;
        }
    }
}

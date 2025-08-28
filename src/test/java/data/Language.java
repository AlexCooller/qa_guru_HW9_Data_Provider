package data;

public enum Language {
    ru("Противодействие мошенничеству"),
    en("Contact us");

    public final String description;

    Language(String description) {
        this.description = description;
    }
}

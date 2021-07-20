package edy.epam.task5.entity;

public enum TypeOfElement {
    TEXT("\r\n"),
    PARAGRAPH(" "),
    SENTENCE(" "),
    LEXEME(""),
    WORD(""),
    SYMBOL(""),
    PUNCTUATION("");

    private final String delimiter;

    TypeOfElement(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}

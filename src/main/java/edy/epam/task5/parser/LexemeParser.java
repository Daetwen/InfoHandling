package edy.epam.task5.parser;

import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;

public class LexemeParser implements ElementTextParser {
    private static final String LEXEME_REGEX = "\\s";
    private final ElementTextParser parser = new WordParser();

    @Override
    public void parse(TextElement textElement, String data) throws InfoHandlingException {
        if (data == null || data.isBlank()) {
            throw new InfoHandlingException("Input data in lexeme parser is not correct.");
        }
        String[] lexemes = data.split(LEXEME_REGEX);
        for (var lexeme : lexemes) {
            if (!lexeme.isEmpty()) {
                TextComposite lexemeElement = new TextComposite(TypeOfElement.LEXEME);
                textElement.add(lexemeElement);
                parser.parse(lexemeElement, lexeme);
            }
        }
    }
}

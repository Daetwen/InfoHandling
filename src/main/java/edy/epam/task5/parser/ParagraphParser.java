package edy.epam.task5.parser;

import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParagraphParser implements ElementTextParser {
    private static final String PARAGRAPH_REGEX = "(\\s{4}|\\t)";
    private final ElementTextParser parser = new SentenceParser();

    @Override
    public void parse(TextElement textElement, String data) throws InfoHandlingException {
        if (data == null || data.isBlank()) {
            throw new InfoHandlingException("Input data in paragraph parser is not correct.");
        }
        String[] paragraphs = data.split(PARAGRAPH_REGEX);
        List<String> paragraphsList = new ArrayList<>(Arrays.asList(paragraphs));
        paragraphsList.remove(0);
        for (var paragraph : paragraphsList) {
            TextComposite paragraphElement = new TextComposite(TypeOfElement.PARAGRAPH);
            textElement.add(paragraphElement);
            parser.parse(paragraphElement, paragraph);
        }
    }
}

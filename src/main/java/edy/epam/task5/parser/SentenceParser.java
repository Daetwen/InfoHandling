package edy.epam.task5.parser;

import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements ElementTextParser {
    private static final String SENTENCE_REGEX = ".+?([.]{3}|[.!?])((\\\\r\\\\n)|$|\\s)";
    private final ElementTextParser parser = new LexemeParser();

    @Override
    public void parse(TextElement textElement, String data) throws InfoHandlingException {
        if (data == null || data.isBlank()) {
            throw new InfoHandlingException("Input data in sentence parser is not correct.");
        }
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            String sentence = matcher.group();
            TextComposite sentenceElement = new TextComposite(TypeOfElement.SENTENCE);
            textElement.add(sentenceElement);
            parser.parse(sentenceElement, sentence);
        }
    }
}

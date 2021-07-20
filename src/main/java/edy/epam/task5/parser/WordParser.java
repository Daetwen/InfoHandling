package edy.epam.task5.parser;

import edy.epam.task5.entity.Symbol;
import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements ElementTextParser {
    private static final String WORD_REGEX = "(.*)+[^.,!?]";
    private static final String PUNCTUATION_REGEX = "([.]{3}|[.!?,])$";
    private final ElementTextParser parser = new SymbolParser();

    @Override
    public void parse(TextElement textElement, String data)  throws InfoHandlingException {
        if (data == null || data.isBlank()) {
            throw new InfoHandlingException("Input data in word parser is not correct.");
        }
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(data);
        Pattern patternPunctuation = Pattern.compile(PUNCTUATION_REGEX);
        Matcher matcherPunctuation = patternPunctuation.matcher(data);
        while (matcher.find()) {
            String word = matcher.group();
            TextComposite wordElement = new TextComposite(TypeOfElement.WORD);
            textElement.add(wordElement);
            parser.parse(wordElement, word);
        }
        while (matcherPunctuation.find()) {
            String punctuation = matcherPunctuation.group();
            Symbol symbol = new Symbol(punctuation, TypeOfElement.PUNCTUATION);
            textElement.add(symbol);
        }
    }
}

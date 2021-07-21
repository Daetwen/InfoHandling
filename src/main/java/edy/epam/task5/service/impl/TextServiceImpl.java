package edy.epam.task5.service.impl;

import edy.epam.task5.comparator.ParagraphComparator;
import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;
import edy.epam.task5.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger();
    private static final String VOWELS = "AEIOUYaeiouy";

    public TextElement sortParagraphs(TextElement textElement)
            throws InfoHandlingException {
        TextElement resultText = new TextComposite(TypeOfElement.TEXT);
        if (textElement.getType() == TypeOfElement.TEXT) {
            ParagraphComparator paragraphComparator = new ParagraphComparator();
            List<TextElement> sortedParagraphs = textElement.getChildren()
                    .stream()
                    .sorted(paragraphComparator)
                    .collect(Collectors.toList());

            resultText.addAll(sortedParagraphs);
        } else {
            throw new InfoHandlingException("Input data in sortParagraphs function is not correct.");
        }
        return resultText;
    }

    public List<TextElement> findSentencesWithLongestWord(TextElement textElement)
            throws InfoHandlingException {
        List<TextElement> resultSentences = new ArrayList<>();
        if (textElement.getType() == TypeOfElement.TEXT) {
            int maxLength = 0;

            List<TextElement> words = textElement.getChildren()
                    .stream()
                    .flatMap(paragraph -> paragraph.getChildren().stream())
                    .flatMap(sentence -> sentence.getChildren().stream())
                    .flatMap(lexeme -> lexeme.getChildren().stream())
                    .collect(Collectors.toList());

            for (TextElement word : words) {
                if (word.getType() == TypeOfElement.WORD) {
                    if (word.getChildren().size() > maxLength) {
                        maxLength = word.getChildren().size();
                    }
                }
            }
            for (TextElement paragraph : textElement.getChildren()) {
                for (TextElement sentence : paragraph.getChildren()) {
                    for (TextElement lexeme : sentence.getChildren()) {
                        for (TextElement word : lexeme.getChildren()) {
                            if (word.getType() == TypeOfElement.WORD && word.getChildren().size() == maxLength) {
                                resultSentences.add(sentence);
                            }
                        }
                    }
                }
            }
        } else {
            logger.error("Input data in findSentencesWithLongestWord function is not correct.");
            throw new InfoHandlingException("Input data in findSentencesWithLongestWord function is not correct.");
        }
        return resultSentences;
    }

    public TextElement deleteSentenceByWordCount(TextElement textElement, int minLength)
            throws InfoHandlingException {
        TextElement resultText = new TextComposite(TypeOfElement.TEXT);
        if (textElement.getType() == TypeOfElement.TEXT) {
            List<TextElement> paragraphs = textElement.getChildren();
            for (TextElement paragraph : paragraphs) {
                TextElement resultParagraph = new TextComposite(TypeOfElement.PARAGRAPH);
                List<TextElement> sentences = paragraph.getChildren();
                for (TextElement sentence : sentences) {
                    List<TextElement> lexemes = sentence.getChildren();
                    if (lexemes.size() >= minLength) {
                        resultParagraph.add(sentence);
                    }
                }
                resultText.add(resultParagraph);
            }
        } else {
            logger.error("Input data in deleteSentenceByWordSize function is not correct.");
            throw new InfoHandlingException("Input data in deleteSentenceByWordSize function is not correct.");
        }
        return resultText;
    }

    public Map<String, Integer> countEqualWords(TextElement textElement)
            throws InfoHandlingException {
        Map<String, Integer> map = new HashMap<>();
        if (textElement.getType() == TypeOfElement.TEXT) {
            List<TextElement> words = textElement.getChildren()
                    .stream()
                    .flatMap(paragraph -> paragraph.getChildren().stream())
                    .flatMap(sentence -> sentence.getChildren().stream())
                    .flatMap(lexeme -> lexeme.getChildren().stream())
                    .collect(Collectors.toList());
            for (TextElement word : words) {
                if (word.getType() == TypeOfElement.WORD) {
                    String key = word.toString().toLowerCase();
                    map.putIfAbsent(key, 0);
                    map.computeIfPresent(key, (keyLocal, value) -> (++value));
                }
            }
        } else {
            logger.error("Input data in countEqualWords function is not correct.");
            throw new InfoHandlingException("Input data in countEqualWords function is not correct.");
        }
        return map;
    }

    public List<Long> countOfVowelsAndConsonants(TextElement textElement)
            throws InfoHandlingException {
        List<Long> resultList = new ArrayList<>();
        long vowelsCount = 0;
        long consonantsCount = 0;
        if (textElement.getType() == TypeOfElement.SENTENCE) {
            List<Character> vowels = new ArrayList<>();
            for (char ch : VOWELS.toCharArray()) {
                vowels.add(ch);
            }
            String workString = textElement.toString();
            for (int i = 0; i <workString.length(); i++) {
                if (Character.isLetter(workString.charAt(i))) {
                    char element = workString.charAt(i);
                    if (vowels.contains(element)) {
                        vowelsCount++;
                    } else {
                        consonantsCount++;
                    }
                }
            }
        } else {
            logger.error("Input data in countOfVowelsAndConsonants function is not correct.");
            throw new InfoHandlingException("Input data in countOfVowelsAndConsonants function is not correct.");
        }
        resultList.add(vowelsCount);
        resultList.add(consonantsCount);
        return resultList;
    }

}

package edy.epam.task5.service;

import edy.epam.task5.entity.TextElement;
import edy.epam.task5.exception.InfoHandlingException;

import java.util.List;
import java.util.Map;

public interface TextService {

    TextElement sortParagraphs(TextElement textElement) throws InfoHandlingException ;

    List<TextElement> findSentencesWithLongestWord(TextElement textElement)  throws InfoHandlingException;

    TextElement deleteSentenceByWordCount(TextElement textElement, int minLength) throws InfoHandlingException;

    Map<String, Integer> countEqualWords(TextElement textElement) throws InfoHandlingException;

    List<Long> countOfVowelsAndConsonants(TextElement textElement) throws InfoHandlingException;
}

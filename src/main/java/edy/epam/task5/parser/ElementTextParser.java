package edy.epam.task5.parser;

import edy.epam.task5.entity.TextElement;
import edy.epam.task5.exception.InfoHandlingException;

public interface ElementTextParser {

    void parse(TextElement textElement, String data) throws InfoHandlingException;
}

package edy.epam.task5.parser;

import edy.epam.task5.entity.Symbol;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;

public class SymbolParser implements ElementTextParser {
    private static final String SYMBOL_REGEX = "";

    @Override
    public void parse(TextElement textElement, String data) throws InfoHandlingException {
        if (data == null || data.isBlank()) {
            throw new InfoHandlingException("Input data in symbol parser is not correct. " + data);
        }
        String[] symbols = data.split(SYMBOL_REGEX);
        for (var symbolValue : symbols) {
            Symbol symbol = new Symbol(symbolValue.charAt(0), TypeOfElement.SYMBOL);
            textElement.add(symbol);
        }
    }
}

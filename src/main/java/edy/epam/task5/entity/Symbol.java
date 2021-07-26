package edy.epam.task5.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;

public class Symbol implements TextElement {

    private static final Logger logger = LogManager.getLogger();
    private char element;
    private TypeOfElement typeOfElement;

    public Symbol(char element, TypeOfElement typeOfElement) {
        this.element = element;
        this.typeOfElement = typeOfElement;
    }

    @Override
    public boolean add(TextElement abstractTextElement) {
        logger.error("Error in method add of Symbol.");
        throw new UnsupportedOperationException("Error in method add of Symbol.");
    }

    @Override
    public boolean addAll(Collection<? extends TextElement> c) {
        logger.error("Error in method addAll of Symbol.");
        throw new UnsupportedOperationException("Error in method addAll of Symbol.");
    }

    @Override
    public boolean remove(TextElement abstractTextElement) {
        logger.error("Error in method remove of Symbol.");
        throw new UnsupportedOperationException("Error in method remove of Symbol.");
    }

    @Override
    public List<TextElement> getChildren() {
        logger.error("Error in method getChildren of Symbol.");
        throw new UnsupportedOperationException("Error in method getChildren of Symbol.");
    }

    @Override
    public TypeOfElement getType() {
        return typeOfElement;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Symbol symbol = (Symbol) obj;
        return element == symbol.element
                && this.typeOfElement.equals(symbol.getType());
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = result * prime + (int)element;
        result = result * prime + typeOfElement.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return Character.toString(element);
    }
}

package edy.epam.task5.entity;

import java.util.Collection;
import java.util.List;

public interface TextElement {

    boolean add(TextElement abstractTextElement);
    boolean addAll(Collection<? extends TextElement> c);
    boolean remove(TextElement abstractTextElement);
    List<TextElement> getChildren();
    TypeOfElement getType();
}

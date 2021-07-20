package edy.epam.task5.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextComposite implements TextElement {
    private List<TextElement> components = new ArrayList<>();
    private TypeOfElement typeOfElement;

    public TextComposite(TypeOfElement typeOfElement) {
        this.typeOfElement = typeOfElement;
    }

    public TextComposite(List<TextElement> components, TypeOfElement typeOfElement) {
        this.components = components;
        this.typeOfElement = typeOfElement;
    }

    @Override
    public boolean add(TextElement item) {
        return components.add(item);
    }

    public boolean addAll(Collection<? extends TextElement> c) {
        return components.addAll(c);
    }

    @Override
    public boolean remove(TextElement item) {
        return components.remove(item);
    }

    @Override
    public List<TextElement> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public TypeOfElement getType() {
        return this.typeOfElement;
    }


    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        result = prime * result + components.hashCode();
        result = prime * result + getType().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextComposite textComposite = (TextComposite) obj;
        return components.equals(textComposite.components)
                && this.getType().equals(textComposite.getType());
    }

    @Override
    public String toString() {
        String paragraphPrefix = "    ";
        StringBuilder stringBuilder = new StringBuilder();
        String delimiter = typeOfElement.getDelimiter();
        if (components.size() >= 1) {
            for (int i = 0; i < components.size() - 1; i++) {
                if (typeOfElement == TypeOfElement.TEXT) {
                    stringBuilder.append(paragraphPrefix);
                }
                stringBuilder.append(components.get(i).toString()).append(delimiter);
            }
            if (typeOfElement == TypeOfElement.TEXT) {
                stringBuilder.append(paragraphPrefix);
            }
            stringBuilder.append(components.get(components.size() - 1).toString());
        }
        return stringBuilder.toString();
    }
}

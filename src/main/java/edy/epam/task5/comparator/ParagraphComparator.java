package edy.epam.task5.comparator;

import edy.epam.task5.entity.TextElement;

import java.util.Comparator;

public class ParagraphComparator implements Comparator<TextElement> {

    @Override
    public int compare(TextElement first, TextElement second) {
        long firstCount = first.getChildren().size();
        long secondCount = second.getChildren().size();
        return Long.compare(firstCount, secondCount);
    }
}

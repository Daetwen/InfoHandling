package edy.epam.task5.parser;

import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;
import edy.epam.task5.reader.TextReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class ElementTextParserTest {

    private static final String FILE_PATH1 = "src/test/resources/info1.txt";
    private static final String FILE_PATH2 = "src/test/resources/info2.txt";
    private final ElementTextParser parser = new ParagraphParser();
    private final TextReader textReader = new TextReader();
    private String text1;
    private String text2;

    @BeforeClass
    public void setUp() throws InfoHandlingException {
        text1 = textReader.readText(FILE_PATH1);
        text2 = textReader.readText(FILE_PATH2);
    }

    @Test
    public void parsePositiveTest1() throws InfoHandlingException {
        String expected = text1;
        TextComposite composite = new TextComposite(TypeOfElement.TEXT);
        parser.parse(composite, text1);
        String actual = composite.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void parsePositiveTest2() throws InfoHandlingException {
        String expected = text2;
        TextComposite composite = new TextComposite(TypeOfElement.TEXT);
        parser.parse(composite, text2);
        String actual = composite.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void parseNegativeTest() {
        TextComposite composite = new TextComposite(TypeOfElement.PARAGRAPH);
        assertThrows(InfoHandlingException.class, () -> parser.parse(composite, ""));
    }
}

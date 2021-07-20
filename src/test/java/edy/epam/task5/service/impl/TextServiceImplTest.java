package edy.epam.task5.service.impl;

import edy.epam.task5.entity.TextComposite;
import edy.epam.task5.entity.TextElement;
import edy.epam.task5.entity.TypeOfElement;
import edy.epam.task5.exception.InfoHandlingException;
import edy.epam.task5.parser.ElementTextParser;
import edy.epam.task5.parser.LexemeParser;
import edy.epam.task5.parser.ParagraphParser;
import edy.epam.task5.reader.TextReader;
import edy.epam.task5.service.TextService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TextServiceImplTest {

    private static final String FILE_PATH1 = "src/test/resources/info1.txt";
    private static final String FILE_PATH2 = "src/test/resources/info2.txt";
    private static final String FILE_PATH3 = "src/test/resources/info3.txt";
    private static final String FILE_PATH4 = "src/test/resources/info4.txt";
    private final ElementTextParser parser = new ParagraphParser();
    private final TextReader textReader = new TextReader();
    private final TextService textService = new TextServiceImpl();
    private String text1;
    private String text2;
    private String text3;
    private String text4;

    @BeforeClass
    public void setUp() throws InfoHandlingException {
        text1 = textReader.readText(FILE_PATH1);
        text2 = textReader.readText(FILE_PATH2);
        text3 = textReader.readText(FILE_PATH3);
        text4 = textReader.readText(FILE_PATH4);
    }

    @DataProvider(name = "sortParagraphs-provider")
    public Object[][] sortParagraphsProvider() {
        return new Object[][] {
                {text4, text1},
                {text3, text2},
        };
    }

    @Test(dataProvider = "sortParagraphs-provider")
    public void sortParagraphsPositiveTest(String expected, String textForWork) throws InfoHandlingException {
        TextComposite composite = new TextComposite(TypeOfElement.TEXT);
        parser.parse(composite, textForWork);
        String actual = textService.sortParagraphs(composite).toString();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "findSentencesWithLongestWord-provider")
    public Object[][] findSentencesWithLongestWordProvider() {
        String sentence1 = "It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        String sentence2 = "The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here) 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1), content here's, making it look like readable English?";
        return new Object[][] {
                {sentence1, text1},
                {sentence2, text2},
        };
    }

    @Test(dataProvider = "findSentencesWithLongestWord-provider")
    public void findSentencesWithLongestWordPositiveTest(String expected, String textForWork)
            throws InfoHandlingException {
        TextComposite composite = new TextComposite(TypeOfElement.TEXT);
        parser.parse(composite, textForWork);
        List<TextElement> result = textService.findSentencesWithLongestWord(composite);
        String actual = result.get(0).toString();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "deleteSentenceByWordCount-provider")
    public Object[][] deleteSentenceByWordCountProvider() {
        String text1expected = "    Bc febd java web development.";
        String text2expected = "    Bc febd (7^5|1&2<<(2|5>>2&71))|1200 java web development. How are your heals?";
        String text1 = "    Bc febd java web development. Adf hello world. How are your heals?";
        String text2 = "    Bc febd (7^5|1&2<<(2|5>>2&71))|1200 java web development. Adf hello world. How are your heals? some sentence.";
        return new Object[][] {
                {text1expected, text1, 5},
                {text2expected, text2, 4},
        };
    }

    @Test(dataProvider = "deleteSentenceByWordCount-provider")
    public void deleteSentenceByWordCountPositiveTest(String expected, String textForWork, int minLength)
            throws InfoHandlingException {
        TextComposite composite = new TextComposite(TypeOfElement.TEXT);
        parser.parse(composite, textForWork);
        String actual = textService.deleteSentenceByWordCount(composite, minLength).toString();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "countOfVowels-provider")
    public Object[][] countOfVowelsProvider() {
        String text1 = "    Bc febd java web development.";
        String text2 = "    Bc febd (7^5|1&2<<(2|5>>2&71))|1200 java web  hello development.";
        return new Object[][] {
                {8, text1},
                {10, text2},
        };
    }

    @DataProvider(name = "countOfConsonants-provider")
    public Object[][] countOfConsonantsProvider() {
        String text1 = "    Bc febd java web development.";
        String text2 = "    Bc febd (7^5|1&2<<(2|5>>2&71))|1200 java web  hello development.";
        return new Object[][] {
                {16, text1},
                {19, text2}
        };
    }

    @Test(dataProvider = "countOfVowels-provider")
    public void countOfVowelsPositiveTest(int expected, String textForWork)
            throws InfoHandlingException {
        TextComposite composite = new TextComposite(TypeOfElement.SENTENCE);
        ElementTextParser parser = new LexemeParser();
        parser.parse(composite, textForWork);
        long actual = textService.countOfVowelsAndConsonants(composite).get(0);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "countOfConsonants-provider")
    public void countOfConsonantsPositiveTest(int expected, String textForWork)
            throws InfoHandlingException {
        TextComposite composite = new TextComposite(TypeOfElement.SENTENCE);
        ElementTextParser parser = new LexemeParser();
        parser.parse(composite, textForWork);
        long actual = textService.countOfVowelsAndConsonants(composite).get(1);
        Assert.assertEquals(actual, expected);
    }
}

import org.junit.Assert;
import org.junit.Test;
import ru.edu.lecture3.FileAnalyser;
import ru.edu.lecture3.FileAnalyserImpl;

import java.io.FileNotFoundException;

public class FileAnalyserTests {

    private FileAnalyserImpl analyser = new FileAnalyserImpl("C:/Users/Aleksei/Desktop/Sber/input.txt");


    @Test
    public void fileAnalyserTest() {

        Assert.assertNotNull(analyser);

        try {
            FileAnalyserImpl analyser2 = new FileAnalyserImpl("C:/Users/Aleksei/Desktop/Sber/input.txt");
            analyser2.saveSummary("C:/Users/Aleksei/Desktop/Sber/input.txt");
        } catch (Exception ex) {
            Assert.assertEquals("Неверно введены данные", ex.getMessage());
        }

        try {
            analyser.saveSummary(null);
            Assert.fail("Файл не найден");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("Файл не найден", ex.getMessage());
        }
    }

    @Test
    public void testGetFileName() {

        Assert.assertEquals("input.txt", analyser.getFileName());
    }

    @Test
    public void testGetRowsCount() {
        try {
            FileAnalyserImpl analyser2 = new FileAnalyserImpl("C:/Users/Aleksei/Desktop/Sber/input.txt");
            analyser2.saveSummary("C:/Users/Aleksei/Desktop/Sber/input.txt");
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals(3, ex.getMessage());
        }
    }

}
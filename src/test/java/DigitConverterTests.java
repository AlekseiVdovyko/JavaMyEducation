import org.junit.Assert;
import org.junit.Test;
import ru.edu.lesson1.DigitConverterImpl;

public class DigitConverterTests {

    DigitConverterImpl converter = new DigitConverterImpl();

    //private DigitConverter converter = null;

    @Test
    public void intConverterTest() {

        Assert.assertNotNull(converter);

        Assert.assertEquals("101", converter.convert(5, 2));
        Assert.assertEquals("12", converter.convert(5, 3));
        Assert.assertEquals("10", converter.convert(5, 5));
        Assert.assertEquals("0", converter.convert(0, 2));
    }

    @Test
    public void doubleConverterTest() {

        Assert.assertNotNull(converter);

        Assert.assertEquals("0.10000", converter.convert(0.5, 2, 5));
        Assert.assertEquals("0.01000", converter.convert(0.25, 2, 5));
    }

    @Test
    public void intNullConverterTest() {

        try {
            converter.convert(-5,2);
            Assert.fail();
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Ok");
        }

        try {
            converter.convert(5,-2);
            Assert.fail();
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Ok");
        }
    }

    @Test
    public void doubleMinusConverterTest() {

        Assert.assertNotNull(converter);

        try {
            converter.convert(-0.5,2,5);
            Assert.fail();
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Ok");
        }

        try {
            converter.convert(0.5,-2,5);
            Assert.fail();
        }
        catch(IllegalArgumentException ex) {
            System.out.println("Ok");
        }
    }
}

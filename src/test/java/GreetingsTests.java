import org.junit.Assert;
import org.junit.Test;
import ru.edu.lesson1.GreetingImpl;
import ru.edu.lesson1.Hobby;

import java.util.Collection;

public class GreetingsTests {

    GreetingImpl greeting = new GreetingImpl();

    //private IGreeting greeting = null;

    @Test
    public void size_Test() {

        Assert.assertNotNull(greeting);

        // check your methods

        Collection<Hobby> hobbys = greeting.getHobbies();

        Assert.assertNotNull(hobbys);
        Assert.assertEquals(1, hobbys.size());

    }
}

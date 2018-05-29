package victoralbertos.io.android;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);


    }


    @Test public void testSerializable() throws Exception {

        GsonTypeToken gsonTypeToken = new GsonTypeToken<BaseResult<List<HomeBannerBean>>>() {
        };


        System.out.println("type  " + gsonTypeToken.getType());
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(
                new File("Customer.txt")));
        oo.writeObject(gsonTypeToken);
        System.out.println("Customer对象序列化成功！");
        oo.close();

    }
}
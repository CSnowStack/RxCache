package victoralbertos.io.android;

import org.json.JSONObject;
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

    private String result="{\n" +
            "    \"error\":0,\n" +
            "    \"msg\":\"请求成功\",\n" +
            "    \"data\":[\n" +
            "        {\n" +
            "            \"id\":\"2282\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"500\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"4\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526805392\",\n" +
            "            \"create_time\":\"1526805392\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"539\",\n" +
            "            \"trade_state\":\"SUCCESS\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"2198\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"5000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526280496\",\n" +
            "            \"create_time\":\"1526280490\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"514\",\n" +
            "            \"trade_state\":\"SUCCESS\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"2195\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"10000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526280153\",\n" +
            "            \"create_time\":\"1526280146\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"513\",\n" +
            "            \"trade_state\":\"SUCCESS\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1999\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"300\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205394\",\n" +
            "            \"create_time\":\"1526205385\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"510\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1996\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"10000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205377\",\n" +
            "            \"create_time\":\"1526205370\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"509\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1994\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"10000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205362\",\n" +
            "            \"create_time\":\"1526205356\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"508\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1993\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"500\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205338\",\n" +
            "            \"create_time\":\"1526205332\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"507\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1991\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"10000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205323\",\n" +
            "            \"create_time\":\"1526205316\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"506\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1989\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"1000\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205302\",\n" +
            "            \"create_time\":\"1526205295\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"505\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":\"1988\",\n" +
            "            \"uid\":\"34\",\n" +
            "            \"trade_type\":\"2\",\n" +
            "            \"amount\":\"9900\",\n" +
            "            \"bussiness_type\":\"3\",\n" +
            "            \"pay_type\":\"1\",\n" +
            "            \"status\":\"1\",\n" +
            "            \"pay_time\":\"1526205288\",\n" +
            "            \"create_time\":\"1526205281\",\n" +
            "            \"out_trade_no\":\"\",\n" +
            "            \"use_way\":\"购买模型\",\n" +
            "            \"order_id\":\"504\",\n" +
            "            \"trade_state\":\" \"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Test public void testTime() throws Exception{

        long time=System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            JSONObject jsonObject=new JSONObject(result);
            if("1".equals(jsonObject.getString("error"))){
            }
        }

        System.out.println("time   "+(System.currentTimeMillis()-time));

    }
}
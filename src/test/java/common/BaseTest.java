package common;

import com.alibaba.fastjson.JSONObject;
import data.Environment;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import pojo.CasePojo;
import utils.Constants;

import java.util.Map;
import java.util.Set;

import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;


/**
 * @Author: SNAYi
 * @Date: 2022/2/8 12:18
 */
public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        // 把实际响应结果中返回值类型为小数的配置为BigDecimal类型
        RestAssured.config= RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        RestAssured.baseURI = Constants.BASE_URI;

    }

    /**
     *  响应结果断言
     * @param res 实际响应结果
     * @param CasePojo 预期响应结果所在的pojo
     */
    public void responseAssert(Response res, CasePojo CasePojo) {
        //获取预期响应
        Map<String,Object> expected = JSONObject.parseObject(CasePojo.getExpected());
        for (String key:expected.keySet()) {
            Object actualResult = res.jsonPath().get(key);
            System.out.println("实际结果："+actualResult+actualResult.getClass());
            Object expectedResult = expected.get(key);
            System.out.println("预期结果："+expectedResult+expectedResult.getClass());
            Assert.assertEquals(actualResult,expectedResult);
        }
    }

    /**
     *  接口请求方法封装
     * @param CasePojo 请求数据所在的pojo
     * @return 实际响应结果
     */
    public Response request(CasePojo CasePojo) {
        //获取接口请求头(需要json字符串转Map)
        Map rHeaderMap = JSONObject.parseObject(CasePojo.getRequestHeader());
        //获取接口请求地址
        String url = CasePojo.getUrl();
        //获取请求参数
        String params = CasePojo.getInputParams();
        //获取请求方式
        String method = CasePojo.getMethod();
        Response res = null;
        if ("post".equalsIgnoreCase(method)) {
//            System.out.println(url);
//            res = RestAssured.given().headers(rHeaderMap).body(params).when().post("http://api.lemonban.com/futureloan"+url).then().log().all().extract().response();
            res = RestAssured.given().headers(rHeaderMap).body(params).when().post(url).then().log().all().extract().response();
        } else if("get".equalsIgnoreCase(method)) {
            res = RestAssured.given().headers(rHeaderMap).body(params).when().get(url).then().log().all().extract().response();

        }
        return res;
    }

    /**
     *  通过【提取表达式】将响应结果存储到环境变量中
     * @param response 实际响应结果
     * @param casePojo  excel对应的实体类
     */
    public void extractToEvnironment(Response response,CasePojo casePojo) {
        String extractExper = casePojo.getExtractExper();
        //将提取表达式转化成map 其中提取表达式的key是可被直接使用在环境变量中的key;value是用来当做jsonpath提取实际响应结果的
        Map<String,Object> map = JSONObject.parseObject(extractExper);
        Set<String> keys = map.keySet();
        for(String key : keys) {
            Object evnValue = response.jsonPath().get((String) map.get(key));
            Environment.evnMap.put(key,evnValue);
        }


    }
}

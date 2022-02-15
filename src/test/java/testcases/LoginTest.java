package testcases;


import common.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.CasePojo;
import utils.ExcelUtil;
import java.util.List;


/**
 * @Author: SNAYi
 * @Date: 2022/2/8 12:30
 */
public class LoginTest extends BaseTest {
    @BeforeTest
    public void beforeLogin() {
        List<CasePojo> list = ExcelUtil.readSpecifyExcelDatas(2, 1, 1);
        Response response = request(list.get(0));
        System.out.println(response.getBody());
    }

    @Test(dataProvider= "getLoginDatas")
    public void login(CasePojo casePojo) {
        responseAssert(request(casePojo),casePojo);

    }

    @DataProvider
    public Object[] getLoginDatas() {
        List<CasePojo> pojoList = ExcelUtil.readSpecifyExcelDatas(2,2);
        return pojoList.toArray();
    }

}


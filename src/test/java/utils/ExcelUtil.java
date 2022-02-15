package utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import pojo.CasePojo;

import java.io.File;
import java.util.List;

import static utils.Constants.EXCEL_PATH;

/**
 * @Author: SNAYi
 * @Date: 2022/2/8 12:17
 */
public class ExcelUtil {
    /**
     * 读取Excel指定sheet的所有数据
     * @return list集合
     */
    public static List<CasePojo> readExcelSheetAllDatas(int sheetIndex){
        //从Excel里面读取到的文件
        File file = new File(EXCEL_PATH);
        //读取/导入Excel的一些参数配置
        ImportParams importParams = new ImportParams();
        //设置读取第几个sheet
        importParams.setStartSheetIndex(sheetIndex-1);
        //读取Excel里面的数据（Easy POI）
        List<CasePojo> listDatas =  ExcelImportUtil.importExcel(file,CasePojo.class,importParams);
        return listDatas;
    }

    /**
     *  读取excel指定sheet的指定行数据(从第几行开始)
     * @param sheetIndex 需要读取的sheet
     * @param startRow  从第几行开始读
     * @param readRows  总共读几行
     * @return
     */
    public static List<CasePojo> readSpecifyExcelDatas(int sheetIndex,int startRow,int readRows){
        //从Excel里面读取到的文件
        File file = new File(EXCEL_PATH);
        //读取/导入Excel的一些参数配置
        ImportParams importParams = new ImportParams();
        //设置读取第几个sheet
        importParams.setStartSheetIndex(sheetIndex-1);
        //从第几行开始
        importParams.setStartRows(startRow-1);
        //总共读几行
        importParams.setReadRows(readRows);
        //读取Excel里面的数据（Easy POI）
        List<CasePojo> listDatas =  ExcelImportUtil.importExcel(file,CasePojo.class,importParams);
        return listDatas;
    }

    /**
     *  读取excel指定sheet的指定行数据(从某行开始到sheet结束)
     * @param sheetIndex 需要读取的sheet
     * @param startRow 从第几行开始读
     * @return
     */
    public static List<CasePojo> readSpecifyExcelDatas(int sheetIndex,int startRow){
        //从Excel里面读取到的文件
        File file = new File(EXCEL_PATH);
        //读取/导入Excel的一些参数配置
        ImportParams importParams = new ImportParams();
        //设置读取第几个sheet
        importParams.setStartSheetIndex(sheetIndex-1);
        //从第几行开始
        importParams.setStartRows(startRow-1);
        //读取Excel里面的数据（Easy POI）
        List<CasePojo> listDatas =  ExcelImportUtil.importExcel(file,CasePojo.class,importParams);
        return listDatas;
    }
}

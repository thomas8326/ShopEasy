package cn.picksomething.drawlayouttest.Databases;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tt791_000 on 2016/7/28.
 */
public class TestDatabaseHelper {
    private String DB_PATH;
    private final String DB_NAME="sd1.db";
    private final Context context;
    private SQLiteDatabase myDataBase;
    private String PACKAGE_NAME = "cn.picksomething.drawlayouttest/";
    public static  final  String TABLE_NAME2 = "Mission_cook_health";
    public static  final  String TABLE_NAME1 = "Point_cook";
    public static  final  String TABLE_NAME3 = "Mission_cook_party";
    public static  final  String TABLE_NAME4 = "Mission_cook_bodycare";
    public static  final  String TABLE_NAME5 = "Mission_cook_cookhealth";
    public static  final  String TABLE_NAME6 = "Mission_cook_exercise";
    public static  final  String TABLE_NAME7 = "Mission_cook_holiday";
    public static  final  String TABLE_NAME8= "all";
    public static  final  String TABLE_NAME9 = "chinese";
    public static  final  String TABLE_NAME10 = "foreign";
    public static  final  String TABLE_NAME11 = "bread";
    public static  final  String TABLE_NAME12 = "dessert";
    public static  final  String TABLE_NAME13 = "Point_cook_new";
    public static  final  String TABLE_NAME14 = "Mission_cook_fourweek";
    public static  final  String TABLE_NAME15 = "Point_project";
    public static  final  String TABLE_NAME16 = "Mission_cook_toghter";

    public TestDatabaseHelper(Context context){
        this.context= context;
        this.DB_PATH= "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/"
                + PACKAGE_NAME ;
        if(checkDbExists()==false)
            try {
                this.CopyDB();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private Boolean checkDbExists(){
        return new File(this.DB_PATH+this.DB_NAME).exists();
    }

    public void CopyDB() throws IOException {
        AssetManager assetManager = this.context.getAssets();
        InputStream in = assetManager.open("sd1.db");
        OutputStream out = new FileOutputStream(this.DB_PATH+this.DB_NAME);
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
        in.close();
        out.flush();
        out.close();
    }

    public void OpenDB() throws SQLException {
        this.myDataBase = SQLiteDatabase.openDatabase(this.DB_PATH+this.DB_NAME
                , null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void CloseDB() {
        if(myDataBase != null)
            myDataBase.close();

    }
    public Cursor SelectMissionProject(String col){
        Cursor cursor = this.myDataBase.rawQuery("SELECT * From Mission_project where _part = "+"'"+ col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectMissionProject2(String col){
        Cursor cursor = this.myDataBase.rawQuery("SELECT * From Mission_project_holiday where _itemPart = "+"'"+ col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectPointCook(String col){
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName From "+"'"+TABLE_NAME13+"'"+" where _ingredientClass = "+"'"+ col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor Select(String col){
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19 From Point_cook where _cookmealpart = "+"'"+ col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getPosition(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19 FROM "+ TABLE_NAME1 +"  where _cookmealpart = "+"'"+ col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getClassName(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19 FROM "+ TABLE_NAME1 +"  where _cookmealpart = "+"'"+ col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIDPC(int col2,String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from Point_cook where _cookmealpartid = "+"'"+col2+"'" +" and _cookmealpart = "+"'"+col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectSearch()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_cookmealpartid, _cookmealpart from Point_cook "
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDo(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName, _foodDo from Point_cook where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIG(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id , _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6, _ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19 from Point_cook where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19 FROM "+ TABLE_NAME1 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19 FROM "+ TABLE_NAME1 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //進補資料
    public Cursor getHealthList(String hp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 FROM "+ TABLE_NAME2 +" where _healthPart = " +"'"+ hp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getHealthListPosition(String hp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME2 +" where _healthPart = " +"'"+ hp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getHealthListClassName(String hp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM "+ TABLE_NAME2 +" where _healthPart = " +"'"+ hp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    //舉辦活動資料
    public Cursor getPartyList(String pi)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26 FROM "+ TABLE_NAME3 +" where _partyPart = " +"'"+ pi+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getPartyListPosition(String pi)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26 FROM "+ TABLE_NAME3 +" where _partyPart = " +"'"+ pi+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getPartyListClassName(String pi)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26 FROM "+ TABLE_NAME3 +" where _partyPart = " +"'"+ pi+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //坐月子
    public Cursor getFourWeek(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 FROM "+ TABLE_NAME14 + " where _healthPart = " + "'"+col+"'" , null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getFourWeekPosition(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8 FROM "+ TABLE_NAME14 + " where _healthPart = " + "'"+col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getFourWeekClassName(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8 FROM "+ TABLE_NAME14 + " where _healthPart = " + "'"+col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //保健身體資料
    public Cursor getBodyCareList(String bp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20 FROM "+ TABLE_NAME4 +" where substr(_bodycarePart,1,2) = " +"'"+ bp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getBodycarePosition(String bp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20 FROM "+ TABLE_NAME4 +" where substr(_bodycarePart,1,2) = " +"'"+ bp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getBodycareClassName(String bp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20 FROM "+ TABLE_NAME4 +" where substr(_bodycarePart,1,2) = " +"'"+ bp+"'", null);        cursor.moveToFirst();
        return cursor;
    }
    public void runSQL(String sql){
        this.myDataBase.execSQL(sql);
    }

    //派對
    public  Cursor SelectID(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME3+" where _partyid = "+"'"+col1+"'"+" and _partyPart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    //派對 菜單
    public  Cursor SelectID2(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME3+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoLastPage(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME3+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGLastPage(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26 from "+TABLE_NAME3+" where _id =  "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getLastPagePosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26 FROM "+ TABLE_NAME3 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor geLastPageClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26 FROM "+ TABLE_NAME3 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //進補
    public  Cursor SelectIDHealth(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME2+" where _healthid = "+"'"+col1+"'"+" and _healthPart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoHealth(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME2+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGHealth(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 from "+TABLE_NAME2+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getHealthPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME2 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getHealthClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM "+ TABLE_NAME2 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    //強身健體
    public  Cursor SelectIDBodycare(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME4+" where _bodycareid = "+"'"+col1+"'"+" and substr(_bodycarePart,1,2) = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoBodycare(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME4+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGBodycare(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 ,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20 from "+TABLE_NAME4+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGBodycarePosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20 FROM "+ TABLE_NAME4 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGBodycareClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20 FROM "+ TABLE_NAME4 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    //健康飲食資料
    public Cursor getCookhealth(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19 FROM "+ TABLE_NAME5 +" where _cookhealthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getCookhealthPosition(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19 FROM "+ TABLE_NAME5 +" where _cookhealthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getCookhealthClassName(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19 FROM "+ TABLE_NAME5 +" where _cookhealthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public  Cursor SelectIDCookhealth(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME5+" where _cookhealthid = "+"'"+col1+"'"+" and _cookhealthPart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoCookhealth(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME5+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGCookhealth(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 ,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19 from "+TABLE_NAME5+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGCookhealthPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19 FROM "+ TABLE_NAME5 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGCookhealthClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19 FROM " + TABLE_NAME5 + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }

        //健身減重
    public Cursor getExercise(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 FROM "+ TABLE_NAME6 +" where _healthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getExercisePosition(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME6 +" where _healthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getExerciseClassName(String ch)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM "+ TABLE_NAME6 +" where _healthPart = " +"'"+ ch+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public  Cursor SelectIDExercise(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME6+" where _healthid = "+"'"+col1+"'"+" and _healthPart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoExercise(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME6+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGExercise(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 ,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 from "+TABLE_NAME6+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGExercisePosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME6 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGExerciseClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM "+ TABLE_NAME6 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //節慶
    public Cursor getFestival(String fp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26,_ingredient27,_ingredient28,_ingredient29,_ingredient30,_ingredient31,_ingredient32,_ingredient33 FROM "+ TABLE_NAME7 +" where _festivalpart = " +"'"+ fp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getFestivalPosition(String fp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26,_ingredientName27,_ingredientName28,_ingredientName29,_ingredientName30,_ingredientName31,_ingredientName32,_ingredientName33 FROM "+ TABLE_NAME7 +" where _festivalpart = " +"'"+ fp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getFestivalClassName(String fp)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26,_ingredientClass27,_ingredientClass28,_ingredientClass29,_ingredientClass30,_ingredientClass31,_ingredientClass32,_ingredientClass33 FROM "+ TABLE_NAME7 +" where _festivalpart = " +"'"+ fp+"'", null);
        cursor.moveToFirst();
        return cursor;
    }

    public  Cursor SelectIDFestival(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME7+" where _festivalpartid = "+"'"+col1+"'"+" and _festivalpart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectDoFestival(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME7+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGFestival(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 ,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 from "+TABLE_NAME7+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGFestivalPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME7 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGFestivalClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM "+ TABLE_NAME7 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //中式食譜
    public Cursor SelectChinesePath()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME9,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectChinesePath(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME9 +" where _chinesefoodpart = "+"'"+col+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectallPathBck(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _imgPath from "+"'"+TABLE_NAME8+"'" +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectChinesePathBck(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _imgPath from "+TABLE_NAME9 +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectForeignPathBck(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _imgPath from "+"'"+TABLE_NAME10 +"'" +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectBreadPathBck(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _imgPath from "+TABLE_NAME11 +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDessertPathBck(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _imgPath from "+TABLE_NAME12 +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectDoChinese(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME9 +" where _chinesefoodid = "+"'"+col1+"'" +" and _chinesefoodpart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoChineseall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME9 +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGChinese(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15 from "+TABLE_NAME9 +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGChinesePosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15 FROM "+ TABLE_NAME9 +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGChineseClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15 FROM " + TABLE_NAME9 + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }


    public Cursor SelectallPath()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+"'"+TABLE_NAME8 +"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+"'"+TABLE_NAME8 +"'"+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14  from "+"'"+TABLE_NAME8 +"'" +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGallPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14 FROM "+"'"+TABLE_NAME8 +"'" +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGallClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14 FROM " +"'"+TABLE_NAME8 +"'" + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectforeignPath()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+"'"+TABLE_NAME10 +"'" ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectforeignPath(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+"'"+TABLE_NAME10 +"'" +" where _Foreigncountrypart = "+"'"+col+"'",null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectDoforeign(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+"'"+TABLE_NAME10 +"'" +" where _Foreigncountrypartid = "+"'"+col1+"'" +" and _Foreigncountrypart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDoforeignall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+"'"+TABLE_NAME10 +"'" +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGforeign(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26,_ingredient27,_ingredient28,_ingredient29,_ingredient30,_ingredient31,_ingredient32,_ingredient33  from "+"'"+TABLE_NAME10 +"'" +" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGforeignPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26,_ingredientName27,_ingredientName28,_ingredientName29,_ingredientName30,_ingredientName31,_ingredientName32,_ingredientName33 FROM "+"'"+TABLE_NAME10 +"'" +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGforeignClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26,_ingredientClass27,_ingredientClass28,_ingredientClass29,_ingredientClass30,_ingredientClass31,_ingredientClass32,_ingredientClass33 FROM " +"'"+TABLE_NAME10 +"'" + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectbreadPath()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME11,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectbreadPath(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME11 +" where _Bakingpart = "+"'"+col+"'",null);
        cursor.moveToFirst();
        return cursor;
    }


    public Cursor SelectDobread(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME11 +" where _Bakingpartid = "+"'"+col1+"'" +" and _Bakingpart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDobreadall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME11+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGbread(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22  from "+TABLE_NAME11+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGbreadPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22 FROM "+"'"+TABLE_NAME11 +"'" +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGbreadClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22 FROM " +"'"+TABLE_NAME11 +"'" + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectdessertPath()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME12,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectdessertPath(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_imgPath from "+TABLE_NAME12 +" where _dessertpart = "+"'"+col+"'",null);
        cursor.moveToFirst();
        return cursor;
    }



    public Cursor SelectDodessert(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME12 +" where _dessertpartid = "+"'"+col1+"'" +" and _dessertpart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectDodessertall(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_foodName,_foodDo from "+TABLE_NAME12+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGdessert(int col1)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT  _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25  from "+TABLE_NAME12+" where _id = "+"'"+col1+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGdessertPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25 FROM "+"'"+TABLE_NAME12 +"'" +" where _id = " +"'"+ col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGdessertClassName(int col2) {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientName23,_ingredientName24,_ingredientName25 FROM " +"'"+TABLE_NAME12 +"'" + " where _id = " + "'" + col2 + "'", null);
        cursor.moveToFirst();
        return cursor;
    }


    public Cursor SelectCookbookSearchChinese()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_chinesefoodid, _chinesefoodpart from "+ TABLE_NAME9
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectCookbookSearchForeign()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_Foreigncountrypartid, _Foreigncountrypart from "+"'"+TABLE_NAME10 +"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectCookbookSearchBread()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_Bakingpartid, _Bakingpart from "+ TABLE_NAME11
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectCookbookSearchDessert()
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_dessertpartid, _dessertpart from "+ TABLE_NAME12
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    //坐月子 名稱 做法  食材
    public  Cursor SelectIDFourweek(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME14+" where _healthid = "+"'"+col1+"'"+" and _healthPart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectDoFourweek(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME14+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGFourweek(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id, _ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8 from "+TABLE_NAME14+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGFourWeekPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8 FROM "+ TABLE_NAME14 + " where _id = " + "'"+col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGFourWeekClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8 FROM "+ TABLE_NAME14 + " where _id = " + "'"+col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    //指定型食材 資料
    public Cursor SelectPointProject(String col){
        Cursor cursor = this.myDataBase.rawQuery("SELECT * From "+TABLE_NAME15+" where _itemPart = "+"'"+ col+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    //舉辦活動聚餐
    public Cursor SelectTogether(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26 FROM "+ TABLE_NAME16 + " where _cuisinePart = " + "'"+col+"'" , null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getTogetherPosition(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26 FROM "+ TABLE_NAME16 + " where _cuisinePart = " + "'"+col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getTogetherClassName(String col)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26 FROM "+ TABLE_NAME16 + " where _cuisinePart = " + "'"+col+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public  Cursor SelectIDTogether(int col1,String col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id from "+TABLE_NAME16+" where _cuisineid = "+"'"+col1+"'"+" and _cuisinePart = "+"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor SelectDoTogether(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _foodName,_foodDo from "+TABLE_NAME16+" where _id = " +"'"+col2+"'",null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor SelectIGTogether(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT   _id,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26 from "+TABLE_NAME16+" where _id = "+"'"+col2+"'"
                ,null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGTogetherPosition(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26 FROM "+ TABLE_NAME16 + " where _id = " + "'"+col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor getIGTogetherClassName(int col2)
    {
        Cursor cursor = this.myDataBase.rawQuery("SELECT _id,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26 FROM "+ TABLE_NAME16 + " where _id = " + "'"+col2+"'", null);
        cursor.moveToFirst();
        return cursor;
    }
}




package cn.picksomething.drawlayouttest.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tt791_000 on 2016/7/26.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Drawerloyout.db";
    public static final String TABLE_NAME = "favorite_data";
    public static final String TABLE_NAME2 = "shoppintlist_item"; //菜餚排序
    public static final String TABLE_NAME3 = "shoppintlist_item2";//品項排序
    public static final String TABLE_NAME4 = "history";
    public static final String COL1 = "_id";
    public static final String COL3 = "ITEM_NAME";
    public static final String COL4 = "FoodName";
    public static final String COL5 = "Classify";
    public static final String COL6 = "Foodid";
    public static final String COL7 = "Foodpart";
    public static final String COL8 = "Part";
    public static final String COL9 = "ITEM_CLASS";
    public static final String COL10 = "_ingredientClass1";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE if not exists " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "  + "ITEM_NAME TEXT,Classify TEXT,Foodid INTEGER,Foodpart TEXT,Part TEXT,_ingredient1,_ingredientName1,_ingredientClass1,_ingredient2,_ingredientName2,_ingredientClass2,_ingredient3,_ingredientName3,_ingredientClass3,_ingredient4,_ingredientName4,_ingredientClass4,_ingredient5,_ingredientName5,_ingredientClass5,_ingredient6,_ingredientName6,_ingredientClass6,_ingredient7,_ingredientName7,_ingredientClass7,_ingredient8,_ingredientName8,_ingredientClass8,_ingredient9,_ingredientName9,_ingredientClass9,_ingredient10,_ingredientName10,_ingredientClass10,_ingredient11,_ingredientName11,_ingredientClass11,_ingredient12,_ingredientName12,_ingredientClass12,_ingredient13,_ingredientName13,_ingredientClass13,_ingredient14,_ingredientName14,_ingredientClass14,_ingredient15,_ingredientName15,_ingredientClass15" +
                ",_ingredient16,_ingredientName16,_ingredientClass16,_ingredient17,_ingredientName17,_ingredientClass17,_ingredient18,_ingredientName18,_ingredientClass18,_ingredient19,_ingredientName19,_ingredientClass19,_ingredient20,_ingredientName20,_ingredientClass20,_ingredient21,_ingredientName21,_ingredientClass21,_ingredient22,_ingredientName22,_ingredientClass22,_ingredient23,_ingredientName23,_ingredientClass23,_ingredient24,_ingredientName24,_ingredientClass24,_ingredient25,_ingredientName25,_ingredientClass25,_ingredient26,_ingredientName26,_ingredientClass26,_ingredient27,_ingredientName27,_ingredientClass27,_ingredient28,_ingredientName28,_ingredientClass28,_ingredient29,_ingredientName29,_ingredientClass29,_ingredient30,_ingredientName30,_ingredientClass30,_ingredient31,_ingredientName31,_ingredientClass31" +
                ",_ingredient32,_ingredientName32,_ingredientClass32,_ingredient33,_ingredientName33,_ingredientClass33,_ingredient34,_ingredientName34,_ingredientClass34,_ingredient35,_ingredientName35,_ingredientClass35,_ingredient36,_ingredientName36,_ingredientClass36,_ingredient37,_ingredientName37,_ingredientClass37,_ingredient38,_ingredientName38,_ingredientClass38,_ingredient39,_ingredientName39,_ingredientClass39,_ingredient40,_ingredientName40,_ingredientClass40)";
        String createTable2 = "CREATE TABLE if not exists " + TABLE_NAME2 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT,_ingredient1,_ingredientName1,_ingredientClass1,_ingredient2,_ingredientName2,_ingredientClass2,_ingredient3,_ingredientName3,_ingredientClass3,_ingredient4,_ingredientName4,_ingredientClass4,_ingredient5,_ingredientName5,_ingredientClass5,_ingredient6,_ingredientName6,_ingredientClass6,_ingredient7,_ingredientName7,_ingredientClass7,_ingredient8,_ingredientName8,_ingredientClass8,_ingredient9,_ingredientName9,_ingredientClass9,_ingredient10,_ingredientName10,_ingredientClass10,_ingredient11,_ingredientName11,_ingredientClass11,_ingredient12,_ingredientName12,_ingredientClass12,_ingredient13,_ingredientName13,_ingredientClass13,_ingredient14,_ingredientName14,_ingredientClass14,_ingredient15,_ingredientName15,_ingredientClass15" +
                ",_ingredient16,_ingredientName16,_ingredientClass16,_ingredient17,_ingredientName17,_ingredientClass17,_ingredient18,_ingredientName18,_ingredientClass18,_ingredient19,_ingredientName19,_ingredientClass19,_ingredient20,_ingredientName20,_ingredientClass20,_ingredient21,_ingredientName21,_ingredientClass21,_ingredient22,_ingredientName22,_ingredientClass22,_ingredient23,_ingredientName23,_ingredientClass23,_ingredient24,_ingredientName24,_ingredientClass24,_ingredient25,_ingredientName25,_ingredientClass25,_ingredient26,_ingredientName26,_ingredientClass26,_ingredient27,_ingredientName27,_ingredientClass27,_ingredient28,_ingredientName28,_ingredientClass28,_ingredient29,_ingredientName29,_ingredientClass29,_ingredient30,_ingredientName30,_ingredientClass30,_ingredient31,_ingredientName31,_ingredientClass31" +
                ",_ingredient32,_ingredientName32,_ingredientClass32,_ingredient33,_ingredientName33,_ingredientClass33,_ingredient34,_ingredientName34,_ingredientClass34,_ingredient35,_ingredientName35,_ingredientClass35,_ingredient36,_ingredientName36,_ingredientClass36,_ingredient37,_ingredientName37,_ingredientClass37,_ingredient38,_ingredientName38,_ingredientClass38,_ingredient39,_ingredientName39,_ingredientClass39,_ingredient40,_ingredientName40,_ingredientClass40)";
        String createTable3 = "CREATE TABLE if not exists " + TABLE_NAME3 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " ITEM_NAME TEXT,ITEM_CLASS TEXT,_ingredientClass1)";
        String createTable4 = "CREATE TABLE if not exists " + TABLE_NAME4 + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " ITEM_NAME TEXT,ITEM_CLASS TEXT)";
        db.execSQL(createTable);
        db.execSQL(createTable2);
        db.execSQL(createTable3);
        db.execSQL(createTable4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME2);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME3);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    public void reList() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop  TABLE  " + TABLE_NAME2);
        db.execSQL("Drop  TABLE  " + TABLE_NAME3);
        this.onCreate(db); //在onCreate去新增
        db.close();

    }

    public void reHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop  TABLE  " + TABLE_NAME4);
        this.onCreate(db); //在onCreate去新增
        db.close();

    }
    public void addHisotry(String str,String classify)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3,str);
        contentValues.put(COL9,classify);
        db.insert(TABLE_NAME4,null,contentValues);

    }
    //增加資料到購物清單
    public void addlist(String item,String itemclass,String position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3,item);
        contentValues.put(COL9,itemclass);
        contentValues.put(COL10,position);
        db.insert(TABLE_NAME3,null,contentValues);
//        db.insert(TABLE_NAME4,null,contentValues);
    }
    //增加資料到購物清單
    public void addlist(String item,String itemclass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3,item);
        contentValues.put(COL9,itemclass);
//        contentValues.put(COL10,position);
        db.insert(TABLE_NAME3,null,contentValues);
//        db.insert(TABLE_NAME4,null,contentValues);
    }


    public void dellist(int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3,COL1 + " = " +"'"+position+"'",null );

    }

    public Cursor favoritesupportCookBook(int id)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT Foodid,Foodpart,Part FROM " + TABLE_NAME + " where _id = "+"'"+id+"'", null);
        data.moveToFirst();
        return data;
    }

    public Cursor favoritesupportDistinct(CharSequence classify)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT _id,ITEM_NAME,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26,_ingredient27,_ingredient28,_ingredient29,_ingredient30,_ingredient31,_ingredient32,_ingredient33,_ingredient34,_ingredient35,_ingredient36,_ingredient37,_ingredient38,_ingredient39,_ingredient40  FROM "+ TABLE_NAME+" where Classify = "+"'"+classify+"'" , null);
        data.moveToFirst();
        return data;
    }
    public Cursor favoritesupportDistinctPosition(CharSequence classify)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT _id,ITEM_NAME,_ingredientName1,_ingredientName2,_ingredientName3,_ingredientName4,_ingredientName5,_ingredientName6,_ingredientName7,_ingredientName8,_ingredientName9,_ingredientName10,_ingredientName11,_ingredientName12,_ingredientName13,_ingredientName14,_ingredientName15,_ingredientName16,_ingredientName17,_ingredientName18,_ingredientName19,_ingredientName20,_ingredientName21,_ingredientName22,_ingredientName23,_ingredientName24,_ingredientName25,_ingredientName26,_ingredientName27,_ingredientName28,_ingredientName29,_ingredientName30,_ingredientName31,_ingredientName32,_ingredientName33,_ingredientName34,_ingredientName35,_ingredientName36,_ingredientName37,_ingredientName38,_ingredientName39,_ingredientName40  FROM "+ TABLE_NAME+" where Classify = "+"'"+classify+"'" , null);
        data.moveToFirst();
        return data;


    }
    public Cursor favoritesupportDistinctClass(CharSequence classify)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT _id,ITEM_NAME,_ingredientClass1,_ingredientClass2,_ingredientClass3,_ingredientClass4,_ingredientClass5,_ingredientClass6,_ingredientClass7,_ingredientClass8,_ingredientClass9,_ingredientClass10,_ingredientClass11,_ingredientClass12,_ingredientClass13,_ingredientClass14,_ingredientClass15,_ingredientClass16,_ingredientClass17,_ingredientClass18,_ingredientClass19,_ingredientClass20,_ingredientClass21,_ingredientClass22,_ingredientClass23,_ingredientClass24,_ingredientClass25,_ingredientClass26,_ingredientClass27,_ingredientClass28,_ingredientClass29,_ingredientClass30,_ingredientClass31,_ingredientClass32,_ingredientClass33,_ingredientClass34,_ingredientClass35,_ingredientClass36,_ingredientClass37,_ingredientClass38,_ingredientClass39,_ingredientClass40  FROM "+ TABLE_NAME+" where Classify = "+"'"+classify+"'" , null);
        data.moveToFirst();
        return data;


    }

    public Cursor favoritesupportDistinct()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT Distinct Classify FROM "+ TABLE_NAME, null);
        data.moveToFirst();
        return data;


    }

    public void addDataFromExClass(int Foodid, String classify) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL5, classify);
        db.update(TABLE_NAME, contentValues, "_id" + "=" + "'" + Foodid + "'", null);
    }
    public void delDataFromExClass(int Foodid ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL1 + "=" + "'" + Foodid + "'",null );
    }
    //從食譜裡面加到購物清單內
    public void addDataFromCBToFavorite(String FoodName,int id,String ig,String part,List<String> ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ArrayList<String> ingredientsColumn = new ArrayList<>();
        for (int i =1 ; i < this.getcolumnNumFavorite() - 6; i++) {
            ingredientsColumn.add("_ingredient" + i);
        }
        String column;
        contentValues.put(COL3,FoodName);
        contentValues.put(COL5,"我喜歡的");//把Classify輸入
        contentValues.put(COL6,id);//把Foodid輸入
        contentValues.put(COL7,ig);//把哪一類輸入
        contentValues.put(COL8,part);//把哪一類輸入
        for (int i = 0; i < ingredient.size() ; i++) {
            column = ingredientsColumn.get(i);
            contentValues.put(column, ingredient.get(i));
        }

        db.insert(TABLE_NAME, null, contentValues);//把料理名稱,食材都加入資料庫
    }
    public void addDataFromCBToFavorite(String FoodName,int id,String ig,String part,List<String> ingredient,List<String> ingredientsName,List<String> ingredientsClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ArrayList<String> ingredientsColumn = new ArrayList<>();
        ArrayList<String> ingredientsNameColumn = new ArrayList<>();
        ArrayList<String> ingredientsClassColumn = new ArrayList<>();
        for (int i =1 ; i < this.getcolumnNumFavorite() - 6; i++) {
            ingredientsColumn.add("_ingredient" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 6; i++) {
            ingredientsNameColumn.add("_ingredientName" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 6; i++) {
            ingredientsClassColumn.add("_ingredientClass" + i);
        }
        String column;
        String columnName;
        String columnClass;
        contentValues.put(COL3,FoodName);
        contentValues.put(COL5,"我喜歡的");//把Classify輸入
        contentValues.put(COL6,id);//把Foodid輸入
        contentValues.put(COL7,ig);//把哪一類輸入
        contentValues.put(COL8,part);//把哪一類輸入
        for (int i = 0; i < ingredient.size() ; i++) {

            column = ingredientsColumn.get(i);
            columnName = ingredientsNameColumn.get(i);
            columnClass = ingredientsClassColumn.get(i);
            contentValues.put(column, ingredient.get(i));
            contentValues.put(columnName, ingredientsName.get(i));//加入 第 k道菜 的第 j 項食材名稱
            contentValues.put(columnClass, ingredientsClass.get(i));//加入 第 k道菜 的第 j 項食材位置

        }

        db.insert(TABLE_NAME, null, contentValues);//把料理名稱,食材都加入資料庫
    }
    public void addDataFromTogetToFavorite(String FoodName,int id,String ig,String part,List<String> ingredient,String classify,List<String> ingredientsName,List<String> ingredientsClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ArrayList<String> ingredientsColumn = new ArrayList<>();
        ArrayList<String> ingredientsNameColumn = new ArrayList<>();
        ArrayList<String> ingredientsClassColumn = new ArrayList<>();
        for (int i =1 ; i < this.getcolumnNumFavorite() - 6; i++) {
            ingredientsColumn.add("_ingredient" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 6; i++) {
            ingredientsNameColumn.add("_ingredientName" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 6; i++) {
            ingredientsClassColumn.add("_ingredientClass" + i);
        }
        String column;
        String columnName;
        String columnClass;
        contentValues.put(COL3,FoodName);
        contentValues.put(COL5,classify);//把Classify輸入
        contentValues.put(COL6,id);//把Foodid輸入
        contentValues.put(COL7,ig);//把哪一類輸入
        contentValues.put(COL8,part);//把哪一類輸入
        for (int i = 0; i < ingredient.size() ; i++) {
            column = ingredientsColumn.get(i);
            columnName = ingredientsNameColumn.get(i);
            columnClass = ingredientsClassColumn.get(i);
            contentValues.put(column, ingredient.get(i));
            contentValues.put(columnName, ingredientsName.get(i));//加入 第 k道菜 的第 j 項食材名稱
            contentValues.put(columnClass, ingredientsClass.get(i));//加入 第 k道菜 的第 j 項食材位置

        }

        db.insert(TABLE_NAME, null, contentValues);//把料理名稱,食材都加入資料庫

    }
    //展開式 增加商品到購物清單
    public void addDataFromExToShoppingList(ArrayList<String> FoodName, ArrayList<List<String>> ingredients) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        ContentValues contentValues2 = new ContentValues();
        ArrayList<String> ingredientsColumn = new ArrayList<>();


        for (int i = 1; i < this.getcolumnNum() - 2; i++) {
            ingredientsColumn.add("_ingredient" + i);
        }

        String column;


        int temp = -1, temp1 = -1, l = -1;

        for (int k = 0; k < ingredients.size(); k++)//有幾道菜，這個size就有多大
        {
            l++;//k改變，也就是換到下一道菜的時候
            for (int j = 0; j < ingredients.get(k).size(); j++)//0
            {
                if (l != temp)//確認已經換到下一道菜
                {
                    contentValues.put(COL4, FoodName.get(l));//把菜名輸入
                }
                column = ingredientsColumn.get(j);//每個食材分別存在不同column，所以個別輸入，以 j  來計數


                contentValues.put(column, ingredients.get(k).get(j));//加入 第 k道菜 的第 j 項食材
//                contentValues2.put(COL3, ingredients.get(k).get(j));
//                db.insert(TABLE_NAME3, null, contentValues2);//把食材都加入資料庫
//                db.insert(TABLE_NAME4,null,contentValues2);
            }

            if (k != temp1 & l != temp) {//確認已經換道下一道菜餚
                db.insert(TABLE_NAME2, null, contentValues);//把菜餚和食材都加入資料庫
                temp1 = k;
                temp = l;
            }
        }
    }
    //展開式 增加商品到購物清單
    public void addDataFromExToShoppingList(ArrayList<String> FoodName, ArrayList<List<String>> ingredients,ArrayList<List<String>> ingredientsName,ArrayList<List<String>> ingredientsClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
//        ContentValues contentValues2 = new ContentValues();
        ArrayList<String> ingredientsColumn = new ArrayList<>();
        ArrayList<String> ingredientsNameColumn = new ArrayList<>();
        ArrayList<String> ingredientsClassColumn = new ArrayList<>();

        for (int i = 1; i < this.getcolumnNum() - 2; i++) {
            ingredientsColumn.add("_ingredient" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 2; i++) {
            ingredientsNameColumn.add("_ingredientName" + i);
        }
        for (int i = 1; i < this.getcolumnNum() - 2; i++) {
            ingredientsClassColumn.add("_ingredientClass" + i);
        }
        String column;
        String columnName;
        String columnClass;
        int temp = -1, temp1 = -1, l = -1;

        for (int k = 0; k < ingredients.size(); k++)//有幾道菜，這個size就有多大
        {
            l++;//k改變，也就是換到下一道菜的時候
            for (int j = 0; j < ingredients.get(k).size(); j++)//0
            {
                if (l != temp)//確認已經換到下一道菜
                {
                    contentValues.put(COL4, FoodName.get(l));//把菜名輸入
                }
                column = ingredientsColumn.get(j);//每個食材分別存在不同column，所以個別輸入，以 j  來計數
                columnName = ingredientsNameColumn.get(j);
                columnClass = ingredientsClassColumn.get(j);

                contentValues.put(column, ingredients.get(k).get(j));//加入 第 k道菜 的第 j 項食材
                contentValues.put(columnName, ingredientsName.get(k).get(j));//加入 第 k道菜 的第 j 項食材名稱
                contentValues.put(columnClass, ingredientsClass.get(k).get(j));//加入 第 k道菜 的第 j 項食材位置
//                contentValues2.put(COL3, ingredients.get(k).get(j));
//                db.insert(TABLE_NAME3, null, contentValues2);//把食材都加入資料庫
//                db.insert(TABLE_NAME4,null,contentValues2);
            }

            if (k != temp1 & l != temp) {//確認已經換道下一道菜餚
                db.insert(TABLE_NAME2, null, contentValues);//把菜餚和食材都加入資料庫
                temp1 = k;
                temp = l;
            }
        }
    }
    //顯示 購物清單 菜餚排序
    public Cursor getShoppinList1(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT foodname,_ingredient1,_ingredient2,_ingredient3,_ingredient4,_ingredient5,_ingredient6,_ingredient7,_ingredient8,_ingredient9,_ingredient10,_ingredient11,_ingredient12,_ingredient13,_ingredient14,_ingredient15,_ingredient16,_ingredient17,_ingredient18,_ingredient19,_ingredient20,_ingredient21,_ingredient22,_ingredient23,_ingredient24,_ingredient25,_ingredient26,_ingredient27,_ingredient28,_ingredient29,_ingredient30,_ingredient31,_ingredient32,_ingredient33,_ingredient34,_ingredient35,_ingredient36,_ingredient37,_ingredient38,_ingredient39,_ingredient40 FROM " + TABLE_NAME2, null);
        data.moveToFirst();
        return data;
    }
    //顯示 購物清單 菜餚排序
    public Cursor getShoppinList2(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME3, null);
        data.moveToFirst();
        return data;
    }
    //顯示 購物清單 菜餚排序
    public Cursor getShoppinList3(String itemclass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " where " + COL9 + " = " +"'"+ itemclass+"'", null);
        data.moveToFirst();
        return data;
    }
    //顯示 購物清單 菜餚排序
    public Cursor getShoppinHistory3(String itemclass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME4 + " where " + COL9 + " = " +"'"+ itemclass+"'", null);
        data.moveToFirst();
        return data;
    }

    public Integer getcolumnNum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, null, null, null, null, null);
        int num = cursor.getColumnCount();
        return num;
    }
    public Integer getcolumnNumFavorite() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, null, null, null, null, null);
        int num = cursor.getColumnCount();
        return num;
    }

    //顯示我的最愛資料
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        data.moveToFirst();
        return data;
    }

}

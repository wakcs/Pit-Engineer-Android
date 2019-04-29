package pineapplez.pitengineer.Logic;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;

public class EngineerDataFactory {
    private ArrayList<CategoryItem> categories;

    private static EngineerDataFactory instance;

    public static EngineerDataFactory getInstance(Context context, String fileName)
    {
        if (instance == null)
        {
            instance = new EngineerDataFactory(context, fileName);
        }
        return instance;
    }

    private EngineerDataFactory(Context context, String fileName){
        try {
            categories = XmlHelper.GetEngineerData(context.getAssets().open(fileName));
        }
        catch (Exception e){
            Log.d("",e.toString());
        }
    }

    public ArrayList<CategoryItem> GetEngineerData(){
        return categories;
    }
}

package pineapplez.pitengineer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import pineapplez.pitengineer.Logic.CategoryItemAdapter;
import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;
import pineapplez.pitengineer.Logic.EngineerDataFactory;

public class MainActivity extends BaseActionBarActivity {

    private EngineerDataFactory dataFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Read in XML data
        ListView lvCategories = findViewById(R.id.CategoryList);
        dataFactory = EngineerDataFactory.getInstance(this, "PitEngineer_Data.xml");

        //Fill list view with data
        ArrayAdapter arrayAdapter = new CategoryItemAdapter(this,dataFactory.GetEngineerData());
        lvCategories.setAdapter(arrayAdapter);
        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                startQuestionActivity(pos);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1){
            String catName = data.getStringExtra("catRef");
            startQuestionActivity(catName);
        }
    }

    private void startQuestionActivity(int categoryID){
        CategoryItem selectedItem = dataFactory.GetEngineerData().get(categoryID);
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        intent.putExtra("Cat", selectedItem);
        startActivityForResult(intent, 1);
    }

    private void startQuestionActivity(String categoryName){
        CategoryItem selectedItem = null;

        // Get data from category
        for(CategoryItem item:dataFactory.GetEngineerData()){
            if(item.name.equals(categoryName)){
                selectedItem = item;
            }
        }
        if(selectedItem == null){
            Toast.makeText(getApplicationContext(), categoryName + "Doesn't exist", Toast.LENGTH_LONG).show();
            return;
        }

        // Start activity with category data
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        intent.putExtra("Cat", selectedItem);
        startActivityForResult(intent, 1);
    }
}
package pineapplez.pitengineer;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;
import pineapplez.pitengineer.ui.question.QuestionFragment;

public class QuestionActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Enable home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (savedInstanceState == null) {
            // Generate fragment with question data
            Intent intent = getIntent();
            CategoryItem categoryItem = intent.getParcelableExtra("Cat");
            swapFragment(categoryItem,0, null);
        }
    }

    public void swapFragment(CategoryItem categoryItem, int questionID, String categoryRef){
        //if answer refers to other category,
        //close this activity and start a new one of that category
        if(categoryRef != null){
            Intent intent = new Intent();
            intent.putExtra("catRef", categoryRef);
            setResult(1, intent);
            finish();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("Cat", categoryItem);
        bundle.putInt("QuestionID", questionID);

        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(bundle);

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.container, questionFragment);
        if(questionID != 0){
            trans.addToBackStack(null);
        }
        trans.commit();
    }
}

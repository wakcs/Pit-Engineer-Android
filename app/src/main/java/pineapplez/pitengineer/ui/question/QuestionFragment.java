package pineapplez.pitengineer.ui.question;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import pineapplez.pitengineer.Logic.AnswerItemAdapter;
import pineapplez.pitengineer.Logic.DataStructs.AnswerItem;
import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;
import pineapplez.pitengineer.Logic.DataStructs.QuestionItem;
import pineapplez.pitengineer.QuestionActivity;
import pineapplez.pitengineer.R;

public class QuestionFragment extends Fragment {

    private CategoryItem categoryItem;
    private QuestionItem questionItem;

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        categoryItem = getArguments().getParcelable("Cat");
        int questionID = getArguments().getInt("QuestionID",0);
        if(categoryItem != null) {
            //get values
            questionItem = categoryItem.questions.get(questionID);
            TextView txtTitle = view.findViewById(R.id.txtTitle);
            ListView lvAnswers = view.findViewById(R.id.lvAnswers);
            ImageView img = view.findViewById(R.id.imgCat);

            int imgRes;
            switch (categoryItem.name){
                case "Braking":
                    imgRes = R.drawable.img_braking;
                    break;
                case "Downforce":
                    imgRes = R.drawable.img_downforce;
                    break;
                case "Suspension":
                    imgRes = R.drawable.img_suspension;
                    break;
                case "Gearing":
                    imgRes = R.drawable.img_gearing;
                    break;
                default:
                    imgRes = R.drawable.ic_launcher_background;
                    break;
            }

            getActivity().setTitle(categoryItem.name);
            txtTitle.setText(questionItem.description);
            img.setImageResource(imgRes);

            ArrayAdapter arrayAdapter = new AnswerItemAdapter(view.getContext(), questionItem.answers);
            lvAnswers.setAdapter(arrayAdapter);
            lvAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AnswerItem selectedItem = questionItem.getAnswers().get(position);
                    if(selectedItem.refID != 0 || selectedItem.refCat != null) {
                        QuestionActivity fragActivity = (QuestionActivity) getActivity();
                        fragActivity.swapFragment(categoryItem, selectedItem.refID, selectedItem.refCat);
                    }
                }
            });

        }
        return view;
    }
}

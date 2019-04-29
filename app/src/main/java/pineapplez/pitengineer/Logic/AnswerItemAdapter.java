package pineapplez.pitengineer.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pineapplez.pitengineer.Logic.DataStructs.AnswerItem;
import pineapplez.pitengineer.R;

public class AnswerItemAdapter extends ArrayAdapter<AnswerItem> {
    public AnswerItemAdapter(Context context, ArrayList<AnswerItem> items){
        super(context,0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AnswerItem item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_answer,
                    parent,
                    false);
        }

        TextView txtAnswer = convertView.findViewById(R.id.txtAnswer);
        txtAnswer.setText(item.description);
        return convertView;
    }
}

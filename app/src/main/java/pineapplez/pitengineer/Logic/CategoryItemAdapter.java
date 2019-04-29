package pineapplez.pitengineer.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;
import pineapplez.pitengineer.R;

public class CategoryItemAdapter extends ArrayAdapter<CategoryItem> {
    public CategoryItemAdapter(Context context, ArrayList<CategoryItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_category,
                    parent,
                    false
            );
        }

        TextView title = convertView.findViewById(R.id.TxtTitle);
        TextView desc = convertView.findViewById(R.id.TxtDesc);
        ImageView img = convertView.findViewById(R.id.imgBackground);
        int imgRes;

        switch (item.name){
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

        title.setText(item.name);
        desc.setText(item.description);
        img.setImageResource(imgRes);
        return convertView;
    }
}

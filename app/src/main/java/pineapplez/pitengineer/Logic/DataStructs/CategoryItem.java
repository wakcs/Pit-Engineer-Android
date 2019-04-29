package pineapplez.pitengineer.Logic.DataStructs;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class CategoryItem implements Parcelable {
    public String name;
    public String description;
    public ArrayList<QuestionItem> questions;

    public CategoryItem(String name,String description, ArrayList<QuestionItem> questions){
        this.name = name;
        this.description = description;
        this.questions = questions;
    }
    public CategoryItem(){
        this.questions = new ArrayList<>();
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public ArrayList<QuestionItem> getQuestions(){
        return questions;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setQuestions(ArrayList<QuestionItem> questions){
        this.questions = questions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeTypedList(this.questions);
    }

    private CategoryItem(Parcel in){
        this();
        this.name = in.readString();
        this.description = in.readString();
        in.readTypedList(this.questions, QuestionItem.CREATOR);
    }

    public static final Parcelable.Creator<CategoryItem> CREATOR = new Parcelable.Creator<CategoryItem>(){
        @Override
        public CategoryItem createFromParcel(Parcel source) {
            return new CategoryItem(source);
        }

        @Override
        public CategoryItem[] newArray(int size) {
            return new CategoryItem[size];
        }
    };
}
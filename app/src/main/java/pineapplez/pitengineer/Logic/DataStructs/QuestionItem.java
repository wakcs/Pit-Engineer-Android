package pineapplez.pitengineer.Logic.DataStructs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class QuestionItem implements Parcelable {
    public int id;
    public String description;
    public ArrayList<AnswerItem> answers;

    public QuestionItem(int id, String description, ArrayList<AnswerItem> answers){
        this.id = id;
        this.description = description;
        this.answers = answers;
    }
    public QuestionItem() {
        this.answers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<AnswerItem> getAnswers() {
        return answers;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAnswers(ArrayList<AnswerItem> answers) {
        this.answers = answers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.description);
        dest.writeTypedList(this.answers);
    }

    private QuestionItem(Parcel in){
        this();
        this.id = in.readInt();
        this.description = in.readString();
        in.readTypedList(this.answers, AnswerItem.CREATOR);
    }

    public static final Parcelable.Creator<QuestionItem> CREATOR = new Parcelable.Creator<QuestionItem>(){
        @Override
        public QuestionItem createFromParcel(Parcel source) {
            return new QuestionItem(source);
        }

        @Override
        public QuestionItem[] newArray(int size) {
            return new QuestionItem[size];
        }
    };
}

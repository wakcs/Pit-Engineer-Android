package pineapplez.pitengineer.Logic.DataStructs;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswerItem implements Parcelable {
    public int refID;
    public String description;
    public String refCat;

    public AnswerItem(int refID, String description, String refCat){
        this.refID = refID;
        this.description = description;
        this.refCat = refCat;
    }
    public AnswerItem() {}

    public int getRefID() {
        return refID;
    }
    public String getDescription() {
        return description;
    }

    public String getRefCat() {
        return refCat;
    }

    public void setRefID(int refID) {
        this.refID = refID;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setRefCat(String refCat) {
        this.refCat = refCat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.refID);
        dest.writeString(this.description);
        dest.writeString(this.refCat);
    }

    private AnswerItem(Parcel in){
        this.refID = in.readInt();
        this.description = in.readString();
        this.refCat = in.readString();
    }

    public static final Parcelable.Creator<AnswerItem> CREATOR = new Parcelable.Creator<AnswerItem>(){
        @Override
        public AnswerItem createFromParcel(Parcel source) {
            return new AnswerItem(source);
        }

        @Override
        public AnswerItem[] newArray(int size) {
            return new AnswerItem[size];
        }
    };
}

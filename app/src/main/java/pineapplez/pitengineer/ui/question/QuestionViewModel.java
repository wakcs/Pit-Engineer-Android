package pineapplez.pitengineer.ui.question;

import android.arch.lifecycle.ViewModel;

import pineapplez.pitengineer.Logic.DataStructs.CategoryItem;
import pineapplez.pitengineer.Logic.EngineerDataFactory;

class QuestionViewModel extends ViewModel {
    private final EngineerDataFactory dataFactory;
    private int categoryID = 0, questionID = 0;

    public QuestionViewModel(EngineerDataFactory dataFactory){
        this.dataFactory = dataFactory;
    }

    public CategoryItem getCategory(String name){
        for (CategoryItem cat: dataFactory.GetEngineerData()){
            if(cat.name == name){
                return cat;
            }
        }
        return null;
    }

    public CategoryItem getCategory(int index){
        return dataFactory.GetEngineerData().get(index);
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setCategoryID(String categoryName){
        for (int i = 0; i < dataFactory.GetEngineerData().size(); ++i){
            if(dataFactory.GetEngineerData().get(i).name == categoryName) {
                this.categoryID = i;
            }
        }
        this.categoryID = 0;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
}

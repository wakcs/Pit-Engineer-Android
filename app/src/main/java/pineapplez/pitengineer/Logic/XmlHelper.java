package pineapplez.pitengineer.Logic;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pineapplez.pitengineer.Logic.DataStructs.*;

class XmlHelper {

    private static final String ns = null;

    public static ArrayList GetEngineerData(InputStream inputStream) throws XmlPullParserException, IOException {
        try{
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(inputStream, null);
            parser.nextTag();
            return ReadFeed(parser);
        }
        finally {
            inputStream.close();
        }
    }

    private static ArrayList ReadFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList categories = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "Root");
        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals("Category")) {
                categories.add(ReadCat(parser));
            } else {
                Skip(parser);
            }
        }
        return categories;
    }

    private static CategoryItem ReadCat(XmlPullParser parser) throws XmlPullParserException, IOException {
        CategoryItem cat = new CategoryItem();
        ArrayList quests = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "Category");
        cat.name = parser.getAttributeValue(null, "Name");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals("Question")) {
                quests.add(ReadQuest(parser));
            }else {
                Skip(parser);
            }
        }
        cat.questions = quests;
        if(!cat.questions.isEmpty()){
            cat.description = cat.questions.get(0).description;
        }
        return cat;
    }

    private static QuestionItem ReadQuest (XmlPullParser parser) throws XmlPullParserException, IOException {
        QuestionItem quest = new QuestionItem();
        ArrayList answers = new ArrayList();
        parser.require(XmlPullParser.START_TAG, ns, "Question");
        String id = parser.getAttributeValue(null, "QuestionID");
        quest.id = Integer.parseInt(id);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "Description":
                    quest.description = ReadInnerText(parser);
                    break;
                case "Answer":
                    answers.add(ReadAnswer(parser));
                    break;
                default:
                    Skip(parser);
                    break;
            }
        }
        quest.answers = answers;
        return quest;
    }

    private static AnswerItem ReadAnswer(XmlPullParser parser) throws XmlPullParserException, IOException {
        AnswerItem answer = new AnswerItem();
        parser.require(XmlPullParser.START_TAG, ns, "Answer");
        String id = parser.getAttributeValue(null, "QuestionRef");
        answer.refID = Integer.parseInt(id);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "Description":
                    answer.description = ReadInnerText(parser);
                    break;
                case "CategoryRef":
                    answer.refCat = ReadInnerText(parser);
                    break;
                default:
                    Skip(parser);
                    break;
            }
        }
        return answer;
    }

    private static String ReadInnerText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String desc = null;
        if (parser.next() == XmlPullParser.TEXT) {
            desc = parser.getText();
            parser.nextTag();
        }
        return desc;
    }

    private static void Skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
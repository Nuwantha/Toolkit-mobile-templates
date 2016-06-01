package org.buildmlearn.comprehension.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by Anupam (opticod) on 1/6/16.
 */
public class ComprehensionModel implements Parcelable {
    public final Parcelable.Creator<ComprehensionModel> CREATOR = new Parcelable.Creator<ComprehensionModel>() {
        @Override
        public ComprehensionModel createFromParcel(Parcel parcel) {
            return new ComprehensionModel(parcel);
        }

        @Override
        public ComprehensionModel[] newArray(int size) {
            return new ComprehensionModel[size];
        }
    };
    private String question;
    private ArrayList<String> options;
    private int correctAnswer;

    public ComprehensionModel(String question, ArrayList<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    private ComprehensionModel(Parcel in) {
        this.question = in.readString();
        this.options = in.createStringArrayList();
        this.correctAnswer = in.readInt();
    }

    public ComprehensionModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeStringList(options);
        dest.writeInt(correctAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Element getXml(Document doc) {
        Element rootElement = doc.createElement("item");
        Element questionElement = doc.createElement("question");
        questionElement.appendChild(doc.createTextNode(question));
        rootElement.appendChild(questionElement);
        for (String option : options) {
            Element optionElement = doc.createElement("option");
            optionElement.appendChild(doc.createTextNode(option));
            rootElement.appendChild(optionElement);
        }

        Element answerElement = doc.createElement("answer");
        answerElement.appendChild(doc.createTextNode(String.valueOf(correctAnswer)));
        rootElement.appendChild(answerElement);
        return rootElement;
    }

}

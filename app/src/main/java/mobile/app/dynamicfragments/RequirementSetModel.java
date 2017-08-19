package mobile.app.dynamicfragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by madhu on 7/7/17.
 */

public class RequirementSetModel {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("options")
    @Expose
    private List<UserQuestionOptionModel> options = null;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<UserQuestionOptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<UserQuestionOptionModel> options) {
        this.options = options;
    }

}

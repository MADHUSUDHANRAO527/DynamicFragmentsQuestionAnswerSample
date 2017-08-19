package mobile.app.dynamicfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by madhu on 18/08/17.
 */

public class HelloFragment extends Fragment implements TriggerPos {
    TextView fragmentCountTxt;
    String json = "[\n" +
            "{\n" +
            "\"question_id\":\"8\",\n" +
            "\"question\":\"What best describes you?\",\n" +
            "\"options\":[\n" +
            "{\n" +
            "\"option_id\":\"16\",\n" +
            "\"option_value\":\"Bride\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"17\",\n" +
            "\"option_value\":\"Bride's friend \\/ relative\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"69\",\n" +
            "\"option_value\":\"Other\"\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"question_id\":\"24\",\n" +
            "\"question\":\"Which event(s) do you need makeup for?\",\n" +
            "\"options\":[\n" +
            "{\n" +
            "\"option_id\":\"70\",\n" +
            "\"option_value\":\"Engagement\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"71\",\n" +
            "\"option_value\":\"Sangeet\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"72\",\n" +
            "\"option_value\":\"Wedding\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"73\",\n" +
            "\"option_value\":\"Reception\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"74\",\n" +
            "\"option_value\":\"Other ( event name)\"\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"question_id\":\"26\",\n" +
            "\"question\":\"How many additional people want the makeup?\",\n" +
            "\"options\":[\n" +
            "{\n" +
            "\"option_id\":\"75\",\n" +
            "\"option_value\":\"No. only need for myself\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"76\",\n" +
            "\"option_value\":\"One\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"77\",\n" +
            "\"option_value\":\"Two\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"78\",\n" +
            "\"option_value\":\"Three - Five\"\n" +
            "},\n" +
            "{\n" +
            "\"option_id\":\"79\",\n" +
            "\"option_value\":\"More than Five\"\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]";
    JSONArray array;
    ArrayList<RequirementSetModel> modelList;
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    int position;
    MainActivity mainActivity;
    Context mContext;

    public HelloFragment(int i) {
        MainActivity.firstTime = i;
    }

    public HelloFragment() {
        mContext = getActivity();
    }

    RadioGroup radioGrp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.hello_fragment_layout, null);
        fragmentCountTxt = (TextView) v.findViewById(R.id.qu_head);
        sharedPreferences = getActivity().getSharedPreferences("QUESTION_1ST", 0);
        editor = sharedPreferences.edit();
        mainActivity = new MainActivity(this, mContext);

        radioGrp = (RadioGroup) v.findViewById(R.id.radio_grp);
        try {
            array = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<RequirementSetModel>>() {
        }.getType();
        modelList = gson.fromJson(array.toString(), type);
        Log.d("pos", Singleton.getInstance().getPos() - 1 + "");

        if (Singleton.getInstance().getPos() < modelList.size()) {
            fragmentCountTxt.setText(modelList.get(Singleton.getInstance().getPos()).getQuestion());


            if (modelList.get(Singleton.getInstance().getPos()).getOptions().size() > 0) {
                ((RadioButton) radioGrp.getChildAt(0)).setText(modelList.get(Singleton.getInstance().getPos()).getOptions().get(0).getOptionValue());
                ((RadioButton) radioGrp.getChildAt(1)).setText(modelList.get(Singleton.getInstance().getPos()).getOptions().get(1).getOptionValue());
            }

            if (modelList.get(Singleton.getInstance().getPos()).getOptions().size() > 2) {
                ((RadioButton) radioGrp.getChildAt(2)).setVisibility(View.VISIBLE);
                ((RadioButton) radioGrp.getChildAt(2)).setText(modelList.get(Singleton.getInstance().getPos()).getOptions().get(2).getOptionValue());
                ((RadioButton) radioGrp.getChildAt(3)).setVisibility(View.GONE);

                if (modelList.get(Singleton.getInstance().getPos()).getOptions().size() > 3) {
                    ((RadioButton) radioGrp.getChildAt(3)).setVisibility(View.VISIBLE);
                    ((RadioButton) radioGrp.getChildAt(3)).setText(modelList.get(Singleton.getInstance().getPos()).getOptions().get(3).getOptionValue());
                }
            } else {
                ((RadioButton) radioGrp.getChildAt(2)).setVisibility(View.GONE);
                ((RadioButton) radioGrp.getChildAt(3)).setVisibility(View.GONE);
            }

        } else {
            Log.d(" interface click ", Singleton.getInstance().getPos() + "");
        }

        return v;
    }

    @Override
    public void getPosition(int pos) {
        Log.d(" interface click ", pos + "");
    }
}
package mobile.app.dynamicfragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    /**
     * Called when the activity is first created.
     */
    boolean replace;

    public static int firstTime = 0;
    TriggerPos triggerPos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLoad = (Button) findViewById(R.id.btn_load);
        replaceFragment(new HelloFragment());
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addFragment(new HelloFragment(mFragmentStack.size() - 1));
                Log.d("onClick: pos :", mFragmentStack.size() + "");

                Singleton.getInstance().setPos(mFragmentStack.size() - 1);
               // triggerPos.getPosition(mFragmentStack.size() - 1);

                //Options



            }
        };
        btnLoad.setOnClickListener(listener);
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        onBackpressedd();
    }

    public MainActivity(TriggerPos triger, Context mContext) {
        triggerPos = triger;
    }

    public MainActivity() {
    }
}
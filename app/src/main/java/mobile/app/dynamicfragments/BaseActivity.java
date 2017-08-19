package mobile.app.dynamicfragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Stack;

/**
 * Created by Madhu on 17/06/17.
 */

public class BaseActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager;
    private Context mContext;
    public  Stack<Fragment> mFragmentStack = null;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mFragmentManager = getSupportFragmentManager();
    }

    public void onBackpressedd() {
        if (mFragmentStack.size() >= 2) {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            mFragmentStack.lastElement().onPause();
            ft.remove(mFragmentStack.pop());
            mFragmentStack.lastElement().onResume();
            ft.show(mFragmentStack.lastElement());
            ft.commitAllowingStateLoss();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "TAP again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void replaceFragment(Fragment fragment) {
        if (mFragmentStack != null) {
            Log.e("Size", mFragmentStack.size() + "");
        }
        mFragmentStack = new Stack<>();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        mFragmentStack.push(fragment);
        transaction.commitAllowingStateLoss();
    }

    public void addFragment(Fragment fragment) {
        if (mFragmentManager != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container, fragment, "HELLO");
       //     mFragmentStack.lastElement().onPause();
         //   transaction.hide(mFragmentStack.lastElement());
            mFragmentStack.push(fragment);
            transaction.commitAllowingStateLoss();
            Log.e("Size", mFragmentStack.size() + "");

        }
    }
}

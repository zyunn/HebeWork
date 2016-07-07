package com.hebe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.localadmin.hebework.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleViewClick(View view) {
        switch (view.getId()) {
            case R.id.glide_demo:
                gotoActivity(GlideDemoActivity.class);
                break;
            case R.id.coordinator_demo:
                gotoActivity(TestCoordinatorActivity.class);
                break;
            case R.id.card_view_demo:
                gotoActivity(DetailCardViewTestActivity.class);
                break;
            case R.id.circle_progress:
                gotoActivity(CircleProgressDemoActivity.class);
                break;
        }
    }


    /***
     * 跳转到新的activity 中去
     *
     * @param newActivityClass 新的activity
     */
    private void gotoActivity(Class<?> newActivityClass) {
        startActivity(new Intent(getBaseContext(), newActivityClass));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

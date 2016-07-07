package com.hebe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.localadmin.hebework.R;

/**
 * 测试CoordinatorLayout
 * Created by HebeChung on 16/6/20.
 */
public class TestCoordinatorActivity extends AppCompatActivity {

    private String[] strings=new String[]{
            "Thai","Asia","American","China","England","Irish",
            "I love ","I like","I do not like","baby","DianDian",
            "TuRan","Cat","Dog","money"

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_coordinator_test);
            RecyclerView recyclerView= (RecyclerView) findViewById(R.id.rvToDoList);
            RVAdapter adapter=new RVAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


 private  class  RVViewHolder extends RecyclerView.ViewHolder{
     TextView textView;
      RVViewHolder(View itemView) {
         super(itemView);
         this.textView= (TextView) itemView.findViewById(android.R.id.text1);
     }
 }

    private class RVAdapter extends RecyclerView.Adapter<RVViewHolder>{

        @Override
        public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RVViewHolder(View.inflate(getBaseContext(),android.R.layout.simple_list_item_1,null));
        }

        @Override
        public void onBindViewHolder(RVViewHolder holder, int position) {
            holder.textView.setText(strings[position]);
        }

        @Override
        public int getItemCount() {
            return strings.length;
        }
    }
}

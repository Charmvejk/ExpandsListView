package com.example.lianxis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private myAdapter myAdapter;
    private List<String> group;
    private List<List<String>> child;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) this.findViewById(R.id.expanfListView);
        // 去掉前面箭头
        //expandableListView.setGroupIndicator(null);
        myAdapter = new myAdapter();
        initData();
        expandableListView.setAdapter(myAdapter);
        //设置子项布局监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), child.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;

            }
        });

    }

    public void initData(){
        group = new ArrayList<String>();
        child = new ArrayList<List<String>>();
        addInfo("jack", new String[] {"a","aa","aaa"});
        addInfo("rose", new String[] {"b","bb","bbb"});
        addInfo("tom", new String[] {"c","cc","ccc"});
    }

    public void addInfo(String g, String[] c){
        group.add(g);
        List<String> item = new ArrayList<>();
        for(int i = 0; i < c.length; i++){
            item.add(c[i]);
        }
        child.add(item);
    }

    public class myAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return group.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return child.size();
        }
//


        @Override
        public Object getGroup(int i) {
            return group.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return child.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

            TextView textView = null;
            if (view == null){
                textView = new TextView(MainActivity.this);
            }else{
                textView = (TextView) view;
            }

            textView.setText(group.get(i));
            textView.setTextSize(20);
            textView.setPadding(70,0,0,40);
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

            TextView textView = null;
            if (view == null){
                textView = new TextView(MainActivity.this);
            }else{
                textView = (TextView) view;
            }
            textView.setText(child.get(i).get(i1));
            textView.setTextSize(20);
            textView.setPadding(70,0,0,40);
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

}

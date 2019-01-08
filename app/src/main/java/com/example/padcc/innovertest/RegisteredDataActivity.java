package com.example.padcc.innovertest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegisteredDataActivity extends AppCompatActivity {

    DatabaseHandler db;
    ListView listView;
    TextView fn,ln,em;
    List<people> visitorList = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_data);



        //  btnview=(Button) findViewById(R.id.button_view);

        listView = (ListView) findViewById(R.id.listview1);
        db=new DatabaseHandler(RegisteredDataActivity.this);

        visitorList=db.getAllVisitors();
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

    }


    class CustomAdapter extends BaseAdapter {
        people visitor=new people();
        @Override
        public int getCount() {
            return visitorList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.peoplelist, null);


            final TextView textViewfname = (TextView) view.findViewById(R.id.FN);
            final TextView textViewlname = (TextView) view.findViewById(R.id.LN);
            TextView textviewEmail=(TextView) view.findViewById(R.id.EM);




            textViewfname.setText(String.valueOf(visitorList.get(position).getFname()));
            textViewlname.setText(visitorList.get(position).getLname());
            textviewEmail.setText(visitorList.get(position).getEmail());

//            visitor.setVisitorId(visitorList.get(position).getVisitorId());
//            visitor.setVfirstnName(visitorList.get(position).getVfirstnName());
//            visitor.setVLastName(visitorList.get(position).getVLastName());
//            visitor.setVPhone(visitorList.get(position).getVPhone());
//            visitor.setVEmail(visitorList.get(position).getVEmail());
//            visitor.setVTechnique(visitorList.get(position).getVTechnique());
//            visitor.setVgender(visitorList.get(position).getVgender());


            return view;
        }
    }
}


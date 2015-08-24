package com.emdsys.android.jsonapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        String strJson = "{\"Employee\" :[{ \"id\":\"01\", " +
                "\"name\":\"Gopal Varma\", \"salary\":\"500000\" }," +
                " { \"id\":\"02\", \"name\":\"Sairamkrishna\", \"salary\":\"500000\" }," +
                " { \"id\":\"03\", \"name\":\"Sathish kallakuri\", \"salary\":\"600000\" } ]" +
                " }";
        String data="";
        try {
            JSONObject jsonObject=new JSONObject(strJson);
            JSONArray jsonArray=jsonObject.optJSONArray("Employee");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                int id=Integer.parseInt(jsonObject1.optString("id"));
                String name=jsonObject1.optString("name");
                float salary=Float.parseFloat(jsonObject1.optString("salary"));
                data+="Node"+i+": \n id= "+id+"\n name = "+name+"\n salary = "+salary+"\n";
            }
            textView.setText(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        // automatically handle clicks on the Home\Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

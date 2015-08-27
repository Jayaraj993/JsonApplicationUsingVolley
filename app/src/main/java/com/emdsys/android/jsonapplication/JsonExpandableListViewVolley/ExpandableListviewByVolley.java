package com.emdsys.android.jsonapplication.JsonExpandableListViewVolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.emdsys.android.jsonapplication.JsonListViewByVolley.AppController2;
import com.emdsys.android.jsonapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ExpandableListviewByVolley extends AppCompatActivity {
    private static final String url = "http://api.androidhive.info/json/movies.json";
    ProgressDialog mPDialog;

    private ExpandableListAdapter adapter;
    private ExpandableListView expandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_listview_by_volley);

        expandableList= (ExpandableListView) findViewById(R.id.expandable_listview);
        mPDialog=new ProgressDialog(this);
        mPDialog.setTitle("Loading.......");
        mPDialog.setCancelable(false);
        makeJsonObjectRequest();
    }
    private void makeJsonObjectRequest(){
        mPDialog.show();
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Group> list = new ArrayList<Group>();
                ArrayList<Child> ch_list = new ArrayList<Child>();

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Group group = new Group();
                        Child child = new Child();
                        group.setName(jsonObject.getString("title"));

                        child.setName(jsonObject.getString("title"));
                        child.setThumbnailUrl(jsonObject.getString("image"));
                        ch_list.add(child);
                        group.setItems(ch_list);
                        list.add(group);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                adapter = new ExpandableListAdapter(ExpandableListviewByVolley.this, list);
                expandableList.setAdapter(adapter);
                mPDialog.dismiss();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPDialog.dismiss();
            }
        });
        MyApplication.getInstance().addToRequestQueue(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expandable_listview_by_volley, menu);
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

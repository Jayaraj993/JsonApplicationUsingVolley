package com.emdsys.android.jsonapplication.JsonVolley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.emdsys.android.jsonapplication.R;

import com.android.volley.Request.Method;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by EMD029 on 8/22/2015.
 */
public class MyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button jsonObjButton,jsonArrayButton;
    private String urlJsonObj = "http://api.androidhive.info/volley/person_object.json";

    // json array response url
    private String urlJsonArry = "http://api.androidhive.info/volley/person_array.json";
    private static String TAG=MyActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;
    private String jsonRespose;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_object_volley_layout);
        initInitializer();


    }
    public void initInitializer(){
        textView= (TextView) findViewById(R.id.textView);
        jsonObjButton= (Button) findViewById(R.id.mtd_to_get_jsonObject);
        jsonArrayButton= (Button) findViewById(R.id.mtd_to_get_jsonArray);
        jsonArrayButton.setOnClickListener(this);
        jsonObjButton.setOnClickListener(this);

        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setTitle("loading data");
        mProgressDialog.setCancelable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mtd_to_get_jsonObject:
                makeJsonObjectRequest();
                break;
            case R.id.mtd_to_get_jsonArray:
                //makeJsonArrayRequest();
                break;


        }
    }
    private void showmProgressDialog(){
        if (!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }
    private void hidemProgressDialog(){
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    private void makeJsonObjectRequest(){
        showmProgressDialog();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG,response.toString());
                try {
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject phone = response.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");

                    textView.setText(""+"Name: " + name + "\n\n"+"Email: " + email + "\n\n"+"Home: " + home + "\n\n"+"Mobile: " + mobile + "\n\n");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidemProgressDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidemProgressDialog();
            }
        });
        AppController1.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}

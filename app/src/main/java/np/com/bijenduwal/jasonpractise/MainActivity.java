package np.com.bijenduwal.jasonpractise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextviewResult;
    private Button loadJson;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextviewResult=findViewById(R.id.content_display_id);
        loadJson=findViewById(R.id.json_display_id);

        mQueue= Volley.newRequestQueue(this);

        loadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParse();
            }
        });

    }

    private void jsonParse() {

        String url="https://api.myjson.com/bins/hsrw8";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

          try {
            JSONArray jsonArray = response.getJSONArray("employees");

            for(int i=0; i<jsonArray.length();i++){
                JSONObject employees=jsonArray.getJSONObject(i);
                String firstName=employees.getString("firstname");
                int age=employees.getInt("age");
                String mail=employees.getString("mail");

                mTextviewResult.append(firstName +", " + String.valueOf(age)+ "," + mail + "\n\n");
            }
              }catch(JSONException e){
              e.printStackTrace();
              }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
}

package io.hardwick.json_to_listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<SimpleObject> items;
    ListView listView;
    ArrayAdapter<SimpleObject> adapter;
    JSONArray jArray;

    public String json = "https://raw.github.com/matthewhardwick/watts_input_to_json/master/someP1.lib0.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Items Container
        items = new ArrayList<SimpleObject>();

        // Setup Listview, yes items is empty, that is OK
        listView = (ListView) findViewById(R.id.listView);
        // NOTE: It is OK to use a SimpleObject, with an android.R.layout.simple_list_item_1 because
        // SimpleObject has a toString method declared, which the adapter calls to generate the
        // ListView text.
        adapter = new ArrayAdapter<SimpleObject>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Now that the adapter is setup, if we need to update the listView we will use adapter.add
        // instead of items.add, the adapter will take care of adding to the host container.
        // Why did we do it this way? Because the HttpClient is Asynchronous, meaning it is run
        // on a separate thread from the UI, so the Activity continues to run at the same time
        // as the HttpClient fetches the Data.

        // Setup the AsyncHttpClient to fetch the JSON String
        AsyncHttpClient client = new AsyncHttpClient();
        // Spin up a new thread, and fetch the JSON
        client.get(json, new AsyncHttpResponseHandler() {

            // When the JSON has been fetched, we will process it
            // The JSON is simply a string at this point. Now because the JSON is formated as a
            // JSON Array, we will parse it as an Array, and then loop over each JSON Object
            // Fetch that object, and parse out two values from it, and put them into our
            // Simple Object Class.
            @Override
            public void onSuccess(String response) {
                try {
                    jArray = new JSONArray(response); // Parse JSON String to JSON Array
                    for (int i = 0; i < jArray.length(); ++i) { // Loop over Array
                        SimpleObject simpleObject = new SimpleObject(); // Create a new Simple Object

                        JSONObject jObject = jArray.getJSONObject(i); // Fetch the ith JSON Object
                        // from the JSON Array
                        simpleObject.setName(jObject.getString("Name")); // Parse Name from the JSON
                        // Object, and put into our
                        // object
                        simpleObject.setType(jObject.getString("Type")); // Do the same for type

                        // Remember there are other items in the JSON Object, and they are of other
                        // Types, so you might want to switch based on type and create an object
                        // such as a Book, Periodical, or Member

                        // For Reference to get the Checked out Array of a Member to a List
                        if (simpleObject.getType().equals("Member")) {
                            List<String> checkedOut = new ArrayList<String>();
                            JSONArray checkedOutArray = jObject.getJSONArray("Checked_Out");
                            for (int j = 0; j < checkedOutArray.length(); ++j)
                                checkedOut.add(checkedOutArray.getString(i));
                            // Do something with the List, such as put it in a Member Object for later
                        }

                        // Add an Item to the Adapter, which will add it to the items List, and
                        // update the List View
                        adapter.add(simpleObject);
                    }
                    Log.d("List Size", Integer.toString(items.size()));
                } catch (JSONException e) {
                    Log.d("JSON Parse", e.toString());
                }
            }
        });

    }

    public class SimpleObject {
        public String name;
        public String type;

        public SimpleObject() {
        }

        public SimpleObject(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            // Example of a String Builder like we talked about in class.
            StringBuilder builder = new StringBuilder();
            return builder.append(name).append(", ").append(type).toString();
        }
    }
}


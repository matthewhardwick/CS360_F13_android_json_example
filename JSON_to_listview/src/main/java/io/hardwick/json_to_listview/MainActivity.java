package io.hardwick.json_to_listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<SimpleObject> items;
    ListView listView;
    ArrayAdapter<SimpleObject> adapter;

    public String json = "https://raw.github.com/matthewhardwick/watts_input_to_json/master/someP1.lib0.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<SimpleObject>();
        for (int i = 0; i < 25; ++i) {
            items.add(new SimpleObject("Name", "Type"));
        }

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<SimpleObject>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

    }

    public class SimpleObject {
        public String name;
        public String type;

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
            StringBuilder builder = new StringBuilder();
            return builder.append(name).append(", ").append(type).toString();
        }
    }
}


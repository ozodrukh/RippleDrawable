package dreamers.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.telly.mrvector.MrVector;

public class SampleActivity extends ActionBarActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new ListAdapter());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MrVector.wrap(newBase));
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if(name.contains("ListView")) {
            Log.i("SampleActivity", context.toString());
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    static class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            }

            return convertView;
        }
    }

}

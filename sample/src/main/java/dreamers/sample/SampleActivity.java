package dreamers.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import codetail.graphics.drawables.LollipopDrawablesCompat;

public class SampleActivity extends ActionBarActivity {

    private ListView mListView;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setSelector(getDrawable(R.drawable.list_selector));
        mListView.setAdapter(new ListAdapter());

        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mActionButton.setBackground(getDrawable(R.drawable.fab_background));
        mActionButton.setClickable(true);
//        mActionButton.setOnTouchListener(new DrawableHotspotTouch((LollipopDrawable) mActionButton.getBackground()));
    }

    public Drawable getDrawable2(int id){
        return LollipopDrawablesCompat.getDrawable(getResources(), id, getTheme());
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

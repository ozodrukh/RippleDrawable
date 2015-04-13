package dreamers.sample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import codetail.graphics.compat.DrawablesContext;
import codetail.graphics.compat.ResourcesCompat;

public class SampleActivity extends ActionBarActivity {

    ListView mListView;

    private DrawablesContext mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(new ListAdapter());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(mContext = new DrawablesContext(newBase));
    }

    @Override
    public ResourcesCompat getResources() {
        return mContext.getResources();
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);

        ResourcesCompat compat = mContext.getResources();
        compat.setTheme(theme);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
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

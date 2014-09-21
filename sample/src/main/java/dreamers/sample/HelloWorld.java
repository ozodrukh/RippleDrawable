package dreamers.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import dreamers.graphics.RippleDrawable;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.HORIZONTAL;

public class HelloWorld extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        RippleDrawable.createRipple(decor, 0xffdddddd);

        RecyclerView recyclerView = new RecyclerView(this);
        setContentView(recyclerView, new LayoutParams(MATCH_PARENT, MATCH_PARENT));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, HORIZONTAL, false));
        recyclerView.setAdapter(new RecyclerAdapter());
    }



    public static class Holder extends RecyclerView.ViewHolder{

        TextView mNumberView;

        public Holder(View itemView) {
            super(itemView);
            mNumberView = (TextView) itemView.findViewById(R.id.number);
        }

        public void setText(Number number){
            mNumberView.setText( String.valueOf(number.intValue()) );
        }
    }

    public static class RecyclerAdapter extends RecyclerView.Adapter<Holder>{

        public List<Integer> mItems = new ArrayList<Integer>();

        public RecyclerAdapter() {
            for(int index = 0; index < 100; index++){
                mItems.add(index);
            }
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup group, int i) {
            View v = LayoutInflater.from(group.getContext())
                    .inflate(R.layout.layout_item, group, false);

            RippleDrawable.createRipple(v, 0xff3f51b5);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int i) {
            holder.setText(mItems.get(i));
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

    }

}

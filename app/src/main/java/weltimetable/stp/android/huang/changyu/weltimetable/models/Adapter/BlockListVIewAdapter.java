package weltimetable.stp.android.huang.changyu.weltimetable.models.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import weltimetable.stp.android.huang.changyu.weltimetable.R;
import weltimetable.stp.android.huang.changyu.weltimetable.components.activitys.BlockActivity;
import weltimetable.stp.android.huang.changyu.weltimetable.models.BlockModel;
import weltimetable.stp.android.huang.changyu.weltimetable.utils.DbHelper;


public class BlockListVIewAdapter extends ArrayAdapter<BlockModel> {
    private Context mContext;
    private int resourceId;

    public BlockListVIewAdapter(Context context, int textViewResourceId,
                                List<BlockModel> objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        resourceId = textViewResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BlockModel BM = getItem(position); // getCurrent Instence
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView blockTime = (TextView) view.findViewById(R.id.block_time);
        blockTime.setText(BM.getTime());
        String color = getColorByCheck(BM);
        if (color == null) return view;
        view.setBackgroundColor(Color.parseColor(color));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "poesition: x= " + BM.getRow() + " Y = " + BM.getColumn(), Toast.LENGTH_SHORT).show();
                if (!BM.getCheck()) {
                    view.setBackgroundColor(Color.parseColor(BM.CHECKED_COLOR));
                } else {
                    view.setBackgroundColor(Color.parseColor(BM.UNCHECKED_COLOR));
                }
                BM.setCheck(!BM.getCheck());
            }
        });
        return view;
    }

    public String getColorByCheck(BlockModel bm) {
        if (bm == null) {
            return bm.getDefaultColor();
        }
        if (bm.getCheck()) {
            return bm.CHECKED_COLOR;
        }
        return bm.UNCHECKED_COLOR;
    }
}

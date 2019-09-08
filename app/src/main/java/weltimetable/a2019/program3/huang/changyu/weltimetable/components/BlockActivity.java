package weltimetable.a2019.program3.huang.changyu.weltimetable.components;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import weltimetable.a2019.program3.huang.changyu.weltimetable.R;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.BlockListVIewAdapter;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.BlockModel;
import weltimetable.a2019.program3.huang.changyu.weltimetable.models.Poestiton;

public class BlockActivity extends AppCompatActivity {
    private ListView mlistViewTime;
    private ListView mlistViewMonday;
    private ListView mlistViewTuesday;
    private ListView mlistViewWednesday;
    private ListView mlistViewThursday;
    private ListView mlistViewFriday;
    private Button Bt_submit;
    private List<BlockModel> blockListMon = new ArrayList<BlockModel>();
    private List<BlockModel> blockListTues = new ArrayList<BlockModel>();
    private List<BlockModel> blockListWed = new ArrayList<BlockModel>();
    private List<BlockModel> blockListThus = new ArrayList<BlockModel>();
    private List<BlockModel> blockListFri = new ArrayList<BlockModel>();
    private List blockLists = new ArrayList<ArrayList>();

    public HashMap getBlockListSets() {
        return blockListSets;
    }

    private HashMap blockListSets = new HashMap<Poestiton,BlockModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_block);
        Bt_submit = (Button) findViewById(R.id.Bt_submit);
        mlistViewMonday = (ListView) findViewById(R.id.LV_monlist);
        mlistViewTuesday = (ListView) findViewById(R.id.LV_tuelist);
        mlistViewWednesday = (ListView) findViewById(R.id.LV_wedlist);
        mlistViewThursday = (ListView) findViewById(R.id.LV_thulist);
        mlistViewFriday = (ListView) findViewById(R.id.LV_fridaylist);
        initLists();
        setListInfo();

        Bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlockActivity.this.finish();
            }
        });
    }

    private void setListInfo() {
        mlistViewMonday.setAdapter(new BlockListVIewAdapter(BlockActivity.this, R.layout.block_item_layout, blockListMon));
        mlistViewTuesday.setAdapter(new BlockListVIewAdapter(BlockActivity.this, R.layout.block_item_layout, blockListTues));
        mlistViewWednesday.setAdapter(new BlockListVIewAdapter(BlockActivity.this, R.layout.block_item_layout, blockListWed));
        mlistViewThursday.setAdapter(new BlockListVIewAdapter(BlockActivity.this, R.layout.block_item_layout, blockListThus));
        mlistViewFriday.setAdapter(new BlockListVIewAdapter(BlockActivity.this, R.layout.block_item_layout, blockListFri));
    }

    private void initLists() {
        //init title
        BlockModel Monday = new BlockModel("Mon", "#0088CC",1);
        BlockModel Tuesday = new BlockModel("Tues","#DA4F49",2);
        BlockModel wednesday = new BlockModel("Wed","#FAA732",3);
        BlockModel Thursday = new BlockModel("Thur","#5BB75B",4);
        BlockModel friday = new BlockModel("Fri","#49afcd",5);

        blockListMon.add(Monday);
        blockListTues.add(Tuesday);
        blockListWed.add(wednesday);
        blockListThus.add(Thursday);
        blockListFri.add(friday);
        blockLists.add(blockListMon);
        blockLists.add(blockListTues);
        blockLists.add(blockListWed);
        blockLists.add(blockListThus);
        blockLists.add(blockListFri);
        //init table
        for (Object item: blockLists) {
            ArrayList list =  (ArrayList)item;
            BlockModel headerBM = (BlockModel) list.get(0);
            for (int i = 8; i < 18; i++) {
                String hour = i + "";
                if (i < 10) hour = "0" + hour;
                BlockModel BM = new BlockModel(i + ":00");
                BM.setColumn(i-7);
                BM.setRow(headerBM.getDayOfweek());
                list.add(BM);
            }
        }
    }


}

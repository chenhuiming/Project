package cirlcle.cn.pipichen;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class ScrollingActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLinearLayout = (LinearLayout) findViewById(R.id.yuandian);
        initYuanDian();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mYuandianHandler.removeCallbacks(mYuandianRunable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private final int SIZE = 3;

    /**
     * 获取数据
     */
    private void initYuanDian() {
        //设置图片
        ImageView imageView;
        View view;
        for(int index = 0; index < SIZE; index++) {
            //创建底部指示器(小圆点)
            view = new View(this);
            view.setBackgroundResource(R.drawable.background);
            view.setEnabled(false);
            //设置宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            //设置间隔
            if (index != 0) {
                layoutParams.leftMargin = 10;
            }
            //添加到LinearLayout
            mLinearLayout.addView(view, layoutParams);

        }

        mYuandianHandler.post(mYuandianRunable);
    }

    Handler mYuandianHandler = new Handler(Looper.myLooper());
    private int mIndex = 0;


    Runnable mYuandianRunable = new Runnable() {
        @Override
        public void run() {
            setIndex(mIndex);
            if(++mIndex == 3) {
                mIndex = 0;
            }
            mYuandianHandler.removeCallbacks(mYuandianRunable);
            mYuandianHandler.postDelayed(mYuandianRunable, 1000);
        }
    };

    private void setIndex(int select) {
        for(int index = 0; index< SIZE; index++) {
            if(index == select) {
                mLinearLayout.getChildAt(index).setEnabled(true);
            } else {
                mLinearLayout.getChildAt(index).setEnabled(false);
            }
        }
    }


}

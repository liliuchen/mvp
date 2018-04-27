package com.llc.framework.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.llc.framework.R;
import com.llc.framework.manager.activity.ActivityManager;
import com.llc.framework.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *@package  com.crm.framework.ui.activity.main
 *@fileName WelcomeActivity
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 欢迎界面
 *@company  棋至文化广播有限公司
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_welcome_vp_controller)
    ViewPager mVpController;
    @BindView(R.id.activity_welcome_btn_jump)
    TextView mTvJump;
    @BindView(R.id.activity_welcome_btn_start_now)
    TextView mTvStartNow;
    List<View> mImageList;
    int[] mImageIds = {R.drawable.main_welcome_0, R.drawable.main_welcome_1, R.drawable.main_welcome_2};
    boolean mIsAutoCut = false;
    public static String IS_AUTO_CUT_FLAG = "WelcomeActivity_IS_AUTO_CUT_FLAG";

    /**
     * 启动欢迎界面
     *
     * @param context   上下文
     * @param isAutoCut 是否自动滑动
     */
    public static void show(Context context, boolean isAutoCut) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.putExtra(IS_AUTO_CUT_FLAG, isAutoCut);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initView();
        initData();
        setListener();


    }

    private void setListener() {
        mVpController.setAdapter(new ViewPagerAdapter(this, mImageList));
        mVpController.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvJump.setVisibility(View.GONE);
                mTvStartNow.setVisibility(View.GONE);
                if (position == 0) {
                    mTvJump.setVisibility(View.VISIBLE);
                } else if (position == mImageIds.length - 1) {
                    mTvStartNow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTvJump.setOnClickListener(this);
        mTvStartNow.setOnClickListener(this);

    }


    private void initData() {
        mIsAutoCut = getIntent().getBooleanExtra(IS_AUTO_CUT_FLAG, false);
        SharedPreferencesHelper.setFirstStart(this, false);
        mImageList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            switch (i) {
                case 0:
                    imageView.setImageResource(mImageIds[0]);
                    break;
                case 1:
                    imageView.setImageResource(mImageIds[1]);
                    break;
                case 2:
                    imageView.setImageResource(mImageIds[2]);
                    break;
            }
            mImageList.add(imageView);

        }
    }

    private void initView() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        MainActivity.show(WelcomeActivity.this);
        finish();
    }

    class ViewPagerAdapter extends PagerAdapter {
        Context context;
        List<View> list;

        public ViewPagerAdapter(Context context, List<View> list) {
            this.context = context;
            this.list = list;
        }

        private ViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            if (list == null) return 0;
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * Create the page for the given position.  The adapter is responsible
         * for adding the view to the container given here, although it only
         * must ensure this is done by the time it returns from
         * {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View in which the page will be shown.
         * @param position  The page position to be instantiated.
         * @return Returns an Object representing the new page.  This does not
         * need to be a View, but can be some other container of the page.
         */
        @Override
        public View instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        /**
         * Remove a page for the given position.  The adapter is responsible
         * for removing the view from its container, although it only must ensure
         * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
         *
         * @param container The containing View from which the page will be removed.
         * @param position  The page position to be removed.
         * @param object    The same object that was returned by
         *                  {@link #instantiateItem(View, int)}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(list.get(position));
        }
    }
}

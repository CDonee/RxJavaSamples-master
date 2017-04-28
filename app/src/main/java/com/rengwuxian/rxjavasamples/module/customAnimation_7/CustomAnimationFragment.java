package com.rengwuxian.rxjavasamples.module.customAnimation_7;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rengwuxian.rxjavasamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.rengwuxian.rxjavasamples.R.id.iv_man;

/**
 * @author DaiJiCheng
 * @time 2017/4/28  14:49
 * @desc ${自定义动画框架}
 */
public class CustomAnimationFragment extends Fragment {

    @Bind(iv_man)
    ImageView mIvMan;
    @Bind(R.id.parallax_container)
    ParallaxContainer mParallaxContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_anim, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mParallaxContainer.setUp(
                new int[]{
                        R.layout.view_intro_1,
                        R.layout.view_intro_2,
                        R.layout.view_intro_3,
                        R.layout.view_intro_4,
                        R.layout.view_intro_5,
                        R.layout.view_login
                }
        );
        mIvMan.setBackgroundResource(R.drawable.man_run);
        mParallaxContainer.setIv_man(mIvMan);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

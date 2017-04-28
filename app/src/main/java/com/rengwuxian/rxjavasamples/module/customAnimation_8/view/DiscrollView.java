package com.rengwuxian.rxjavasamples.module.customAnimation_8.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class DiscrollView extends ScrollView {
	
	private DiscrollViewContent mContent;

	public DiscrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		View content = getChildAt(0);
		mContent = (DiscrollViewContent)content;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		View first = mContent.getChildAt(0);
		first.getLayoutParams().height = getHeight();
	}
	
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		
		int scrollViewHeight = getHeight();
		//��������----�ӿ�---->����DiscrollViewContent������
		for(int i=0;i<mContent.getChildCount();i++){//����MyLinearLayout���������ӿؼ�(MyViewGroup)
			View child = mContent.getChildAt(i);
			if(!(child instanceof DiscrollvableInterface)){
				continue;
			}
			
			//ratio:0~1
			DiscrollvableInterface discrollvableInterface =  (DiscrollvableInterface) child;
			//1.child��scrollview�����ĸ߶� a
			int discrollvableTop = child.getTop();
			int discrollvableHeight = child.getHeight();
			
			//2.�õ�scrollview����ȥ�ĸ߶�  b  ����int t,
			//3.�õ�child����Ļ�����ĸ߶�  c
			int discrollvableAbsoluteTop = discrollvableTop - t;
			//ʲôʱ��ִ�ж�������child������Ļ��ʱ��
			if(discrollvableAbsoluteTop<=scrollViewHeight){
				int visibleGap = scrollViewHeight - discrollvableAbsoluteTop;
				//ȷ��ratio����0~1��������1 Ҳ����Ϊ1
				discrollvableInterface.onDiscrollve(clamp(visibleGap/(float)discrollvableHeight, 1f,0f));
			}else{//���򣬾ͻָ���ԭ����λ��
				discrollvableInterface.onResetDiscrollve();
			}
		}
	}
	
	public static float clamp(float value, float max, float min){
		return Math.max(Math.min(value, max), min);
	}

}

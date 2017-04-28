package com.rengwuxian.rxjavasamples.module.customAnimation_8.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class DiscrollvableView extends FrameLayout implements DiscrollvableInterface{

	/**
	 *  <attr name="discrollve_translation">
        <flag name="fromTop" value="0x01" />
        <flag name="fromBottom" value="0x02" />
        <flag name="fromLeft" value="0x04" />
        <flag name="fromRight" value="0x08" />
    	</attr>
    	0000000001
    	0000000010
    	0000000100
    	0000001000
    	
    	
    	0000000101
	 */
	private static final int TRANSLATION_FROM_TOP = 0x01;
	private static final int TRANSLATION_FROM_BOTTOM = 0x02;
	private static final int TRANSLATION_FROM_LEFT = 0x04;
	private static final int TRANSLATION_FROM_RIGHT = 0x08;
	
	//��ɫ��ֵ��
	private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();
	/**
	 * �Զ������Ե�һЩ���յı���
	 */
	private int mDiscrollveFromBgColor;//������ɫ�仯��ʼֵ
	private int mDiscrollveToBgColor;//������ɫ�仯����ֵ
	private boolean mDiscrollveAlpha;//�Ƿ���Ҫ͸���ȶ���
	private int mDisCrollveTranslation;//ƽ��ֵ
	private boolean mDiscrollveScaleX;//�Ƿ���Ҫx�᷽������
	private boolean mDiscrollveScaleY;//�Ƿ���Ҫy�᷽������
	private int mHeight;//��view�ĸ߶�
	private int mWidth;//���

	public void setmDiscrollveFromBgColor(int mDiscrollveFromBgColor) {
		this.mDiscrollveFromBgColor = mDiscrollveFromBgColor;
	}

	public void setmDiscrollveToBgColor(int mDiscrollveToBgColor) {
		this.mDiscrollveToBgColor = mDiscrollveToBgColor;
	}

	public void setmDiscrollveAlpha(boolean mDiscrollveAlpha) {
		this.mDiscrollveAlpha = mDiscrollveAlpha;
	}

	public void setmDisCrollveTranslation(int mDisCrollveTranslation) {
		this.mDisCrollveTranslation = mDisCrollveTranslation;
	}

	public void setmDiscrollveScaleX(boolean mDiscrollveScaleX) {
		this.mDiscrollveScaleX = mDiscrollveScaleX;
	}

	public void setmDiscrollveScaleY(boolean mDiscrollveScaleY) {
		this.mDiscrollveScaleY = mDiscrollveScaleY;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
		onResetDiscrollve();
	}
	
	
	public DiscrollvableView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DiscrollvableView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDiscrollve(float ratio) {
		// ratio:0~1
		//��������Ķ�������
		if(mDiscrollveAlpha){
			setAlpha(ratio);
		}
		if(mDiscrollveScaleX){
			setScaleX(ratio);
		}
		if(mDiscrollveScaleY){
			setScaleY(ratio);
		}
		
//		int mDisCrollveTranslation �кܶ���ö�ٵ����
		
		//�жϵ�������һ��ֵ��fromTop,fromBottom,fromLeft,fromRight
		//fromBottom
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_BOTTOM)){
			setTranslationY(mHeight*(1-ratio));//mHeight-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_TOP)){
			setTranslationY(-mHeight*(1-ratio));//-mHeight-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_LEFT)){
			setTranslationX(-mWidth*(1-ratio));//-width-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_RIGHT)){
			setTranslationX(mWidth*(1-ratio));//width-->0(����ԭ����λ��)
		}
		
		//��ɫ���䶯��	
		if(mDiscrollveFromBgColor!=-1&&mDiscrollveToBgColor!=-1){
			//ratio=0.5 color=�м���ɫ
			setBackgroundColor((Integer) sArgbEvaluator.evaluate(ratio, mDiscrollveFromBgColor, mDiscrollveToBgColor));
		}
		
	}

	private boolean isDiscrollTranslationFrom(int translationMask) {
		if(mDisCrollveTranslation==-1){
			return false;
		}
		//fromLeft|fromBottom & fromBottom = fromBottom
		return (mDisCrollveTranslation & translationMask)==translationMask;
	}

	@Override
	public void onResetDiscrollve() {
		//��������Ķ�������
		if(mDiscrollveAlpha){
			setAlpha(0);
		}
		if(mDiscrollveScaleX){
			setScaleX(0);
		}
		if(mDiscrollveScaleY){
			setScaleY(0);
		}
		
//		int mDisCrollveTranslation �кܶ���ö�ٵ����
		
		//�жϵ�������һ��ֵ��fromTop,fromBottom,fromLeft,fromRight
		//fromBottom
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_BOTTOM)){
			setTranslationY(mHeight);//mHeight-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_TOP)){
			setTranslationY(-mHeight);//-mHeight-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_LEFT)){
			setTranslationX(-mWidth);//-width-->0(����ԭ����λ��)
		}
		if(isDiscrollTranslationFrom(TRANSLATION_FROM_RIGHT)){
			setTranslationX(mWidth);//width-->0(����ԭ����λ��)
		}
		
	}

}

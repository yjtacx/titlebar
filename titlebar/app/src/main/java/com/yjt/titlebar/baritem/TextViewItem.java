package com.yjt.titlebar.baritem;

import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.yjt.titlebar.TitleBarView;
import com.yjt.titlebar.TitleBarConfig;
import com.yjt.titlebar.barHelper.BarPosition;
import com.yjt.titlebar.barentity.BarTextEntity;

/**
 * Created by yujiangtao on 16/4/11.
 */
    public class TextViewItem extends BarItem {
    protected TextView textView=null;
    protected int textcolor;
    protected String text;
    protected boolean isbacktext=false;
    protected int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            TitleBarConfig.DEFAULT_ITEM_TEXT_PADDING,
            mcontext.getResources().getDisplayMetrics());


    public TextViewItem(TitleBarView titleBarView, BarTextEntity textbean){
        super(titleBarView);
        this.bp=textbean.barPosition;
        this.text=textbean.text;
        this.id=textbean.id;
         this.textcolor=textbean.textColor;
        this.clickable=textbean.clickable;
        this.isbacktext = textbean.isbacktext;
        textView = new TextView(mcontext);
        barType= textbean.itemtype;
    }

    protected void initTextView(BarPosition bp){

    }

    @Override
    protected void buildView() {
        RelativeLayout.LayoutParams lp;
        switch(bp){
            case Left:
                lp=getLeftLayoutParams();
                textView.setPadding(padding, 0, padding, 0);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        TitleBarConfig.DEFAULT_TEXTSIZE_BUTTON);
                if(isbacktext){
                    Drawable drawable= mcontext.getResources().getDrawable(
                            TitleBarConfig.DEFAULT_BACK_BUTTON_RES);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable,null,null,null);
                }
                break;
            case Right:
               lp=getRightLayoutParams();
                textView.setPadding(padding, 0, padding, 0);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        TitleBarConfig.DEFAULT_TEXTSIZE_BUTTON);
                break;
            case Center:
                lp=getCenterLayoutParams();
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        TitleBarConfig.DEFAULT_TEXTSIZE_TITLE);
                break;
            default:throw new RuntimeException("BarPosition 不存在");

        }

        textView.setLayoutParams(lp);

        textView.setTextColor(textcolor);
        textView.setGravity(Gravity.CENTER);
        textView.setId(id);
        textView.setText(text);
        if(clickable) {
            textView.setClickable(true);
            textView.setOnClickListener(titleBarView);
            textView.setBackgroundDrawable(backstateD);
        }
        initTextView(bp);
    }

    @Override
    protected View getItemView() {
        return textView;
    }

    @Override
    protected int getWidth() {
        return  RelativeLayout.LayoutParams.WRAP_CONTENT;
    }


}

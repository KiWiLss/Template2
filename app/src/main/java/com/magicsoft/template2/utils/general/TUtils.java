package com.magicsoft.template2.utils.general;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicsoft.template2.R;


public class TUtils {
    public static final String TAG="MMM";
    private static Toast mToast;
    private volatile static TUtils instance;
    private static CharSequence mText;
    private TUtils(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.eplay_toast, null);
        if (mToast==null){
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(v);
        }
    }

    public TUtils setText(CharSequence text,int type){
        if (mToast!=null&& !TextUtils.isEmpty(text)){
            View view = mToast.getView();
            TextView tv = view.findViewById(R.id.tv_toast_text);
            tv.setText(text);
            //设置图片的类型
            ImageView imgIcon = view.findViewById(R.id.img_toast_icon);



        }
        return this;
    }





    public static TUtils makeText(Context context) {
        if (instance==null){
            synchronized (TUtils.class){
                if (instance==null) {
                    return new TUtils(context);
                }
            }
        }
       return instance;
    }



    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }
    public TUtils setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
        return this;
    }

    public void cancel(){
        if (mToast!=null){
            mToast.cancel();
        }
    }



}
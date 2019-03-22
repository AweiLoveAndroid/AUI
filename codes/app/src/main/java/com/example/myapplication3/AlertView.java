package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * 高仿IOS的AlertView
 */
@SuppressLint("AppCompatCustomView")
public class AlertView{

    private Context context;
    private Dialog dialog;

    // 普通两个按钮布局使用的控件
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private View img_line;
    private ListView listView;

    // 多个按钮布局使用的控件
    private LinearLayout lLayout_bg2;
    private TextView txt_title2;
    private TextView txt_msg2;
    private Button btn_neg2;
    private Button btn_pos2;

    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean isMultiGroup = false;

    public AlertView(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public AlertView builder() {
        builder(false);
        return this;
    }

    public AlertView builder(boolean isMultiGroup) {
        View view = null;
        View view2 = null;
        this.isMultiGroup = isMultiGroup;
        if(isMultiGroup == false){
            view = LayoutInflater.from(context).inflate(
                    R.layout.view_alert_dialog, null);
            lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_msg = (TextView) view.findViewById(R.id.txt_msg);
            btn_neg = (Button) view.findViewById(R.id.btn_neg);
            btn_pos = (Button) view.findViewById(R.id.btn_pos);
            img_line = (View) view.findViewById(R.id.img_line);
        }else{ // 按钮超过2个
            view2 = LayoutInflater.from(context).inflate(
                    R.layout.view_alert_dialog_muiltgroup, null);
            lLayout_bg2 = (LinearLayout) view2.findViewById(R.id.lLayout_bg2);
            txt_title2 = (TextView) view2.findViewById(R.id.txt_title2);
            txt_msg2 = (TextView) view2.findViewById(R.id.txt_msg2);
            listView = (ListView) view2.findViewById(R.id.lv_multi);
            btn_neg2 = (Button) view2.findViewById(R.id.btn_neg2);
            btn_pos2 = (Button) view2.findViewById(R.id.btn_pos2);
        }
        setGone();
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        if(isMultiGroup == false) {
            dialog.setContentView(view);
            lLayout_bg.setLayoutParams(
                    new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                            FrameLayout.LayoutParams.WRAP_CONTENT));
        }else{
            dialog.setContentView(view2);
            lLayout_bg2.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }
        return this;
    }

    /**
     * 恢复初始
     * @return
     */
    public AlertView setGone() {
        if (lLayout_bg != null) {
            txt_title.setVisibility(View.GONE);
            txt_msg.setVisibility(View.GONE);
            btn_neg.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            img_line.setVisibility(View.GONE);

        }
        if (lLayout_bg2 != null) {
            txt_title2.setVisibility(View.GONE);
            txt_msg2.setVisibility(View.GONE);
            btn_pos2.setVisibility(View.GONE);
//            listView.setVisibility(View.GONE);
        }
        showTitle = false;
        showMsg = false;
        showPosBtn = false;
        showNegBtn = false;
        return this;
    }

    /**
     * 设置title
     * @param title
     * @return
     */
    public AlertView setTitle(String title) {
        showTitle = true;
        if (isMultiGroup==true){
            if (TextUtils.isEmpty(title)) {
                txt_title2.setText("提示");
            } else {
                txt_title2.setText(title);
            }
        } else{
            if (TextUtils.isEmpty(title)) {
                txt_title.setText("提示");
            } else {
                txt_title.setText(title);
            }
        }
        return this;
    }

    /**
     * 设置Message
     * @param msg
     * @return
     */
    public AlertView setMsg(String msg) {
        showMsg = true;
        if (isMultiGroup==true){
            if (TextUtils.isEmpty(msg)) {
                txt_msg2.setText("提示");
            } else {
                txt_msg2.setText(msg);
            }
        } else{
            if (TextUtils.isEmpty(msg)) {
                txt_msg.setText("提示");
            } else {
                txt_msg.setText(msg);
            }
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     * @param cancel
     * @return
     */
    public AlertView setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 右侧按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public AlertView setPositiveButton(String text,
                                         final View.OnClickListener listener) {
        return setPositiveButton(text, -1, listener);
    }

    public AlertView setPositiveButton(String text, int color,
                                         final View.OnClickListener listener) {
        showPosBtn = true;
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        if (isMultiGroup==true){
            if ("".equals(text)) {
                btn_pos2.setText("");
            } else {
                btn_pos2.setText(text);
            }
            btn_pos2.setTextColor(ContextCompat.getColor(context, color));

            btn_pos2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick(v);
                    dismiss();
                }
            });
        }else{
            if ("".equals(text)) {
                btn_pos.setText("");
            } else {
                btn_pos.setText(text);
            }
            btn_pos.setTextColor(ContextCompat.getColor(context, color));
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onClick(v);
                    dismiss();
                }
            });
        }
        return this;
    }

    /**
     * 左侧按钮
     *
     * @param text
     * @param listener
     * @return
     */

    public AlertView setNegativeButton(String text,
                                         final View.OnClickListener listener) {

        return setNegativeButton(text, -1, listener);
    }

    public AlertView setNegativeButton(String text, int color,
                                         final View.OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("");
        } else {
            btn_neg.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_blue;
        }
        btn_neg.setTextColor(ContextCompat.getColor(context, color));

        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置显示
     */
    private void setLayout() {
        if(isMultiGroup==false){ // 两个按钮
            if (!showTitle && !showMsg) {
                txt_title.setText("");
                txt_title.setVisibility(View.VISIBLE);
            }

            if (showTitle) {
                txt_title.setVisibility(View.VISIBLE);
            }

            if (showMsg) {
                txt_msg.setVisibility(View.VISIBLE);
            }

            if (!showPosBtn && !showNegBtn) {
                btn_pos.setText("");
                btn_pos.setVisibility(View.VISIBLE);
                btn_pos.setBackgroundResource(R.drawable.alert_dialog_selector);
                btn_pos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }

            if (showPosBtn && showNegBtn) {
                btn_pos.setVisibility(View.VISIBLE);
                btn_pos.setBackgroundResource(R.drawable.alert_dialog_right_selector);
                btn_neg.setVisibility(View.VISIBLE);
                btn_neg.setBackgroundResource(R.drawable.alert_dialog_left_selector);
                img_line.setVisibility(View.VISIBLE);
            }

            if (showPosBtn && !showNegBtn) {
                btn_pos.setVisibility(View.VISIBLE);
                btn_pos.setBackgroundResource(R.drawable.alert_dialog_selector);
            }

            if (!showPosBtn && showNegBtn) {
                btn_neg.setVisibility(View.VISIBLE);
                btn_neg.setBackgroundResource(R.drawable.alert_dialog_selector);
            }
        }else{ // 多个按钮
            if (!showTitle && !showMsg) {
                txt_title2.setText("");
                txt_title2.setVisibility(View.VISIBLE);
            }
            if (showTitle) {
                txt_title2.setVisibility(View.VISIBLE);
            }
            if (showMsg) {
                txt_msg2.setVisibility(View.VISIBLE);
            }

            if (showPosBtn) {
                btn_pos2.setVisibility(View.VISIBLE);
                btn_pos2.setBackgroundResource(R.drawable.alert_dialog_selector);
                btn_pos2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }

    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null) {
            if (dialog.isShowing())
                return true;
            else
                return false;
        }
        return false;
    }

    public void dismiss() {
        if (dialog!=null){
            dialog.dismiss();
        }

    }

    /**
     * 设置多组按钮
     */
    public AlertView setMultiGroupBtn(final List<Object> data){
        listView.setDividerHeight(1);
        listView.setAdapter(new MyAdapter(context,data));
        return this;
    }

    // 四种自带风格
    enum AlertViewStyle{
        MORMAL, // 默认风格，无输入框 UIAlertViewStyleDefault
        PASSWORD_INPUT, // 密码输入风格 UIAlertViewStyleSecureTextInput
        NORMAL_INPUT, // 普通输入框 UIAlertViewStylePlainTextInput
        Login_PASSWORD_INPUT // 账号密码框风格 UIAlertViewStyleLoginAndPasswordInput
    }

    class MyAdapter extends  BaseAdapter{

        private List<Object> mData;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<Object> data) {
            mData = data;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = mInflater.inflate(R.layout.item_listview,parent,false);
            Button btnBeg2 = (Button) view.findViewById(R.id.btn_neg2);
            btnBeg2.setText(mData.get(position).toString());
            btnBeg2.setBackgroundResource(R.drawable.alert_dialog_left_selector);
            return view;
        }
    }
}
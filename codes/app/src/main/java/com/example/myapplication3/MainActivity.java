package com.example.myapplication3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AlertView alertView;
    AlertView alertView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        alertView = new AlertView(MainActivity.this).builder();
        alertView2 = new AlertView(MainActivity.this).builder(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(MainActivity.this);
                localBuilder.setTitle("简单对话框");
                localBuilder.setIcon(R.mipmap.ic_launcher);
                localBuilder.setMessage("提示信息？");
                localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
                localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });

                /***
                 * 设置点击返回键不会消失
                 * */
                localBuilder.setCancelable(false).create();

                localBuilder.show();
                break;
            case R.id.btn2:
                alertView.setGone()
                        .setTitle("提示")
                        .setMsg("基本使用")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,
                                        "点击了确定", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.btn3:
                alertView.setGone()
                        .setMsg("修改按钮颜色")
                        .setNegativeButton("取消", R.color.colorAccent,null)
                        .setPositiveButton("确定", R.color.colorAccent,null)
                        .show();
                break;
            case R.id.btn4:
                alertView.setGone()
                        .setMsg("弹出确定按钮")
                        .setPositiveButton("确定",null)
                        .show();
                break;
            case R.id.btn5:
                alertView.setGone()
                        .setMsg("弹出取消按钮")
                        .setNegativeButton("取消",null)
                        .setCancelable(true)
                        .show();
                break;
            case R.id.btn6:
                ArrayList arrayList = new ArrayList();
                arrayList.add("按钮1");
                arrayList.add("按钮2");
                arrayList.add("按钮3");
                arrayList.add("按钮4");
                arrayList.add("按钮5");
                arrayList.add("按钮6");
                alertView2.setGone()
                        .setTitle("弹出多个按钮")
                        .setMsg("这是弹出取消按钮的示例")
                        .setMultiGroupBtn(arrayList)
                        .setPositiveButton("取消",null)
                        .setCancelable(true)
                        .show();
                break;
        }
    }
}

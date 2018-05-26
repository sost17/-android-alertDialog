package com.example.myalertdialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView old_name;
    Button bt_change_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        old_name = (TextView) findViewById(R.id.tv_name);
        bt_change_name = (Button) findViewById(R.id.bt_name);

        bt_change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取自定义AlertDialog布局文件的view
                LinearLayout change_name = (LinearLayout) getLayoutInflater()
                        .inflate(R.layout.my_dialog, null);
                TextView tv_name_dialog = (TextView) change_name.findViewById(R.id.tv_name_dialog);
                //由于EditText要在内部类中对其进行操作，所以要加上final
                final EditText et_name_dialog = (EditText) change_name.findViewById(R.id.et_name_dialog);

                //设置AlertDialog中TextView和EditText显示Activity中TextView的内容
                tv_name_dialog.setText(old_name.getText().toString());
                et_name_dialog.setText(old_name.getText().toString());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("修改用户名")
                        .setView(change_name)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //将Activity中的textview显示AlertDialog中EditText中的内容
                                //并且用Toast显示一下
                                old_name.setText(et_name_dialog.getText().toString());
                                Toast.makeText(MainActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                        .setNegativeButton("取消", null)
                        .create()
                        .show();

            }
        });


    }
}

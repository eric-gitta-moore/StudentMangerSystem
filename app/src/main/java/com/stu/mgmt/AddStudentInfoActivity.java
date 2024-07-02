package com.stu.mgmt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.stu.mgmt.bean.StudentInfo;
import com.stu.mgmt.dao.AddStudentInfoDao;

public class AddStudentInfoActivity extends Activity {
    //成员变量
    EditText editnum, editname, editage, editmark;
    RadioButton radiomen, radiowomen;
    ArrayAdapter<String> proadapter;
    String[] proname = {"计算机应用", "计算机网络", "移动互联开发", "WEB前端开发"};
    Spinner pro;
    Button butok, butre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_info);
        //调用init()方法
        this.init();
        //提交按钮事件添加监听
        this.butok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View V) {
                //提交按钮的时间处理代码
                addaction();
            }
        });
        //为清空按钮事件添加监听
        this.butre.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                qingkongaction();
            }
        });
    }

    //初始化声明的控件对象
    public void init() {
        this.editnum = findViewById(R.id.addnumedit);
        this.editname = findViewById(R.id.addnameedit);
        this.radiomen = findViewById(R.id.addradioman);
        this.radiowomen = findViewById(R.id.addradiowoman);
        this.editage = findViewById(R.id.addageedit);
        this.pro = findViewById(R.id.spinner1);
        //构建适配器--数据源--显示格式
        this.proadapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, this.proname);
        //设置spinpro 数据源
        this.pro.setAdapter(proadapter);
        this.editmark = findViewById(R.id.addmarkedit);
        this.butok = findViewById(R.id.addbutton);
        this.butre = findViewById(R.id.resbutton);

    }

    //添加按钮事件处理方法
    public void addaction() {
        //1.获取用户输入的信息
        String num = this.editnum.getText().toString();
//    			//nn为空判读
//    			double nn=Integer.parseInt(num);
        String name = this.editname.getText().toString();
        String sex = "男";
        if (this.radiowomen.isChecked()) {
            //
            sex = "女";
        }
        String age = this.editage.getText().toString();
        String pro = this.pro.getSelectedItem().toString();
        String mark = this.editmark.getText().toString();
        //2.调用相关 存储添加学生信息
        StudentInfo tem = new StudentInfo();
        tem.setNum(num);
        tem.setName(name);
        tem.setSex(sex);
        tem.setAge(age);
        tem.setPro(pro);
        tem.setMark(mark);
        AddStudentInfoDao adao = new AddStudentInfoDao(this);
        long n = adao.addStudentInfo(tem);
        //3.根据结果显示
        String mes = "学生信息添加失败";
        if (n > 0) {
            mes = "学生信息添加成功";
        }
        Toast.makeText(this, mes, Toast.LENGTH_LONG).show();
    }

    //清空按钮事件处理方法
    public void qingkongaction() {
        this.editnum.setText("");
        this.editname.setText("");
        //设置性别默认为男进行选中显示
        this.radiomen.setChecked(true);
        this.editage.setText("");
        //设置下拉选择框的专业为选择框的第一个
        this.pro.setSelection(0);
        this.editmark.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

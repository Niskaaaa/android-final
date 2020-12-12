package com.example.notebook;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Data;
import presenter.MyDatabase;

public class NewNote extends AppCompatActivity{
    EditText ed_title;
    EditText ed_content;
    FloatingActionButton floatingActionButton;
    MyDatabase myDatabase;
    RadioGroup ed_type;
    RadioButton word;
    RadioButton group;
    RadioButton sentence;
    RadioGroup ed_imp;
    RadioButton imp1;
    RadioButton imp2;
    RadioButton imp3;

    Data data;
    int ids;
    String type;
    String imp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        ed_title = (EditText)findViewById(R.id.title);
        ed_content = (EditText)findViewById(R.id.content);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.finish);
        myDatabase = new MyDatabase(this);
        Intent intent = this.getIntent();
        ids = intent.getIntExtra("ids",0);

        if (ids != 0){
            data = myDatabase.getTiandCon(ids);
            ed_title.setText(data.getTitle());
            ed_content.setText(data.getContent());

        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSave();
            }
        });







    }

    protected void onStart() {
        super.onStart();
        ed_type = (RadioGroup) this.findViewById(R.id.type);
        word = (RadioButton) this.findViewById(R.id.word);
        group = (RadioButton) this.findViewById(R.id.group);
        sentence = (RadioButton) this.findViewById(R.id.sentence);



        ed_imp = (RadioGroup) this.findViewById(R.id.imp);
        imp1 = (RadioButton) this.findViewById(R.id.imp1);
        imp2 = (RadioButton) this.findViewById(R.id.imp2);
        imp3 = (RadioButton) this.findViewById(R.id.imp3);
        setImp();
        setType();
        ed_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
    }

    @Override
    public void onBackPressed() {     //重写返回建方法，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd   HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        String title = ed_title.getText().toString();
        String content = ed_content.getText().toString();
        if(ids!=0){
            data=new Data(title,ids, content, time,type,imp);
            myDatabase.toUpdate(data);
            Intent intent=new Intent(NewNote.this,MainActivity.class);
            startActivity(intent);
            NewNote.this.finish();
        }
        //新建日记
        else{
            data=new Data(title,content,time,type,imp);
            myDatabase.toInsert(data);
            Intent intent=new Intent(NewNote.this,MainActivity.class);
            startActivity(intent);
            NewNote.this.finish();
        }

    }

    private void isSave(){   //写一个方法进行调用，如果是属于新建则插入数据表并返回主页面，如果是修改，修改表中数据并返回主页面
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH：mm");
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        Log.d("new_note", "isSave: "+time);
        String title = ed_title.getText().toString();
        String content = ed_content.getText().toString();
        if(ids!=0){
            data=new Data(title,ids, content, time,type,imp);
            myDatabase.toUpdate(data);
            Intent intent=new Intent(NewNote.this,MainActivity.class);
            startActivity(intent);
            NewNote.this.finish();
        }
        //新建日记
        else{
            data=new Data(title,content,time,type,imp);
            myDatabase.toInsert(data);
            Intent intent=new Intent(NewNote.this,MainActivity.class);
            startActivity(intent);
            NewNote.this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_lo,menu);
        return true;
    }

    public void setType() {
        if(word.isChecked()==true)
            type="word";
        else if (sentence.isChecked()==true)
            type="sentence";
        else if (group.isChecked()==true)
            type = "group";
        else{
            word.setChecked(true);
            type="word";
        }

    }
    public void setImp() {
        if(imp1.isChecked()==true)
            imp="imp1";
        else if (imp2.isChecked()==true)
            imp="imp2";
        else if(imp3.isChecked()==true)
            imp="imp3";
        else{
            imp1.setChecked(true);
            imp="imp1";
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_share :
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "标题："+ed_title.getText().toString()+"    " +
                                "内容："+ed_content.getText().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }


}

package com.example.bsz.fourth_app;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;


public class MainActivity extends AppCompatActivity {
    String FileName = "StuInfo.text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writer = (Button)this.findViewById(R.id.writer);
        writer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream out = null;
                try{
                    FileOutputStream fileInputStream =
                            openFileOutput(FileName,MODE_PRIVATE);
                    out = new BufferedOutputStream(fileInputStream);
                    try {
                        EditText editText = (EditText)findViewById(R.id.name);
                        Charset.forName("UTF-8");
                        out.write(editText.getText().toString().getBytes());
                        editText = (EditText)findViewById(R.id.stu_id);
                        out.write(editText.getText().toString().getBytes());
                    } catch (IOException e) {
                        Log.e("writer","writer error!Charset.forName(\"UTF-8\");");
                        e.printStackTrace();
                    }finally {
                        if(out != null)
                            out.close();
                    }
                } catch (IOException e) {
                    Log.e("writer","open file "+FileName+"error!");
                    e.printStackTrace();
                }
            }
        });

        Button reader = (Button)findViewById(R.id.reader);
        reader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputStream in = null;
                try {
                    FileInputStream fileInputStream =
                            openFileInput(FileName);
                    in = new BufferedInputStream(fileInputStream);

                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    while((c=in.read())!=-1){
                        stringBuilder.append((char)c);
                    }
                    TextView textView = (TextView)findViewById(R.id.show);
                    textView.setText(stringBuilder.toString());


                } catch (FileNotFoundException e) {
                    Log.e("reader","open file "+FileName+"error!");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("reader","read file "+FileName+"error!");
                    e.printStackTrace();
                }

            }
        });
    }
}

package tw.org.iii.iotest1;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView tv;
    private File sdroot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        sp = getSharedPreferences("gamedata", MODE_PRIVATE);
        editor = sp.edit();

        String state = Environment.getExternalStorageState();
        Log.v("brad", state);

    }

    public void test1(View view) {
        String name = sp.getString("name",
                "nobody");
        boolean isSound = sp.getBoolean("sound",
                true);
        tv.setText("Name: " + name + "\n" +
                "Sound: " + (isSound?"On":"Off"));

    }

    public void test2(View view) {
        editor.putString("name", "brad");
        editor.putBoolean("sound", false);
        editor.commit();
        Toast.makeText(this, "Save OK",
                Toast.LENGTH_SHORT).show();
    }

    // Inner Storage
    public void test3(View view) {
        try(FileOutputStream fout =
                    openFileOutput("test3.txt",
                            MODE_PRIVATE)){
            fout.write("Hello, Brad".getBytes());
            fout.flush();
            Toast.makeText(this,
                    "Save OK2", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }

    }

    public void test4(View view) {
        try(FileInputStream fin =
                    openFileInput("test3.txt")){
            int b; StringBuffer sb = new StringBuffer();
            while ( (b = fin.read()) != -1){
                sb.append((char)b);
            }

            Toast.makeText(this,
                    sb, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Log.v("brad", e.toString());
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){

        }

        return super.onKeyDown(keyCode, event);
    }

    public void test5(View view) {
    }

    public void test6(View view) {
    }
}

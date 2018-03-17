package tw.org.iii.iotest1;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
    private File sdroot, approot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }else{
            init();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // OK
            init();
        }else{
            // NO
            finish();
        }

    }

    private void init(){
       tv = findViewById(R.id.tv);
        sp = getSharedPreferences("gamedata", MODE_PRIVATE);
        editor = sp.edit();

        String state = Environment.getExternalStorageState();
        //Log.v("brad", state);
        sdroot = Environment.getExternalStorageDirectory();
        Log.v("brad", sdroot.getAbsolutePath());

        approot = new File(sdroot, "Android/data/" + getPackageName());
        if (!approot.exists()){
            approot.mkdirs();
        }

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
        File file1 = new File(sdroot, "brad.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello, Brad".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,
                    "Save OK3", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    public void test6(View view) {
        File file1 = new File(approot, "brad.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello, Brad".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,
                    "Save OK4", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    public void test7(View view) {
        File file1 = new File(sdroot, "brad.txt");
        if (file1.exists()){
            try {
                FileInputStream fileInputStream =
                        new FileInputStream(file1);
                byte[] buf = new byte[(int)file1.length()];
                fileInputStream.read(buf);
                fileInputStream.close();
                Toast.makeText(this,
                        new String(buf), Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.v("brad", e.toString());
            }
        }else{
            Toast.makeText(this,
                    "not exist", Toast.LENGTH_SHORT).show();
        }
    }
    public void test8(View view) {
        File file1 = new File(approot, "brad.txt");
        if (file1.exists()){
            try {
                FileInputStream fileInputStream =
                        new FileInputStream(file1);
                byte[] buf = new byte[(int)file1.length()];
                fileInputStream.read(buf);
                fileInputStream.close();
                Toast.makeText(this,
                        new String(buf), Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Log.v("brad", e.toString());
            }
        }else{
            Toast.makeText(this,
                    "not exist", Toast.LENGTH_SHORT).show();
        }
    }
}

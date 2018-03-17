package tw.org.iii.iotest1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        sp = getSharedPreferences("gamedata", MODE_PRIVATE);
        editor = sp.edit();

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
    }
}

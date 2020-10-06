package ratsoft.android.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void tombol(View v){
        String tag = v.getTag().toString();
        switch (tag){
            case "internal" :
                startActivity(new Intent( MainActivity.this,InternalActivity.class));
                break;
            case "external" :
                startActivity(new Intent( MainActivity.this,ExternalStorage.class));
                break;
        }
    }


}
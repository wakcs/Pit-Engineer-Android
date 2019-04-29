package pineapplez.pitengineer;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set app version on version text
        String version = "1.0";
        try{
            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(),0);
            version = pInfo.versionName;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        TextView txtVersion = findViewById(R.id.txtVersion);
        txtVersion.setText(version);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Back arrow to return to home
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

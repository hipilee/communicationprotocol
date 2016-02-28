package android.softfan;

import android.app.ActionBar;
import android.softfan.dataCenter.ApDataCenter;
import android.softfan.dataCenter.DataCenterClientService;
import android.softfan.dataCenter.DataCenterClientTestService;
import android.softfan.dataCenter.DataCenterProcess;
import android.softfan.dataCenter.config.DataCenterClientConfig;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActionBar actionBar = getActionBar();

        Log.e("error","start testing");
        DataCenterClientConfig config = new DataCenterClientConfig();
        config.setAddr("192.168.0.94");
        config.setPort(30014);
        config.setAp("xt");
        config.setOrg("*");
        config.setPassword("123456");
        config.setServerAp("DataCenter");
        config.setServerOrg("*");
        config.setProcessClass(DataCenterProcess.class);
        config.setPushThreadClass(DataCenterClientTestService.class);

        DataCenterClientService.startup(config);
        Log.e("error", "finish starting");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package sample.dev;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import sample.dev.dashboard.DashboardFragment;
import sample.dev.dashboard.DashboardItemGenerator;
import sample.dev.home.HomeFragment;
import sample.dev.notification.NotificationFragment;

public class MainActivity extends AppCompatActivity implements DashboardFragment.DashboardListener {

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
//                    mTextMessage.setText(R.string.title_home);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = HomeFragment.newInstance(1);
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.commit();
                    return true;
                }
                case R.id.navigation_dashboard: {
//                    mTextMessage.setText(R.string.title_dashboard);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = DashboardFragment.newInstance(1);
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.commit();
                    return true;
                }

                case R.id.navigation_notifications: {
//                    mTextMessage.setText(R.string.title_notifications);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = NotificationFragment.newInstance(1);
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.commit();
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = HomeFragment.newInstance(1);
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDashboardInteraction(DashboardItemGenerator.DummyItem item) {

    }
}

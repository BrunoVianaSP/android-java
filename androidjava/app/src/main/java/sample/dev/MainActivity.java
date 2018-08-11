package sample.dev;

import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import sample.dev.dashboard.DashboardFragment;
import sample.dev.dashboard.DashboardItemGenerator;
import sample.dev.home.HomeFragment;
import sample.dev.notification.NotificationFragment;
import sample.dev.notification.NotificationItemGenerator;
import sample.dev.place.PlaceOverviewFragment;
import sample.dev.product.ProductDetailFragment;
import sample.dev.user.ProfileFragment;
import sample.dev.user.SignupFragment;

public class MainActivity extends AppCompatActivity implements DashboardFragment.DashboardListener,
        HomeFragment.HomeListener,
        NotificationFragment.NotificationListener,
        SignupFragment.SignupFragmentListener,
        ProfileFragment.OnFragmentInteractionListener,
        PlaceOverviewFragment.PlaceFragmentListener,
        ProductDetailFragment.ProductDetailListener
{

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private Menu menu;

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
                    Fragment fragment = HomeFragment.newInstance("","");
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.commit();
                    return true;
                }

                case R.id.navigation_user: {
//                    mTextMessage.setText(R.string.title_dashboard);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = SignupFragment.newInstance("", "");
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

                case R.id.navigation_location: {
//                    mTextMessage.setText(R.string.title_dashboard);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = PlaceOverviewFragment.newInstance("","");
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });








        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = HomeFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
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
        } else if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }



    @Override
    public void onDashboardInteraction(DashboardItemGenerator.DummyItem item) {

    }

    @Override
    public void onNotificationInteraction(NotificationItemGenerator.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = ProfileFragment.newInstance("", "");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onHomeButtonPressed(int button) {
        log.info("onHomeInteraction: " + button);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (button) {
            case R.id.btnProductDetail: {
                Fragment fragment = ProductDetailFragment.newInstance("","");
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.commit();
               break;
            }
            default: {
                log.info("Invalid button pressed!");
                break;
            }
        }

    }
}

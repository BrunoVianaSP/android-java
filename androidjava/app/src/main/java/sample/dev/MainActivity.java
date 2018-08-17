package sample.dev;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.Objects;

import butterknife.ButterKnife;
import sample.dev.cadidate.CandidateFragment;
import sample.dev.settings.AboutFragment;
import sample.dev.settings.FavoriteFragment;
import sample.dev.settings.FavoriteContent;
import sample.dev.home.HomeFragment;
import sample.dev.settings.HelpFragment;
import sample.dev.settings.LegalTermsFragment;
import sample.dev.notification.NotificationFragment;
import sample.dev.notification.NotificationItemGenerator;
import sample.dev.place.PlaceOverviewFragment;
import sample.dev.product.ProductDetailFragment;
import sample.dev.quiz.QuizFragment;
import sample.dev.settings.SettingsFragment;
import sample.dev.settings.SuggestionFragment;
import sample.dev.user.ProfileFragment;
import sample.dev.user.SignupFragment;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.HomeListener,
        NotificationFragment.NotificationListener,
        SignupFragment.SignupFragmentListener,
        ProfileFragment.ProfileFragmentListener,
        PlaceOverviewFragment.PlaceFragmentListener,
        ProductDetailFragment.ProductDetailListener,
        CandidateFragment.CandidateFragmentListener,
        QuizFragment.QuizFragmentListener,
        LegalTermsFragment.LegalTermsFragmentListener,
        SettingsFragment.SettingsFragmentListener,
        FavoriteFragment.FavoriteFragmentListener,
        SuggestionFragment.SuggestionFragmentListener,
        AboutFragment.AboutFragmentListener,
        HelpFragment.HelpFragmentListener
{

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private Menu menu;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            LinearLayout layout = findViewById(R.id.main_container);
            layout.removeAllViews();



            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    showHomeFragment();
                    return true;
                }

                case R.id.navigation_user_profile: {
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Usuario");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = ProfileFragment.newInstance("", "");
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }

                case R.id.navigation_quiz: {
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Quiz de Afinidade");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = QuizFragment.newInstance(1);
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return true;
                }

                case R.id.navigation_candidates: {
                    Objects.requireNonNull(getSupportActionBar()).setTitle("Candidatos");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = CandidateFragment.newInstance(1);
                    fragmentTransaction.replace(R.id.main_container, fragment);
                    fragmentTransaction.addToBackStack(null);
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
        ButterKnife.bind(this);
        ConfigBottomNavigator();
        configFloatButton();
        showHomeFragment();
    }

    private void showHomeFragment() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = HomeFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    private void configFloatButton() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void ConfigBottomNavigator() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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

    public void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    public void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
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
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onHomeButtonPressed(int button) {
        log.info("onHomeInteraction: " + button);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        switch (button) {
//            case R.id.btnProductDetail: {
//                Fragment fragment = ProductDetailFragment.newInstance("","");
//                fragmentTransaction.replace(R.id.main_container, fragment);
//                fragmentTransaction.commit();
//               break;
//            }
//            default: {
//                log.info("Invalid button pressed!");
//                break;
//            }
//        }

    }

    @Override
    public void onBackPressed() {
//        offerExitApp();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fr = fragmentManager.findFragmentById(R.id.main_container);
        if(fr!=null && !(fr instanceof HomeFragment)){
            BottomNavigationView bottomNavigationView;
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        } else {
            offerExitApp();
        }

    }

    private void offerExitApp() {
        log.info("offerExitApp");
        String msg = "Deseja sair?";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        closeApp();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void closeApp() {
        MainActivity.this.finish();
    }

    @Override
    public void openEditProfile() {
        log.info("openEditProfile");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Editar perfil");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = SignupFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openLegalTerms() {
        log.info("openLegalTerms");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Termos Legais");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = LegalTermsFragment.newInstance("", "");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void openSettings() {
        log.info("openSettings");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Configurações");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = SettingsFragment.newInstance("", "");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void notificationCheck() {
        log.info("notificationCheck");
    }





    @Override
    public void openFavorites() {
        log.info("openFavorites");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Favoritos");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = FavoriteFragment.newInstance(1);
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFavoriteSelected(FavoriteContent.FavoriteItem item) {
        log.info("onFavoriteSelected");
    }



    @Override
    public void openHelp() {
        log.info("openHelp");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ajuda");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = HelpFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openSuggestion() {
        log.info("openSuggestion");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sugestões");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = SuggestionFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openAbout() {
        log.info("openAbout");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sobre a Empresa");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = AboutFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void exit() {
        log.info("exit");
        offerExitApp();
    }




}

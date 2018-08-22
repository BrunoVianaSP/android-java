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

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.dev.cadidate.CandidateFragment;
import sample.dev.home.FeedContent;
import sample.dev.home.FeedFragment;
import sample.dev.home.HomeFragment;
import sample.dev.quiz.QuizFragment;
import sample.dev.settings.AboutFragment;
import sample.dev.settings.AppInfoFragment;
import sample.dev.settings.FaqContent;
import sample.dev.settings.FaqFragment;
import sample.dev.settings.FavoriteContent;
import sample.dev.settings.FavoriteFragment;
import sample.dev.settings.HelpFragment;
import sample.dev.settings.LegalTermsFragment;
import sample.dev.settings.SettingsFragment;
import sample.dev.settings.SuggestionFragment;
import sample.dev.user.ProfileFragment;
import sample.dev.user.SignupFragment;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.HomeListener,
        SignupFragment.SignupFragmentListener,
        ProfileFragment.ProfileFragmentListener,
        CandidateFragment.CandidateFragmentListener,
        QuizFragment.QuizFragmentListener,
        LegalTermsFragment.LegalTermsFragmentListener,
        SettingsFragment.SettingsFragmentListener,
        FavoriteFragment.FavoriteFragmentListener,
        SuggestionFragment.SuggestionFragmentListener,
        AboutFragment.AboutFragmentListener,
        HelpFragment.HelpFragmentListener,
        FaqFragment.FaqFragmentListener,
        AppInfoFragment.AppInfoFragmentListener,
        FeedFragment.FeedFragmentListener
{

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private Menu menu;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            log.info("getSupportFragmentManager().getBackStackEntryCount(): " + getSupportFragmentManager().getBackStackEntryCount());
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                if (bottomNavigationView.getSelectedItemId() == item.getItemId()) {
                    log.info("Same option selected!");
                    return false;
                }
            }

            getSupportFragmentManager().getFragments().clear();

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
        log.info("showHomeFragment");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = HomeFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        showHomeFeedList();
    }

    private void showHomeFeedList() {
        log.info("showHomeFeedList");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = FeedFragment.newInstance(1);
        fragmentTransaction.replace(R.id.container_home, fragment);
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
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_refresh);
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
            bottomNavigationView.setSelectedItemId(R.id.navigation_user_profile);
            openSettings();
            return true;
        } else if (id == R.id.action_refresh) {
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fr = fragmentManager.findFragmentById(R.id.main_container);
        if(fr!=null && !(fr instanceof HomeFragment)){
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
        MainActivity.this.finishAffinity();
        System.exit(0);
    }

    @Override
    public void openEditProfile() {
        log.info("openEditProfile");

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// show back button
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


    @Override
    public void openFaq() {
        log.info("openFaq");
        Objects.requireNonNull(getSupportActionBar()).setTitle("FAQ");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = FaqFragment.newInstance(1);
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openAppInfo() {
        log.info("openAppInfo");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sobre o App");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = AppInfoFragment.newInstance("","");
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFaqInteraction(FaqContent.DummyItem item) {
        log.info("onFaqInteraction");
    }

    @Override
    public void onFeedItemInteraction(FeedContent.DummyItem item) {
        log.info("onFeedItemInteraction");
    }
}

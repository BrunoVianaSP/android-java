package sample.activity;

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

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.api.UserAPI;
import sample.cadidate.CandidateFragment;
import sample.controller.Controller;
import sample.dev.R;
import sample.home.FeedContent;
import sample.home.FeedFragment;
import sample.home.HomeFragment;
import sample.model.User;
import sample.quiz.QuizFragment;
import sample.settings.AboutFragment;
import sample.settings.AppInfoFragment;
import sample.settings.FaqContent;
import sample.settings.FaqFragment;
import sample.settings.FavoriteContent;
import sample.settings.FavoriteFragment;
import sample.settings.HelpFragment;
import sample.settings.LegalTermsFragment;
import sample.settings.SettingsFragment;
import sample.settings.SuggestionFragment;
import sample.user.ProfileFragment;
import sample.user.SignupFragment;
import sample.util.FragmentUtils;

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

            if (isSameOptionSelected(item)) {
                return false;
            }

            cleanMainContainer();

            return showSelectedFragment(item);
        }
    };

    private boolean isSameOptionSelected(@NonNull MenuItem item) {
        if ( getSupportFragmentManager().getBackStackEntryCount() == 1) {
            if (bottomNavigationView.getSelectedItemId() == item.getItemId()) {
                log.info("Same option selected!");
                return true;
            }
        }
        return false;
    }

    private void cleanMainContainer() {
        FragmentUtils.clearAllFragments(MainActivity.this);
        LinearLayout layout = findViewById(R.id.main_container);
        layout.removeAllViews();
    }

    private boolean showSelectedFragment(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home: {
                showHomeFragment();
                return true;
            }
            case R.id.navigation_user_profile: {
                Objects.requireNonNull(getSupportActionBar()).setTitle("Usuario");
                FragmentUtils.replace(MainActivity.this, ProfileFragment.newInstance("", ""), R.id.main_container);
                return true;
            }

            case R.id.navigation_quiz: {
                Objects.requireNonNull(getSupportActionBar()).setTitle("Quiz de Afinidade");
                FragmentUtils.replace(MainActivity.this, QuizFragment.newInstance(1), R.id.main_container);
                return true;
            }

            case R.id.navigation_candidates: {
                Objects.requireNonNull(getSupportActionBar()).setTitle("Candidatos");
                FragmentUtils.replace(MainActivity.this, CandidateFragment.newInstance(1), R.id.main_container);
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ConfigBottomNavigator();
        configFloatButton();
        showHomeFragment();

        log.info("START REQUEST");
        User user = new User();
        final UserAPI loginClient = Controller.createService(UserAPI.class);
        Call<ResponseBody> call = loginClient.login(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        // get String from response
                        String stringResponse = response.body().string();
                        log.info("REQUEST RESULT: " + stringResponse);
                    } catch (IOException e) {
                        log.info("REQUEST RESULT ERROR ");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void showHomeFragment() {
        log.info("showHomeFragment");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Inicio");
        FragmentUtils.replace(MainActivity.this, HomeFragment.newInstance("",""), R.id.main_container);
        showHomeFeedList();
    }

    private void showHomeFeedList() {
        log.info("showHomeFeedList");
        FragmentUtils.replace(MainActivity.this, FeedFragment.newInstance(1), R.id.main_container);
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
        FragmentUtils.replace(MainActivity.this, ProfileFragment.newInstance("", ""), R.id.main_container);
    }

    @Override
    public void onHomeButtonPressed(int button) {
        log.info("onHomeInteraction: " + button);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fr = fragmentManager.findFragmentById(R.id.main_container);
        if(isGoingToHomeScreen(fr)){
            setHomeScreenSelected();
        } else {
            offerExitApp();
        }
    }

    private void setHomeScreenSelected() {
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    private boolean isGoingToHomeScreen(Fragment fr) {
        return fr!=null && !(fr instanceof HomeFragment);
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
        Objects.requireNonNull(getSupportActionBar()).setTitle("Editar perfil");
        FragmentUtils.replace(MainActivity.this, SignupFragment.newInstance("", ""), R.id.main_container);
    }

    @Override
    public void openLegalTerms() {
        log.info("openLegalTerms");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Termos Legais");
        FragmentUtils.replace(MainActivity.this, LegalTermsFragment.newInstance("", ""), R.id.main_container);
    }


    @Override
    public void openSettings() {
        log.info("openSettings");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Configurações");
        FragmentUtils.replace(MainActivity.this, SettingsFragment.newInstance("", ""), R.id.main_container);
    }

    @Override
    public void notificationCheck() {
        log.info("notificationCheck");
    }


    @Override
    public void openFavorites() {
        log.info("openFavorites");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Favoritos");
        FragmentUtils.replace(MainActivity.this, FavoriteFragment.newInstance(1), R.id.main_container);
    }

    @Override
    public void onFavoriteSelected(FavoriteContent.FavoriteItem item) {
        log.info("onFavoriteSelected");
    }

    @Override
    public void openHelp() {
        log.info("openHelp");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ajuda");
        FragmentUtils.replace(MainActivity.this, HelpFragment.newInstance("",""), R.id.main_container);
    }

    @Override
    public void openSuggestion() {
        log.info("openSuggestion");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sugestões");
        FragmentUtils.replace(MainActivity.this, SuggestionFragment.newInstance("",""), R.id.main_container);
    }

    @Override
    public void openAbout() {
        log.info("openAbout");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sobre a Empresa");
        FragmentUtils.replace(MainActivity.this, AboutFragment.newInstance("",""), R.id.main_container);
    }


    @Override
    public void openFaq() {
        log.info("openFaq");
        Objects.requireNonNull(getSupportActionBar()).setTitle("FAQ");
        FragmentUtils.replace(MainActivity.this, FaqFragment.newInstance(1), R.id.main_container);
    }

    @Override
    public void openAppInfo() {
        log.info("openAppInfo");
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sobre o App");
        FragmentUtils.replace(MainActivity.this, AppInfoFragment.newInstance("", ""), R.id.main_container);
    }

    @Override
    public void onFaqInteraction(FaqContent.DummyItem item) {
        log.info("onFaqInteraction");
    }

    @Override
    public void onFeedItemInteraction(FeedContent.DummyItem item) {
        log.info("onFeedItemInteraction");
    }

    @Override
    public void exit() {
        log.info("exit");
        offerExitApp();
    }

}

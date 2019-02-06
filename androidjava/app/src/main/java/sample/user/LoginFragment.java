package sample.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import sample.dev.R;
import sample.util.ConstantUtils;
import sample.view.LoginModelView;

public class LoginFragment extends Fragment {

    private String mEmailParam;
    private String mPasswordParam;

    private LoginFragmentListener mListener;

    private LoginModelView viewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance(String email, String password) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ConstantUtils.ARG_USER_EMAIL, email);
        args.putString(ConstantUtils.ARG_USER_PASSWORD, password);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEmailParam = getArguments().getString(ConstantUtils.ARG_USER_EMAIL);
            mPasswordParam = getArguments().getString(ConstantUtils.ARG_USER_PASSWORD);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new LoginModelView(view);

        viewModel.forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.forgotButton();
            }
        });

        viewModel.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.loginButton(viewModel.email.getText().toString(), viewModel.password.getText().toString());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            mListener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LegalTermsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface LoginFragmentListener {
        void loginButton(String email, String password);
        void forgotButton();
    }
}

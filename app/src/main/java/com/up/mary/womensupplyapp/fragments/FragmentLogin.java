package com.up.mary.womensupplyapp.fragments;

import android.app.AlertDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.main.MainActivity;
import com.up.mary.womensupplyapp.model.empreendedor.Empreendedor;
import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentLogin extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mLoggedInStatusTextView;
    private ProgressDialog mAuthProgressDialog;
    private Firebase mFirebaseRef;
    private AuthData mAuthData;
    private Firebase.AuthStateListener mAuthStateListener;
    private LoginButton mFacebookLoginButton;
    private CallbackManager mFacebookCallbackManager;
    private AccessTokenTracker mFacebookAccessTokenTracker;
    private GoogleApiClient mGoogleApiClient;
    private boolean mGoogleIntentInProgress;
    private boolean mGoogleLoginClicked;
    private ConnectionResult mGoogleConnectionResult;
    private SignInButton mGoogleLoginButton;

    private LinearLayout linearLayout;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getContext());
        Firebase.setAndroidContext(getContext());

        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        //Começo o processo de autenticação
        mFacebookCallbackManager = CallbackManager.Factory.create();
        mFacebookLoginButton = (LoginButton) rootView.findViewById(R.id.login_with_facebook);
        mFacebookAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                onFacebookAccessTokenChange(currentAccessToken);
            }
        };

        mGoogleLoginButton = (SignInButton) rootView.findViewById(R.id.login_with_google);
        mGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleLoginClicked = true;
                if (!mGoogleApiClient.isConnecting()) {
                    if (mGoogleConnectionResult != null) {
                        resolveSignInError();
                    } else if (mGoogleApiClient.isConnected()) {
                        getGoogleOAuthTokenAndLogin();
                    } else {
                        Log.d(TAG, "tentando conectar com Google API");
                        mGoogleApiClient.connect();
                    }
                }
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext()).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

        mLoggedInStatusTextView = (TextView) rootView.findViewById(R.id.login_status);

        mFirebaseRef = new Firebase(getResources().getString(R.string.firebase_url));

        //Mostro o loading para o usuario
        mAuthProgressDialog = new ProgressDialog(getActivity());
        mAuthProgressDialog.setTitle("Carregando");
        mAuthProgressDialog.setMessage("Entrando ...");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        mAuthStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                mAuthProgressDialog.hide();
                setAuthenticatedUser(authData);
            }
        };

        mFirebaseRef.addAuthStateListener(mAuthStateListener);
        return rootView;
    }

    private void setAuthenticatedUser(AuthData authData) {
        if (authData != null) {
            mFacebookLoginButton.setVisibility(View.GONE);
            mGoogleLoginButton.setVisibility(View.GONE);
            mLoggedInStatusTextView.setVisibility(View.VISIBLE);
            String name = null;
            if (authData.getProvider().equals("facebook")
                    || authData.getProvider().equals("google")) {
                name = (String) authData.getProviderData().get("displayName");
            } else {
                Log.e(TAG, "Provedor de autenticação invalido: " + authData.getProvider());
            }
            if (name != null) {
                mLoggedInStatusTextView.setText("Logado como " + name + " (" + authData.getProvider() + ")");
            }
        } else {
            mFacebookLoginButton.setVisibility(View.VISIBLE);
            mGoogleLoginButton.setVisibility(View.VISIBLE);
            mLoggedInStatusTextView.setVisibility(View.GONE);
        }
        this.mAuthData = authData;
        getActivity().supportInvalidateOptionsMenu();
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(getActivity().getApplicationContext())
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onConnected(Bundle bundle) {
        getGoogleOAuthTokenAndLogin();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mGoogleIntentInProgress) {
            mGoogleConnectionResult = result;
            if (mGoogleLoginClicked) {
                resolveSignInError();
            } else {
                Log.e(TAG, result.toString());
            }
        }
    }

    private class AuthResultHandler implements Firebase.AuthResultHandler {

        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }

        @Override
        public void onAuthenticated(AuthData authData) {
            mAuthProgressDialog.hide();
            Log.i(TAG, provider + " Ta tranquilo, ta favoravel");
            setAuthenticatedUser(authData);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mAuthProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }

    private void onFacebookAccessTokenChange(AccessToken token) {
        if (token != null) {
            mAuthProgressDialog.show();
            mFirebaseRef.authWithOAuthToken("facebook", token.getToken(), new AuthResultHandler("facebook"));
        } else {
            if (this.mAuthData != null && this.mAuthData.getProvider().equals("facebook")) {
                mFirebaseRef.unauth();
                setAuthenticatedUser(null);
            }
        }
    }

    private void resolveSignInError() {
        if (mGoogleConnectionResult.hasResolution()) {
            try {
                mGoogleIntentInProgress = true;
                mGoogleConnectionResult.startResolutionForResult(getActivity(), MainActivity.RC_GOOGLE_LOGIN);
            } catch (IntentSender.SendIntentException e) {
                mGoogleIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void getGoogleOAuthTokenAndLogin() {
        mAuthProgressDialog.show();
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            String errorMessage = null;

            @Override
            protected String doInBackground(Void... params) {
                String token = null;

                try {
                    String scope = String.format("oauth2:%s", Scopes.PLUS_LOGIN);
                    token = GoogleAuthUtil.getToken(getActivity().getApplicationContext(), Plus.AccountApi.getAccountName(mGoogleApiClient), scope);
                } catch (IOException transientEx) {
                    Log.e(TAG, "Erro de autenticação do Google: " + transientEx);
                    errorMessage = "Erro de rede: " + transientEx.getMessage();
                } catch (UserRecoverableAuthException e) {
                    Log.w(TAG, "Erro OAuth: " + e.toString());
                    if (!mGoogleIntentInProgress) {
                        mGoogleIntentInProgress = true;
                        Intent recover = e.getIntent();
                        startActivityForResult(recover, MainActivity.RC_GOOGLE_LOGIN);
                    }
                } catch (GoogleAuthException authEx) {
                    Log.e(TAG, "Erro de autenticação do Google: " + authEx.getMessage(), authEx);
                    errorMessage = "Erro de autenticação do Google: " + authEx.getMessage();
                }
                return token;
            }

            @Override
            protected void onPostExecute(String token) {
                mGoogleLoginClicked = false;
                if (token != null) {
                    mFirebaseRef.authWithOAuthToken("google", token, new AuthResultHandler("google"));
                    try {
                        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
                            Person currentPerson = Plus.PeopleApi
                                    .getCurrentPerson(mGoogleApiClient);
                            String personName = currentPerson.getDisplayName();
                            String personPhotoUrl = currentPerson.getImage().getUrl();
                            String personGooglePlusProfile = currentPerson.getUrl();
                            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

                            Log.e(TAG, "Nome: " + personName + ", plusProfile: "
                                    + personGooglePlusProfile + ", email: " + email
                                    + ", Image: " + personPhotoUrl);

                            Bundle bundle = getArguments();
                            String tipoUsuario = bundle.getSerializable("escolha").toString();

                            if(("empreendedor").equals(tipoUsuario)){
                                Empreendedor empreendedor = new Empreendedor();
                                empreendedor.setNomeResponsavel(personName);
                                empreendedor.setEmailResponsavel(email);

                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                FragmentEmpreendedor fragmentEmpreendedor = new FragmentEmpreendedor();
                                //Passo os dados do usuario
                                Bundle bundles = new Bundle();
                                bundles.putSerializable("empreendedor", empreendedor);
                                fragmentEmpreendedor.setArguments(bundles);
                                fragmentTransaction.replace(R.id.frame, fragmentEmpreendedor);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }else{
                                Fornecedor fornecedor = new Fornecedor();
                                fornecedor.setNomeResponsavel(personName);
                                fornecedor.setEmailResponsavel(email);

                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                FragmentFornecedor fragmentFornecedor = new FragmentFornecedor();
                                //Passo os dados do usuario
                                Bundle bundles = new Bundle();
                                bundles.putSerializable("fornecedor", fornecedor);
                                fragmentFornecedor.setArguments(bundles);
                                fragmentTransaction.replace(R.id.frame, fragmentFornecedor);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "NULO = ", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (errorMessage != null) {
                    mAuthProgressDialog.hide();
                    showErrorDialog(errorMessage);
                }
            }
        };
        task.execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> options = new HashMap<String, String>();
        if (requestCode == MainActivity.RC_GOOGLE_LOGIN) {
            if (resultCode != getActivity().RESULT_OK) {
                mGoogleLoginClicked = false;
            }
            mGoogleIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        } else {
            mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mFacebookAccessTokenTracker != null) {
            mFacebookAccessTokenTracker.stopTracking();
        }
        mFirebaseRef.removeAuthStateListener(mAuthStateListener);
    }

    //Caso o usuario queira sair e deslogar do aplicativo
    private void logout() {
        if (this.mAuthData != null) {
            mFirebaseRef.unauth();
            if (this.mAuthData.getProvider().equals("facebook")) {
                LoginManager.getInstance().logOut();
            } else if (this.mAuthData.getProvider().equals("google")) {
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }
            }
            setAuthenticatedUser(null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

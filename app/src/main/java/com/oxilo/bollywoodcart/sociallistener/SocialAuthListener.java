package com.oxilo.bollywoodcart.sociallistener;

/**
 * Created by ericbasendra on 02/05/16.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;

/**
 * Created by ericbasendra on 22/08/15.
 */
public class SocialAuthListener {

    private Context mContext;
    public ProgressDialog progressDialog;
    // SocialAuth Components
    private static SocialAuthAdapter adapter;
    public SocialAuthListener(Context mContext){
        this.mContext = mContext;
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Sign in ListYou...");
        adapter = getInstance();
    }

    public SocialAuthAdapter getInstance(){
        adapter = new SocialAuthAdapter(new ResponseListener());
        return adapter;
    }

    public void faceBookAuthentication(){
        adapter.authorize(mContext, SocialAuthAdapter.Provider.FACEBOOK);
    }

    public void googlePlusAuthentication(){
        adapter.authorize(mContext, SocialAuthAdapter.Provider.GOOGLEPLUS);
    }

    /**
     * ---------------------------------------SocialAuth Handler
     * Code--------------------------------------------- We Receive the facebook
     * twitter google Response and get the user detail fiels
     */

    // To receive the response after authentication
    private final class ResponseListener implements DialogListener {

        @Override
        public void onComplete(Bundle values) {

            Log.d("Custom-UI", "Successful");

            // Changing Sign In Text to Sign Out
            progressDialog.show();
            adapter.getUserProfileAsync(new ProfileDataListener());

        }

        @Override
        public void onError(SocialAuthError error) {
            Log.d("Custom-UI", "Error");
            error.printStackTrace();
        }

        @Override
        public void onCancel() {
            Log.d("Custom-UI", "Cancelled");
        }

        @Override
        public void onBack() {
            Log.d("Custom-UI", "Dialog Closed by pressing Back Key");

        }
    }
    /**
     * ---------------To Receive Profile Response after
     * Authencation-----------------------
     * ----------------------------------------------
     */
    // To receive the profile response after authentication
    private final class ProfileDataListener implements org.brickred.socialauth.android.SocialAuthListener<Profile> {

        @Override
        public void onExecute(String provider, Profile t) {

            Log.d("Custom-UI", "Receiving Data");
            progressDialog.dismiss();
            Profile profileMap = t;

//            Intent intent = new Intent(mContext, LandingActivity.class);
//            intent.putExtra("provider", provider);
//            intent.putExtra("profile", profileMap);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            mContext.startActivity(intent);
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

}

package com.mariekids.mariekids_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mariekids.mariekids_app.databinding.ActivityUserLoginBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

class UserLogin : AppCompatActivity() {

//    private lateinit var service:LoginService;
    private lateinit var loginInstance: OAuthLogin;
    private lateinit var _context: Context;

    private lateinit var clientID:String;
    private lateinit var clientPW:String;
    private lateinit var clientName:String;

    private lateinit var binding:ActivityUserLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserLoginBinding.inflate(layoutInflater);
        setContentView(binding.root);

        _context = this;

        clientID = getString(R.string.naver_client_id);
        clientPW = getString(R.string.naver_client_secret);
        clientName = getString(R.string.naver_client_name);

        initLogin();
    }

    private fun initLogin(){
        loginInstance = OAuthLogin.getInstance();
        loginInstance.init(_context, clientID, clientPW, clientName);
        val naverLogin = binding.naverLogin;
        naverLogin.setOAuthLoginHandler(loginAuthHandler())
    }

    inner class loginAuthHandler() : OAuthLoginHandler(){
        override fun run(p0: Boolean) {
            if(p0){
                val tokent = loginInstance.getAccessToken(_context);
                val refresh = loginInstance.getRefreshToken(_context);

                val profileUrl = "https://openapi.naver.com/v1/nid/me";
                var a = loginInstance.requestApi(_context, tokent, profileUrl);

                val intent = Intent(this@UserLogin, MainWebView::class.java);
                startActivity(intent);
            }
        }
    }
}
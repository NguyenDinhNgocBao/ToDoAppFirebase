package com.example.todoappfirebase.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoappfirebase.R
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {
    private lateinit var auth:FirebaseAuth
    private lateinit var navCOntrol: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        navCOntrol = Navigation.findNavController(view)
        //Delay trước khi chuyển qua trang khác
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            navCOntrol.navigate(R.id.action_splashFragment_to_signInFragment)
        },2000)
    }
}
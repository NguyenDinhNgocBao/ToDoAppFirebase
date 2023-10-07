package com.example.todoappfirebase.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoappfirebase.R
import com.example.todoappfirebase.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {
    private lateinit var binding:FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navCOntrol: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        innit(view)
        registerEvents()
    }

    private fun registerEvents() {

        binding.txtAuth.setOnClickListener {
            navCOntrol.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnNext.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(context, "Login Successfully", Toast.LENGTH_LONG).show()
                        navCOntrol.navigate(R.id.action_signInFragment_to_homeFragment)

                    }else{
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_LONG).show()
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }else{
                Toast.makeText(context,"Mật khẩu nhập lại không đúng!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun innit(view: View) {
        navCOntrol = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
}
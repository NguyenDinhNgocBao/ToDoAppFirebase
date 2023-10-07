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
import com.example.todoappfirebase.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var navCOntrol:NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        innit(view)
        registerEvents()
    }

    private fun registerEvents() {

        binding.txtAuth.setOnClickListener {
            navCOntrol.navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnNext.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPassword.text.toString().trim()
            val rePass = binding.edtRePassword.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty() && rePass.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                if(pass.equals(rePass)){
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(context, "Register Successfully", Toast.LENGTH_LONG).show()
                            navCOntrol.navigate(R.id.action_signUpFragment_to_signInFragment)

                        }else{
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_LONG).show()
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                }else{
                    Toast.makeText(context, "Mật khẩu nhập lại không đúng!!",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun innit(view: View) {
        navCOntrol = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

}
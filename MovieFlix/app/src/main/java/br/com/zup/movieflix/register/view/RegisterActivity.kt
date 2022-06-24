package br.com.zup.movieflix.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.R
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var confirmPassword: String
    private lateinit var account: RegisterModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bvLogin.setOnClickListener {
            getUserData()
            if(!checkFields()){
                if(!validatePassword()){
                    account = RegisterModel(this.name,this.email,this.password,this.confirmPassword)
                    viewModel.getAccount(account)
                    val username = viewModel.response.value?.userName
                    Toast.makeText(this, "Cadastro de $username realizado com sucesso!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getUserData(){
        this.name = binding.etUserNameRegister.text.toString()
        this.email = binding.etEmailRegister.text.toString()
        this.password = binding.etPasswordRegister.text.toString()
        this.confirmPassword = binding.etConfirmPasswordRegister.text.toString()
    }

    private fun checkFields(): Boolean{
        when{
            this.name.isEmpty() -> {
                binding.etUserNameRegister.error = "Campo Obrigatório"
                return true
            }
            this.email.isEmpty() ->{
                binding.etEmailRegister.error = "Campo Obrigatório"
                return true
            }
            this.password.isEmpty() -> {
                binding.etPasswordRegister.error = "Campo Obrigatório"
                return true
            }
            this.confirmPassword.isEmpty() -> {
                binding.etConfirmPasswordRegister.error = "Campo Obrigatório"
                return true
            }
            else -> {
                return false
            }
        }
    }

    private fun validatePassword(): Boolean{
        return if(this.password != this.confirmPassword){
            binding.etConfirmPasswordRegister.error = "As senhas digitadas devem ser iguais"
            true
        } else{
            false
        }
    }
}
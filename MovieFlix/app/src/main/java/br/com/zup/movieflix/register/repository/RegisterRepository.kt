package br.com.zup.movieflix.register.repository

import br.com.zup.movieflix.register.model.RegisterModel

class RegisterRepository {

   fun getRegister(account: RegisterModel): RegisterModel{
       return account
   }
}
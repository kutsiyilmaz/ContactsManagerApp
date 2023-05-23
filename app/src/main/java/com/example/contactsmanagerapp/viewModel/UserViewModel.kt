package com.example.contactsmanagerapp.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmanagerapp.room.User
import com.example.contactsmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository):ViewModel(), Observable {

    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete: User

    //observable.
    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllorDeleteButtonText = MutableLiveData<String>()

    init{
        saveOrUpdateButtonText.value = "Save"
        clearAllorDeleteButtonText.value = "Clear All"
    }


    fun saveOrUpdate(){
        if( isUpdateOrDelete){
            //make update
            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!
            update(userToUpdateOrDelete)

        }
        else{
            //Insert functionality
            val name = inputName.value!!
            val email = inputEmail.value!!//!! database e null değer girilmemesini sağlıyor.

            insert(User(0,name,email))

            inputName.value = null
            inputEmail.value = null
        }



    }

    fun celareAllOrDelete(){
        if(isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }else{
            cleareAll()
        }

    }

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun cleareAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user:User) = viewModelScope.launch {
        repository.update(user)

        //resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllorDeleteButtonText.value = "Clear All"
    }

    fun delete(user:User)= viewModelScope.launch {
        repository.delete(user)

        //resetting the buttons and fields
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllorDeleteButtonText.value = "Clear All"

    }

    fun initUpdateAndDelete(user: User){
        //resetting the buttons and fields
        inputName.value = user.name
        inputEmail.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdateButtonText.value = "Update"
        clearAllorDeleteButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }




}
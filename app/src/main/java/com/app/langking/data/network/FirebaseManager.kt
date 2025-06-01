package com.app.langking.data.network

import android.util.Log
import com.app.langking.data.model.RealTimeId
import com.app.langking.ultis.AppConstants
import com.app.langking.ultis.Resource
import com.google.firebase.database.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseManager {

    private val database = FirebaseDatabase.getInstance()
    private val rootRef = database.getReference(AppConstants.FIREBASE_ROOT_DB)

    suspend fun <T> getObject(path: String, clazz: Class<T>): Resource<T> = suspendCancellableCoroutine { cont ->
        Log.e("FirebaseManager", "---> get [${path}]")
        rootRef.child(path).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val value = snapshot.getValue(clazz)
                    cont.resume(Resource.Success(value))
                    Log.e("FirebaseManager", "<--- 200 get [${path}]: $value")
                }catch (e: Exception){
                    cont.resume(Resource.Error(message = e.message))
                    Log.e("FirebaseManager", "<--- 400 get [${path}]: ${e.message}")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseManager", "<-- ${error.code} ${path}]: ${error.toException()}")
                cont.resume(Resource.Error(message = error.toException().toString()))
            }
        })
    }

    suspend fun <T> getList(path: String, clazz: Class<T>): Resource<List<T>> = suspendCancellableCoroutine { cont ->
        Log.e("FirebaseManager", "---> getList [${path}]")
        rootRef.child(path).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    Log.e("data", "data: ${snapshot.value}")
                    val list = snapshot.children.mapNotNull { it.getValue(clazz) }
                    for (snap in snapshot.children) {
                        Log.e("DEBUG", "Null found at key: ${snap?.key}, raw: ${snap?.value}")
                    }
                    cont.resume(Resource.Success(list))
                    Log.e("FirebaseManager", "<--- 200 getList [${path}]: $list")
                } catch (e: Exception) {
                    cont.resume(Resource.Error(message = e.message))
                    Log.e("FirebaseManager", "<--- 400 getList [${path}]: ${e.message}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseManager", "<-- ${error.code} getList [${path}]: ${error.toException()}")
                cont.resume(Resource.Error(message = error.toException().toString()))
            }
        })
    }


    fun readOnce(path: String, listener: ValueEventListener) {
        rootRef.child(path).addListenerForSingleValueEvent(listener)
    }

    fun addListener(path: String, listener: ValueEventListener) {
        rootRef.child(path).addValueEventListener(listener)
    }

    fun removeListener(path: String, listener: ValueEventListener) {
        rootRef.child(path).removeEventListener(listener)
    }

    suspend fun <T> overwrite(path: String, data: T): Resource<T> = suspendCancellableCoroutine { cont ->
        val ref = rootRef.child(path)

        ref.setValue(data)
            .addOnSuccessListener {
                cont.resume(Resource.Success(data))
            }
            .addOnFailureListener { e ->
                cont.resume(Resource.Error(message = e.message ?: "Unknown error"))
            }
    }


    suspend fun <T> push(path: String, data: T): Resource<T> = suspendCancellableCoroutine { cont ->
        // if is array set id as key in db
        val dataPush = if (data is List<*> && data.all { it is RealTimeId }) {
            data.filterIsInstance<RealTimeId>().associateBy { it.id }
        } else {
            data as Any
        }
        Log.e("FirebaseManager", "---> push [${path}]: $dataPush")
        rootRef.child(path).setValue(dataPush)
            .addOnSuccessListener {
                cont.resume(Resource.Success(data as T))
            }
            .addOnFailureListener { e ->
                cont.resume(Resource.Error(message = e.message ?: "Unknown error"))
            }
    }

    suspend fun <T> remove(path: String, data: T): Resource<T> = suspendCancellableCoroutine { cont ->
        rootRef.child(path).removeValue()
            .addOnSuccessListener {
                cont.resume(Resource.Success(data))
            }
            .addOnFailureListener { e ->
                cont.resume(Resource.Error(message = e.message ?: "Unknown error"))
            }
    }

    fun getReference(path: String): DatabaseReference {
        return rootRef.child(path)
    }
}

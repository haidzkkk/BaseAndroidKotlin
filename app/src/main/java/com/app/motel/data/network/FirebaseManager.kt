package com.app.motel.data.network

import android.util.Log
import com.app.motel.data.model.RealTimeId
import com.google.firebase.database.*
import com.history.vietnam.data.model.Resource
import com.history.vietnam.ultis.AppConstants
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class FirebaseManager {

    private val database = FirebaseDatabase.getInstance()
    private val rootRef = database.getReference(AppConstants.FIREBASE_ROOT_DB)

    suspend fun <T> getObject(path: DbPath, clazz: Class<T>): Resource<T>? = suspendCancellableCoroutine { cont ->
        Log.e("FirebaseManager", "---> get [${path.value}]")
        rootRef.child(path.value).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val value = snapshot.getValue(clazz)
                    cont.resume(Resource.Success(value))
                    Log.e("FirebaseManager", "<--- 200 get [${path.value}]: $value")
                }catch (e: Exception){
                    cont.resume(Resource.Error(e.message))
                    Log.e("FirebaseManager", "<--- 400 get [${path.value}]: ${e.message}")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseManager", "<-- ${error.code} ${path.value}]: ${error.toException()}")
                cont.resume(Resource.Error(error.toException().toString()))
            }
        })
    }

    suspend fun <T> getList(path: DbPath, clazz: Class<T>): Resource<List<T>> = suspendCancellableCoroutine { cont ->
        Log.e("FirebaseManager", "---> getList [${path.value}]")
        rootRef.child(path.value).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val list = mutableListOf<T>()
                    for (child in snapshot.children) {
                        val item = child.getValue(clazz)
                        if (item != null) {
                            list.add(item)
                        }
                    }
                    cont.resume(Resource.Success(list))
                    Log.e("FirebaseManager", "<--- 200 getList [${path.value}]: $list")
                } catch (e: Exception) {
                    cont.resume(Resource.Error(e.message))
                    Log.e("FirebaseManager", "<--- 400 getList [${path.value}]: ${e.message}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseManager", "<-- ${error.code} getList [${path.value}]: ${error.toException()}")
                cont.resume(Resource.Error(error.toException().toString()))
            }
        })
    }


    fun readOnce(path: DbPath, listener: ValueEventListener) {
        rootRef.child(path.value).addListenerForSingleValueEvent(listener)
    }

    fun addListener(path: DbPath, listener: ValueEventListener) {
        rootRef.child(path.value).addValueEventListener(listener)
    }

    fun removeListener(path: DbPath, listener: ValueEventListener) {
        rootRef.child(path.value).removeEventListener(listener)
    }

    // Ghi đè dữ liệu tại path
    suspend fun <T> overwrite(path: DbPath, data: T): Resource<T> = suspendCancellableCoroutine { cont ->
        val ref = rootRef.child(path.value)

        ref.setValue(data)
            .addOnSuccessListener {
                cont.resume(Resource.Success(data))
            }
            .addOnFailureListener { e ->
                cont.resume(Resource.Error(e.message ?: "Unknown error"))
            }
    }


    suspend fun <T> push(path: DbPath, data: T): Resource<T> = suspendCancellableCoroutine { cont ->
        // if is array set id as key in db
        val dataPush = if (data is List<*> && data.all { it is RealTimeId }) {
            data.filterIsInstance<RealTimeId>().associateBy { it.id.toString() }
        } else {
            data as Any
        }

        rootRef.child(path.value).setValue(dataPush)
            .addOnSuccessListener {
                cont.resume(Resource.Success(data as T))
            }
            .addOnFailureListener { e ->
                cont.resume(Resource.Error(e.message ?: "Unknown error"))
            }
    }


    fun getReference(path: DbPath): DatabaseReference {
        return rootRef.child(path.value)
    }
}

enum class DatabasePath(private val base: String) {
    ACCOUNT(AppConstants.FIREBASE_ACCOUNT_PATH),
    HISTORY_DYNASTY(AppConstants.FIREBASE_HISTORY_DYNASTY_PATH),
    HISTORY_EVENT(AppConstants.FIREBASE_HISTORY_EVENT_PATH);

    fun dbPath(id: String): DbPath {
        return DbPath("$base/$id")
    }

    fun dbPath(): DbPath {
        return DbPath(base)
    }
}

@JvmInline
value class DbPath(val value: String)

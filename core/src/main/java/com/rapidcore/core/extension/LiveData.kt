package com.rapidcore.core.extension

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}

/**
 * Kotlin extension untuk menambahkan fungsi validasi form
 * pada mediator livedata, contoh:
 * ```
 * val idType = MutableLiveData<Int>()
 * val idNumber = MutableLiveData<String>()
 * val idImage = MutableLiveData<Bitmap>()
 *
 * val isFormIdValid = MediatorLiveData<Boolean>().also {
 *      val mutableLiveData = listOf(idType, idNumber)
 *      it.addFormValidator(mutableLiveData){
 *           validateUploadId()
 *      }
 * }
 * private fun validateUploadId(): Boolean =
 *         idType.value != null && idNumber.value.isNotNullOrEmpty() && idImage.value != null
 * ```
 */
fun MediatorLiveData<Boolean>.addFormValidator(
    listOfLiveData: List<LiveData<out Any>>,
    validator: () -> Boolean
) {
    listOfLiveData.map {
        this.addSource(it) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(100)
                this@addFormValidator.postValue(validator())
            }
        }
    }
}

/**
 * Menambahkan fungsi debounce pada MutableLiveData
 *
 * Ketika value berubah sebelum waktu [debounce] selesai
 * maka value sebelumnya tidak akan ter-dispatch
 * contoh usecase pada filter search agar hit-api menunggu
 * input keyboard selesai
 *
 * [lifecycleOwner] gunakan viewLifecycleOwner untuk fragment
 *
 * [duration] lamanya waktu debounce
 *
 * [addNull] jika True maka saat pertama kali value diobserve akan ada value null
 *            yang terdispatch
 *
 * Contoh :
 *
 * ```
 * viewModel.searchKeyword.addDebounce(lifecycleScope, 1000, addNull = true)
 *          .observe(viewLifecycleOwner){
 *              // nilai null akan ter-dispatch saat pertama kali di observe
 * }
 *
 * ```
 */

@OptIn(FlowPreview::class)
fun <T>MutableLiveData<T>.addDebounce(lifecycleOwner: LifecycleOwner, duration: Long, addNull: Boolean = true, observer: (T?) -> Unit): MutableLiveData<T>{
    val flow = callbackFlow {
        observe(lifecycleOwner, Observer { value ->
            trySend(value)
        })
        awaitClose()
    }.debounce(duration)
    lifecycleOwner.lifecycleScope.launch {
        flow.onStart { if (addNull) emit(null) }
            .collectLatest {
            observer(it)
        }
    }
    return this
}

fun <T>MutableLiveData<T>.triggerWhenTrue(owner: LifecycleOwner, liveData: LiveData<Boolean>): MutableLiveData<T>{
    liveData.observe(owner, Observer {
        if (it) this.postValue(this.value)
    })
    return this
}

fun <T>MutableLiveData<T>.addTrigger(owner: LifecycleOwner, liveData: LiveData<out Any>): MutableLiveData<T>{
    liveData.observe(owner, Observer {
        this.postValue(this.value)
    })
    return this
}

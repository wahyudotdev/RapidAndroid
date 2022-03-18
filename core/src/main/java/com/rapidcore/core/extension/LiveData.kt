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
 * [lifecycleCoroutineScope] Coroutine scope
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
fun <T>LiveData<T>.addDebounce(lifecycleCoroutineScope: LifecycleCoroutineScope, duration: Long, addNull: Boolean = true): MediatorLiveData<T> {
    val mediatorLiveData = MediatorLiveData<T>()
    val filterFlow = callbackFlow<T?> {
        mediatorLiveData.addSource(this@addDebounce){
            trySend(it)
        }
        awaitClose()
    }.debounce(duration)
    lifecycleCoroutineScope.launch {
        filterFlow
            .onStart {
                if (addNull) emit(null)
            }
            .collect {
                mediatorLiveData.postValue(it)
            }
    }
    return mediatorLiveData
}

/**
 * Untuk melisten dari livedata lain namun hanya untuk mentrigger ulang aksi didalam observer callback.
 * (gunakan MediatorLiveData jika ingin menggunakan valuenya)
 *
 * Contoh case ketika mem-filter produk dari searchbar dan dari custom filter (urutan, jenis item, dsb)
 *
 * Contoh :
 *```
 * viewModel.searchKeyword.addDebounce(lifecycleScope, 1000)
 *          .addTrigger(viewModel.selectedProductType)
 *          .observe(viewLifecycleOwner) { filter ->
 *               // nilai akan ter-dispatch setiap ada perubahan
 *               // pada viewModel.selectedProductType
 *               // dan viewModel.searchKeyword
 *          }
 *```
 */
fun <T>MediatorLiveData<T>.addTrigger(liveData: LiveData<out Any>): MediatorLiveData<T>{
    this.addSource(liveData){
        this.postValue(this.value)
    }
    return this
}
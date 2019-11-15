package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.CharacterDetailDialogFragment
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class CharacterView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = 1
    val characterPublisher = PublishSubject.create<Int>()
    private val DIALOG_TAG = "Character Dialog"
    //var adapter = CharacterAdapter { character -> activity.applicationContext.showToast(character.id.toString()) }
   // var adapter = CharacterAdapter { character -> activity.applicationContext.showToast(character.resourceURI) }
   // var adapter = CharacterAdapter { character -> activity.applicationContext.showToast(character.comics.available.toString()) } //var adapter = CharacterAdapter { character -> activity.applicationContext.showToast(character.comics.collectionURI) }

     var adapter = CharacterAdapter { character ->
        run {
            showLoading()
            characterPublisher.onNext(character.id)
        }
    }

    fun refreshObservable(): Observable<Boolean> {
        val activity = activityRef.get()
        return Observable.create { emitter ->
            if (activity != null) {
                activity.floatingActionButton.setOnClickListener {
                    emitter.onNext(true)
                }
            }
        }
    }
    
    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            activity.recycleView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
            activity.recycleView.adapter = adapter
            showLoading()
        }

    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        if (activity != null) {
            val message = activity.baseContext.resources.getString(R.string.message_no_items_to_show)
            activity.applicationContext.showToast(message)

        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()!!.applicationContext.showToast(error)
    }

    fun hideLoading() {
        activityRef.get()!!.progressBar.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        val activity = activityRef.get()
        if (activity != null) {
            activity.recycleView.adapter = adapter
        }
        adapter.data = characters
        hideLoading()
    }

    fun showLoading() {
        activityRef.get()!!.progressBar.visibility = View.VISIBLE

    }

    fun showDetailDialog(name: String, description: String, image: String, availableComics: String, resourceUrl: String, comicCollectionUrl: String, comicsReturned: String) {
        val activity = activityRef.get()
        val dialog = CharacterDetailDialogFragment.newInstance(name , description , image, resourceUrl, availableComics, comicCollectionUrl, comicsReturned )
        dialog.show(activity!!.fragmentManager,DIALOG_TAG )
        hideLoading()
    }

    fun reset() {
        val activity = activityRef.get()
        if (activity != null) {
            activity.recycleView.adapter = null
        }
        showLoading()
    }




}

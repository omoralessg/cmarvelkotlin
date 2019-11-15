package com.puzzlebench.clean_marvel_kotlin.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl

class CharacterDetailDialogFragment : DialogFragment() {

    companion object {
        private val CHARACTER_NAME = "CHARACTER_NAME"
        private val CHARACTER_DESCRIPTION = "CHARACTER_DESCRIPTION"
        private val CHARACTER_IMAGE = "CHARACTER_IMAGE"
        private val CHARACTER_URL = "CHARACTER_URL"
        private val CHARACTER_AVAILABLE_COMICS = "CHARACTER_AVAILABLE_COMICS"
        private val CHARACTER_COLLECTION_URL = "CHARACTER_COLLECTION_URL"
        private val CHARACTER_RETURN = "CHARACTER_RETURN"



        fun newInstance(name: String, description: String, image: String, url: String,
                        availableComics :String, comicCollectionUrl: String, comicsReturned: String ): CharacterDetailDialogFragment {
            val dialog = CharacterDetailDialogFragment()
            val args = Bundle().apply {
                putString(CHARACTER_NAME, name)
                putString(CHARACTER_DESCRIPTION, description)
                putString(CHARACTER_URL, url)
                putString(CHARACTER_IMAGE, image)
                putString(CHARACTER_AVAILABLE_COMICS, availableComics)
                putString(CHARACTER_COLLECTION_URL, comicCollectionUrl)
                putString(CHARACTER_RETURN, comicsReturned)

            }
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(R.layout.character_detail_layout, null)
        val name = view.findViewById<View>(R.id.tv_character_detail_name) as TextView
        val description = view.findViewById<View>(R.id.tv_character_detail_description) as TextView
        val url = view.findViewById<View>(R.id.tv_character_url) as TextView
        val availableComics = view.findViewById<View>(R.id.tv_character_available_comics) as TextView

        val imageHero = view.findViewById<View>(R.id.image_character_detail_thumbnail) as ImageView
        lateinit var imagePath : String

        imagePath = arguments?.getString(CHARACTER_IMAGE).toString()
        imageHero.getImageByUrl(imagePath)
        name.text = arguments?.getString(CHARACTER_NAME)
        description.text = arguments?.getString(CHARACTER_DESCRIPTION)
        availableComics.text = arguments?.getString(CHARACTER_AVAILABLE_COMICS)
        url.text = arguments?.getString(CHARACTER_URL)




        val dialogBuilder = AlertDialog.Builder(context)
                    .setView(view)
                    .setNeutralButton("OK") { _, _ ->
                        dialog.cancel()
                    }


        return dialogBuilder.create()
    }
}
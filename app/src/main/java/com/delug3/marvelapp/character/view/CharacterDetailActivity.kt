package com.delug3.marvelapp.character.view


import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.delug3.marvelapp.R
import com.delug3.marvelapp.character.model.ItemsItem
import com.delug3.marvelapp.character.model.Thumbnail
import com.delug3.marvelapp.character.viewmodels.CharacterDetailViewModel
import com.delug3.marvelapp.common.utilities.Constants
import com.delug3.marvelapp.databinding.ActivityDetailCharactersBinding
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailCharactersBinding
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var characterId = 0
        if (savedInstanceState == null) {

            val extras = intent.extras
            if (extras != null) {
                characterId = extras.getInt(Constants.CHARACTER_ID)
            }
        }
        setCharacterId(characterId)
        getData()
        initUI()
    }


    /**This method get the character details from an API using a coroutine
     *
     */
    private fun getData() {
        characterDetailViewModel.getCharacterDetails()
    }


    /**This method set up the character data : name, description and image
     *
     */
    private fun initUI() {
        setCharacterName()
        setCharacterDescription()
        setCharacterImage()
        setComicsAppearance()
    }


    /**This method observe a list of comics
     * When a change happens, the method create a dynamic list of comics
     */
    private fun setComicsAppearance() {
        val comics = Observer<List<ItemsItem>?> { comics -> setComicsAppearanceDynamically(comics) }
        characterDetailViewModel.characterAppearanceInComics.observe(this, comics)
    }


    /**Required method to set the marvel image in the imageView
     *Added an extra image to show in case that no image is available or an error happens when loading the resource
     */
    private fun setCharacterImage() {
        val characterThumbnailObserver = Observer<Thumbnail> { characterThumbnail ->
            val thumbnail = "${characterThumbnail.path}.${characterThumbnail.extension}"
            Picasso.get().load(thumbnail).error(R.drawable.error).into(binding.ivCharacter)
        }
        characterDetailViewModel.characterThumbnail.observe(this, characterThumbnailObserver)
    }


    /**This method set up the character description
     * We check here if the description is empty, in that case a value replace the empty value
     * The new value is stored in the Constants file
     */
    private fun setCharacterDescription() {
        val characterDescriptionObserver = Observer<String> { characterDescription ->
            if (characterDescription == "") {
                binding.tvDescription.text = Constants.NO_DESCRIPTION
            } else {
                binding.tvDescription.text = characterDescription
            }
        }
        characterDetailViewModel.characterDescription?.observe(this, characterDescriptionObserver)
    }


    /**This Method add the character name to the textview
     *If the name is empty, we replace the value with one coming from the Constants file
     */
    private fun setCharacterName() {
        val characterNameObserver = Observer<String> { characterName ->
            if (characterName == "") {
                binding.tvName.text = Constants.NO_NAME
            } else {
                binding.tvName.text = characterName
            }
        }
        characterDetailViewModel.characterName?.observe(this, characterNameObserver)
    }


    /**This method dynamically add the list of comics if there are any
     * We add here the text properties : color and size
     */
    private fun setComicsAppearanceDynamically(comics: List<ItemsItem>?) {
        if (comics?.isEmpty() == true) {
            val tvDynamic = TextView(this)
            tvDynamic.textSize = 15f
            tvDynamic.text = Constants.NO_COMICS
            tvDynamic.setTextColor(ContextCompat.getColor(this,R.color.white))
            binding.llComicsLayout.addView(tvDynamic)
        } else {
            for (i in comics?.indices!!) {
                val tvDynamic = TextView(this)
                tvDynamic.textSize = 15f
                tvDynamic.setTextColor(ContextCompat.getColor(this,R.color.white))
                tvDynamic.text = comics[i].name
                binding.llComicsLayout.addView(tvDynamic)
            }
        }
    }


    /**Required method to set the character id in order to get the details from the API endpoint
     *
     */
    private fun setCharacterId(characterId: Int) {
        characterDetailViewModel.setCharacterId(characterId)
    }
}
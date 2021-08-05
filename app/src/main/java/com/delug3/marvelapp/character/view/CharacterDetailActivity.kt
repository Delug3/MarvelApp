package com.delug3.marvelapp.character.view


import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    private fun getData() {
        characterDetailViewModel.getCharacterDetails()
    }

    private fun initUI() {
        setCharacterName()
        setCharacterDescription()
        setCharacterImage()
        setComicsAppearance()
    }

    private fun setComicsAppearance() {
        val comics = Observer<List<ItemsItem>?> { comics -> setComicsAppearanceDynamically(comics) }
        characterDetailViewModel.characterAppearanceInComics.observe(this, comics)
    }

    private fun setCharacterImage() {
        val characterThumbnailObserver = Observer<Thumbnail> { characterThumbnail ->
            val thumbnail = "${characterThumbnail.path}.${characterThumbnail.extension}"
            val secureThumbnail: String = thumbnail.replace("http", "https")
            Picasso.get().load(secureThumbnail).error(R.drawable.error).into(binding.ivCharacter)
        }
        characterDetailViewModel.characterThumbnail.observe(this, characterThumbnailObserver)
    }

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

    private fun setCharacterName() {
        val characterNameObserver =
            Observer<String> { characterName -> binding.tvName.text = characterName }
        characterDetailViewModel.characterName?.observe(this, characterNameObserver)
    }

    private fun setComicsAppearanceDynamically(comics: List<ItemsItem>?) {
        if (comics?.isEmpty() == true) {
            val tvDynamic = TextView(this)
            tvDynamic.textSize = 15f
            tvDynamic.text = Constants.NO_COMICS
            binding.llComicsLayout.addView(tvDynamic)
        } else {
            for (i in comics?.indices!!) {
                val tvDynamic = TextView(this)
                tvDynamic.textSize = 15f
                tvDynamic.text = comics[i].name
                binding.llComicsLayout.addView(tvDynamic)
            }
        }
    }

    private fun setCharacterId(characterId: Int) {
        characterDetailViewModel.setCharacterId(characterId)
    }
}
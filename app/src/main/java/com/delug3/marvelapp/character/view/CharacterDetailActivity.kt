package com.delug3.marvelapp.character.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.delug3.marvelapp.character.model.Thumbnail
import com.delug3.marvelapp.character.viewmodels.CharacterDetailViewModel
import com.delug3.marvelapp.common.utilities.Constants
import com.delug3.marvelapp.databinding.ActivityDetailCharactersBinding
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailCharactersBinding
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()
    private var characterId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras != null) {
                characterId = extras.getInt(Constants.CHARACTER_ID)
            }
        }
        setCharacterId(characterId)
        getData()
        updateUI()
    }

    private fun getData() {
        characterDetailViewModel.getCharacterDetails()
    }

    private fun updateUI() {

        val characterNameObserver = Observer<String> { characterName ->
            binding.tvName.text = characterName
        }
        val characterDescriptionObserver = Observer<String> { characterDescription ->
            binding.tvDescription.text = characterDescription
        }

        val characterThumbnailObserver = Observer<Thumbnail> { characterThumbnail ->
            val thumbnail = "${characterThumbnail.path}.${characterThumbnail.extension}"
            Picasso.get().load(thumbnail).into(binding.ivCharacter)
        }

        characterDetailViewModel.characterName?.observe(this, characterNameObserver)
        characterDetailViewModel.characterDescription?.observe(this, characterDescriptionObserver)
        characterDetailViewModel.characterThumbnail.observe(this, characterThumbnailObserver)
    }

    private fun setCharacterId(characterId: Int) {
        characterDetailViewModel.setCharacterId(characterId)
    }
}
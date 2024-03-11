package com.example.rick_and_morty.ui.character_info.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.rick_and_morty.R
import com.example.rick_and_morty.app.App
import com.example.rick_and_morty.databinding.FragmentItemBinding
import com.example.rick_and_morty.domain.model.character.Character
import com.example.rick_and_morty.ui.character_info.recycler.InfoAdapter
import com.example.rick_and_morty.ui.character_info.view_model.ItemViewModel
import com.example.rick_and_morty.ui.mapper.CharacterToListOfPairsMapper
import javax.inject.Inject

class ItemFragment : Fragment() {

    @Inject lateinit var vm: ItemViewModel
    private lateinit var binding: FragmentItemBinding

    private var character: Character? = null

    private val infoAdapter = InfoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as App).appComponent.inject(this)
        binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        character = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARGS_CHARACTER, Character::class.java)
        } else {
            requireArguments().getParcelable(ARGS_CHARACTER)
        }

        binding.rvInfo.adapter = infoAdapter

        showContent(CharacterToListOfPairsMapper.map(character!!))

        bindViews()
    }

    private fun showContent(pairs: List<Pair<String, String>>) = with(infoAdapter) {
        clearPairs()
        addPairs(pairs)
        notifyItemRangeChanged(itemCount, pairs.size)
    }

    private fun bindViews() = with(binding) {
        ivImage.load(character?.image) {
            placeholder(R.drawable.ic_placeholder)
        }

        toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        toolBar.title = character?.name
    }

    companion object {
        private const val ARGS_CHARACTER = "character"

        fun createArgs(character: Character): Bundle =
            bundleOf(ARGS_CHARACTER to character)
    }
}
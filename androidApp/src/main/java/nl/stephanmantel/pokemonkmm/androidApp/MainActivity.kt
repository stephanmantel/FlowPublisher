package nl.stephanmantel.pokemonkmm.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nl.stephanmantel.pokemonkmm.shared.Greeting
import android.widget.TextView
import androidx.activity.viewModels

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory()
    }

    private val tv: TextView by lazy {
        findViewById(R.id.text_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.latestPokemon.observe(this) {
            tv.text = it.name
        }
    }
}

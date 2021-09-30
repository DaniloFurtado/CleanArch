package com.example.cleanarch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.recyclerview.widget.ConcatAdapter
import com.example.cleanarch.databinding.ActivityMainBinding
import com.example.cleanarch.di.appModule
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.domain.model.Person
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PersonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        viewModel.requestListPerson()
    }

    private fun initObservers() {
        viewModel
            .loading
            .observe(this, { showDialog ->
                binding.progress.isGone = showDialog.not()
                binding.listItem.isGone = showDialog
            })

        viewModel
            .liveDataListUsers
            .observe(this, {
                binding.listItem.adapter = ConcatAdapter(

                    HeaderAdapter("Lista número 1"),
                    ListPersonAdapter(it, ::onClickHandle),

                    HeaderAdapter("Essa é a Lista número 2"),
                    ListPersonAdapter(it, ::onClickHandle),

                    HeaderAdapter("Agora sim a 3"),
                    ListPersonAdapter(it, ::onClickHandle)
                )
                    .apply { addAdapter(ListPersonAdapter(it, ::onClickHandle)) }
            })
    }

    private fun onClickHandle(person: Person, position: Int){
        Toast.makeText(this, "${person.firstName} position: $position", Toast.LENGTH_SHORT).show()
    }
}

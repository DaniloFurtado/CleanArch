package com.example.cleanarch.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import com.example.cleanarch.databinding.ActivityMainBinding
import com.example.cleanarch.presentation.home.adapter.ListPersonAdapter
import com.example.domain.model.Person
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PersonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initObservers()
        lifecycle.addObserver(viewModel)
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initObservers() {
        initLoadingObserver()
        initListUserObserver()
    }

    private fun initListUserObserver() {
        viewModel
            .liveDataListUsers
            .observe(this) { listPerson ->
                binding.listItem.adapter = ListPersonAdapter(
                    listPerson,
                    ::onClickHandle
                )
            }
    }

    private fun initLoadingObserver() {
        viewModel
            .loading
            .observe(this) { showDialog ->
                binding.progress.isGone = showDialog.not()
                binding.listItem.isGone = showDialog
            }
    }

    private fun onClickHandle(person: Person, position: Int) {
        Toast.makeText(this, "${person.firstName} position: $position", Toast.LENGTH_SHORT).show()
    }
}

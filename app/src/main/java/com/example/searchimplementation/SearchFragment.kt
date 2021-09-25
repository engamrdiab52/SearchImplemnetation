package com.example.searchimplementation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchimplementation.databinding.FragmentSearchFrigmentBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchFrigmentBinding
    private var people: ArrayList<Person> = arrayListOf()
    private lateinit var recyclerView: RecyclerView
    private var matchedPeople: ArrayList<Person> = arrayListOf()
    private var personAdapter: PersonAdapter = PersonAdapter(people)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_frigment, container, false)
        recyclerView = binding.recyclerView

        initRecyclerView()
        performSearch()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initRecyclerView() {

        people = arrayListOf(
            Person("Eric G", 19),
            Person("Reen", 19),
            Person("Jeff", 21),
            Person("Geoffrey", 19),
            Person("Lorem ipsum", 35),
            Person("Paul N", 23),
            Person("Diana", 20),
            Person("Peter", 24),
            Person("Amos", 41),
            Person("Steve", 17),
        )

        personAdapter = PersonAdapter(people).also {
            recyclerView.adapter = it
            recyclerView.adapter!!.notifyDataSetChanged()
        }
        binding.searchView.isSubmitButtonEnabled = true
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }

    private fun search(text: String?) {
        matchedPeople = arrayListOf()

        text?.let {
            people.forEach { person ->
                if (person.name.contains(text, true) ||
                    person.age.toString().contains(text, true)
                ) {
                    matchedPeople.add(person)
                }
            }
            updateRecyclerView()
            if (matchedPeople.isEmpty()) {
                Toast.makeText(requireContext(), "No match found!", Toast.LENGTH_SHORT).show()
            }
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        binding.recyclerView.apply {
            personAdapter.list = matchedPeople
            personAdapter.notifyDataSetChanged()
        }
    }
}
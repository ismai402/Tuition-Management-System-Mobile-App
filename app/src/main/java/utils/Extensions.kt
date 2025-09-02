package com.example.edututor.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.show() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }

fun Fragment.toast(msg: String?) = Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()

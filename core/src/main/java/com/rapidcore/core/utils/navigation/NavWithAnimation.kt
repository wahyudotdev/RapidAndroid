package com.rapidcore.core.utils.navigation

import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.rapidcore.core.utils.navigation.FragmentNavigatorAnimation

class NavWithAnimation : NavHostFragment() {

    override fun onCreateNavHostController(navHostController: NavHostController) {
        super.onCreateNavHostController(navHostController)
        navController.navigatorProvider.addNavigator(
            FragmentNavigatorAnimation(requireContext(), childFragmentManager, id)
        )
    }

}
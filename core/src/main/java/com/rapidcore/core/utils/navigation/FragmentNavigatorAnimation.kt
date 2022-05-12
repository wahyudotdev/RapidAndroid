package com.rapidcore.core.utils.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import com.rapidcore.core.R

@Navigator.Name("fragment")
class FragmentNavigatorAnimation(
    context: Context,
    manager: FragmentManager, containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    companion object {
        private val option = navOptions {
            anim {
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
            }
        }
        private val emptyNavOptions = navOptions {}
    }

    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ) {
        val shouldUseTransitionsInstead = navigatorExtras != null
        val options =
            if (shouldUseTransitionsInstead) navOptions else navOptions.fillEmptyAnimationsWithDefaults()
        super.navigate(entries, options, navigatorExtras)
    }

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val shouldUseTransitionsInstead = navigatorExtras != null
        val options =
            if (shouldUseTransitionsInstead) navOptions else navOptions.fillEmptyAnimationsWithDefaults()
        return super.navigate(destination, args, options, navigatorExtras)
    }

    private fun NavOptions?.fillEmptyAnimationsWithDefaults(): NavOptions =
        this?.copyNavOptionsWithDefaultAnimations() ?: option

    private fun NavOptions.copyNavOptionsWithDefaultAnimations(): NavOptions =
        let { originalNavOptions ->
            navOptions {
                launchSingleTop = originalNavOptions.shouldLaunchSingleTop()
                popUpTo(originalNavOptions.popUpToId){
                    inclusive = originalNavOptions.isPopUpToInclusive()
                    saveState = originalNavOptions.shouldPopUpToSaveState()
                }

                anim {
                    enter =
                        if (originalNavOptions.enterAnim == emptyNavOptions.enterAnim) option.enterAnim
                        else originalNavOptions.enterAnim
                    exit =
                        if (originalNavOptions.exitAnim == emptyNavOptions.exitAnim) option.exitAnim
                        else originalNavOptions.exitAnim
                    popEnter =
                        if (originalNavOptions.popEnterAnim == emptyNavOptions.popEnterAnim) option.popEnterAnim
                        else originalNavOptions.popEnterAnim
                    popExit =
                        if (originalNavOptions.popExitAnim == emptyNavOptions.popExitAnim) option.popExitAnim
                        else originalNavOptions.popExitAnim
                }
            }
        }
}